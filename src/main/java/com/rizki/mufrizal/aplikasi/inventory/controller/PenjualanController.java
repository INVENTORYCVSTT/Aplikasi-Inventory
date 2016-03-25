package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.BarangAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PenjualanAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PenjualanDetailAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PenjualanSementaraAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.TableAutoResizeColumn;
import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.domain.JenisBarang;
import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanDetail;
import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanSementara;
import com.rizki.mufrizal.aplikasi.inventory.view.PenjualanSimpanView;
import com.rizki.mufrizal.aplikasi.inventory.view.PenjualanView;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:56:09 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.controller
 *
 */
public class PenjualanController {

    private PenjualanSimpanView penjualanSimpanView;
    private PenjualanView penjualanView;

    private static final Logger LOGGER = LoggerFactory.getLogger(PenjualanController.class);

    private BarangAbstractTableModel barangAbstractTableModel;
    private PenjualanAbstractTableModel penjualanAbstractTableModel;
    private PenjualanDetailAbstractTableModel penjualanDetailAbstractTableModel;

    private final TableAutoResizeColumn tableAutoResizeColumn = new TableAutoResizeColumn();

    private final List<PenjualanSementara> penjualanSementaras = new ArrayList<>();

    public PenjualanController(PenjualanSimpanView penjualanSimpanView) {
        this.penjualanSimpanView = penjualanSimpanView;
    }

    public PenjualanController(PenjualanView penjualanView) {
        this.penjualanView = penjualanView;
    }

    //halaman simpan view
    //paging barang
    private Integer totalRowsBarang = 0;
    private Integer pageNumberBarang = 1;
    private Integer totalPageBarang = 1;
    private Integer rowsPerPageBarang = 10;

    public void ambilDataBarang() {
        LOGGER.info("Ambil data barang");
        rowsPerPageBarang = Integer.valueOf(this.penjualanSimpanView.getPerPage().getSelectedItem().toString());
        totalRowsBarang = App.barangService().jumlahBarang();
        Double dbTotalPage = Math.ceil(totalRowsBarang.doubleValue() / rowsPerPageBarang.doubleValue());
        totalPageBarang = dbTotalPage.intValue();

        if (pageNumberBarang == 1) {
            this.penjualanSimpanView.getFirst().setEnabled(Boolean.FALSE);
            this.penjualanSimpanView.getPrevious().setEnabled(Boolean.FALSE);
        } else {
            this.penjualanSimpanView.getFirst().setEnabled(Boolean.TRUE);
            this.penjualanSimpanView.getPrevious().setEnabled(Boolean.TRUE);
        }

        if (pageNumberBarang.equals(totalPageBarang)) {
            this.penjualanSimpanView.getNext().setEnabled(Boolean.FALSE);
            this.penjualanSimpanView.getLast().setEnabled(Boolean.FALSE);
        } else {
            this.penjualanSimpanView.getNext().setEnabled(Boolean.TRUE);
            this.penjualanSimpanView.getLast().setEnabled(Boolean.TRUE);
        }

        this.penjualanSimpanView.getLabelPaging().setText("Page " + pageNumberBarang + " of " + totalPageBarang);

        barangAbstractTableModel = new BarangAbstractTableModel(App.barangService().ambilBarangs(pageNumberBarang, rowsPerPageBarang));
        this.penjualanSimpanView.getTabelBarang().setModel(barangAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanSimpanView.getTabelBarang());
        LOGGER.info("Paging : {}", pageNumberBarang);
    }

    public void firstPagingBarang() {
        pageNumberBarang = 1;
        if (this.penjualanSimpanView.getValue().getText().isEmpty()) {
            ambilDataBarang();
        } else {
            cariDataBarang();
        }
        LOGGER.info("Paging awal : {}", pageNumberBarang);
    }

    public void PreviousPagingBarang() {
        if (pageNumberBarang > 1) {
            pageNumberBarang -= 1;
            if (this.penjualanSimpanView.getValue().getText().isEmpty()) {
                ambilDataBarang();
            } else {
                cariDataBarang();
            }
            LOGGER.info("Paging sebelum : {}", pageNumberBarang);
        }
    }

    public void nextPagingBarang() {
        if (pageNumberBarang < totalPageBarang) {
            pageNumberBarang += 1;
            if (this.penjualanSimpanView.getValue().getText().isEmpty()) {
                ambilDataBarang();
            } else {
                cariDataBarang();
            }
            LOGGER.info("Paging selanjutnya : {}", pageNumberBarang);
        }
    }

    public void lastPagingBarang() {
        pageNumberBarang = totalPageBarang;
        if (this.penjualanSimpanView.getValue().getText().isEmpty()) {
            ambilDataBarang();
        } else {
            cariDataBarang();
        }
        LOGGER.info("Paging akhir : {}", pageNumberBarang);
    }

    public void refreshBarang() {
        ambilDataBarang();
        LOGGER.info("refresh paging : {}", pageNumberBarang);
    }
    //end paging barang

    private PenjualanSementara checkContains(PenjualanSementara penjualanSementara1, List<PenjualanSementara> penjualanSementaras1) {
        for (PenjualanSementara ps : penjualanSementaras1) {
            if (ps.getIdBarang().equals(penjualanSementara1.getIdBarang())) {
                LOGGER.info("id sama : {}", ps.getIdBarang());
                return ps;
            }
        }
        LOGGER.info("Beda id");
        return null;
    }

    public void tambahPenjualanSementara() {
        try {
            PenjualanSementara penjualanSementara = new PenjualanSementara();
            Integer index = this.penjualanSimpanView.getTabelBarang().getSelectedRow();

            Integer jumlahBarangYangTersedia = Integer.parseInt(String.valueOf(this.penjualanSimpanView.getTabelBarang().getValueAt(index, 6)));

            //insert value
            penjualanSementara.setIdBarang(String.valueOf(this.penjualanSimpanView.getTabelBarang().getValueAt(index, 1)));
            penjualanSementara.setNamaBarang(String.valueOf(this.penjualanSimpanView.getTabelBarang().getValueAt(index, 2)));
            penjualanSementara.setJenisBarang(JenisBarang.valueOf(this.penjualanSimpanView.getTabelBarang().getValueAt(index, 3).toString()));

            java.util.Date tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(this.penjualanSimpanView.getTabelBarang().getValueAt(index, 4)));
            penjualanSementara.setTanggalKadaluarsa(tanggal);

            String hargaSatuanBarang = this.penjualanSimpanView.getTabelBarang().getValueAt(index, 5).toString();
            penjualanSementara.setHargaSatuanBarang(BigDecimal.valueOf(Double.parseDouble(hargaSatuanBarang.split(" ")[1])));

            PenjualanSementara ps = checkContains(penjualanSementara, penjualanSementaras);

            if (ps != null) {
                if (ps.getJumlahBarang() + 1 <= jumlahBarangYangTersedia) {
                    penjualanSementara.setJumlahBarang(ps.getJumlahBarang() + 1);
                    int indexItem = penjualanSementaras.indexOf(ps);
                    penjualanSementaras.set(indexItem, penjualanSementara);
                } else {
                    JOptionPane.showMessageDialog(null, "Stok Barang tidak cukup", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                penjualanSementara.setJumlahBarang(1);
                penjualanSementaras.add(penjualanSementara);
            }

            tampilPenjualanSementara();
        } catch (ParseException ex) {
            LOGGER.error("error di : {}", ex);
        }
    }

    public void tampilPenjualanSementara() {
        PenjualanSementaraAbstractTableModel penjualanSementaraAbstractTableModel = new PenjualanSementaraAbstractTableModel(penjualanSementaras);
        this.penjualanSimpanView.getTabelPenjualanSementara().setModel(penjualanSementaraAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanSimpanView.getTabelPenjualanSementara());
    }

    public void simpanTransaksi() {
        if (this.penjualanSimpanView.getTanggalPenjualan().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Tanggal Penjualan belum dipilih", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (this.penjualanSimpanView.getNamaPembeli().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama Pembeli belum diisi", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Penjualan penjualan = new Penjualan();
            List<PenjualanDetail> penjualanDetails = new ArrayList<>();
            BigDecimal totalHarga = new BigDecimal(BigInteger.ZERO);
            for (PenjualanSementara penjualanSementara : penjualanSementaras) {

                BigDecimal totalHargaBarang = penjualanSementara.getHargaSatuanBarang().multiply(BigDecimal.valueOf(penjualanSementara.getJumlahBarang().doubleValue()));
                totalHarga = totalHarga.add(totalHargaBarang);

                Barang barang = App.barangService().getBarang(penjualanSementara.getIdBarang());
                barang.setJumlahBarang(barang.getJumlahBarang() - penjualanSementara.getJumlahBarang());
                App.barangService().editBarang(barang);

                PenjualanDetail penjualanDetail = new PenjualanDetail();
                penjualanDetail.setJumlahBarang(penjualanSementara.getJumlahBarang());
                penjualanDetail.setTotalHargaPerBarang(totalHargaBarang);

                penjualanDetail.setBarang(barang);
                penjualanDetail.setPenjualan(penjualan);

                penjualanDetails.add(penjualanDetail);

            }

            penjualan.setTanggalTransaksi(this.penjualanSimpanView.getTanggalPenjualan().getDate());
            penjualan.setNamaPembeli(this.penjualanSimpanView.getNamaPembeli().getText());
            penjualan.setTotalHarga(totalHarga);
            penjualan.setPenjualanDetails(penjualanDetails);

            App.penjualanService().simpanPenjualan(penjualan);

            JOptionPane.showMessageDialog(null, "Data Penjualan Tersimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
            ambilDataBarang();
            penjualanSementaras.clear();
            tampilPenjualanSementara();
        }
    }

    //cari data barang
    public void cariDataBarang() {
        totalRowsBarang = 0;
        pageNumberBarang = 1;
        totalPageBarang = 1;
        rowsPerPageBarang = 10;

        if (this.penjualanSimpanView.getValue().getText().isEmpty()) {
            ambilDataBarang();
        } else {
            String value = this.penjualanSimpanView.getValue().getText();
            String key = null;

            if (this.penjualanSimpanView.getKey().getSelectedIndex() == 0) {
                key = "idBarang";
            } else if (this.penjualanSimpanView.getKey().getSelectedIndex() == 1) {
                key = "namaBarang";
            }

            LOGGER.info("cari data barang");
            rowsPerPageBarang = Integer.valueOf(this.penjualanSimpanView.getPerPage().getSelectedItem().toString());
            totalRowsBarang = App.barangService().jumlahCariBarang(key, value);
            Double dbTotalPage = Math.ceil(totalRowsBarang.doubleValue() / rowsPerPageBarang.doubleValue());
            totalPageBarang = dbTotalPage.intValue();

            if (pageNumberBarang == 1) {
                this.penjualanSimpanView.getFirst().setEnabled(Boolean.FALSE);
                this.penjualanSimpanView.getPrevious().setEnabled(Boolean.FALSE);
            } else {
                this.penjualanSimpanView.getFirst().setEnabled(Boolean.TRUE);
                this.penjualanSimpanView.getPrevious().setEnabled(Boolean.TRUE);
            }

            if (pageNumberBarang.equals(totalPageBarang)) {
                this.penjualanSimpanView.getNext().setEnabled(Boolean.FALSE);
                this.penjualanSimpanView.getLast().setEnabled(Boolean.FALSE);
            } else {
                this.penjualanSimpanView.getNext().setEnabled(Boolean.TRUE);
                this.penjualanSimpanView.getLast().setEnabled(Boolean.TRUE);
            }

            this.penjualanSimpanView.getLabelPaging().setText("Page " + pageNumberBarang + " of " + totalPageBarang);

            barangAbstractTableModel = new BarangAbstractTableModel(App.barangService().cariBarang(key, value, pageNumberBarang, rowsPerPageBarang));
            this.penjualanSimpanView.getTabelBarang().setModel(barangAbstractTableModel);
            tableAutoResizeColumn.autoResizeColumn(this.penjualanSimpanView.getTabelBarang());
            LOGGER.info("Paging : {}", pageNumberBarang);
        }

    }
    //end cari data barang

    //end halaman simpan view
    //halaman penjualan view
    //penjualan
    //paging penjualan
    private Integer totalRowsPenjualan = 0;
    private Integer pageNumberPenjualan = 1;
    private Integer totalPagePenjualan = 1;
    private Integer rowsPerPagePenjualan = 10;

    public void ambilDataPenjualan() {
        LOGGER.info("Ambil data Penjualan");
        rowsPerPagePenjualan = Integer.valueOf(this.penjualanView.getPerPage().getSelectedItem().toString());
        totalRowsPenjualan = App.penjualanService().jumlahPenjualan();
        Double dbTotalPage = Math.ceil(totalRowsPenjualan.doubleValue() / rowsPerPagePenjualan.doubleValue());
        totalPagePenjualan = dbTotalPage.intValue();

        if (pageNumberPenjualan == 1) {
            this.penjualanView.getFirst().setEnabled(Boolean.FALSE);
            this.penjualanView.getPrevious().setEnabled(Boolean.FALSE);
        } else {
            this.penjualanView.getFirst().setEnabled(Boolean.TRUE);
            this.penjualanView.getPrevious().setEnabled(Boolean.TRUE);
        }

        if (pageNumberPenjualan.equals(totalPagePenjualan)) {
            this.penjualanView.getNext().setEnabled(Boolean.FALSE);
            this.penjualanView.getLast().setEnabled(Boolean.FALSE);
        } else {
            this.penjualanView.getNext().setEnabled(Boolean.TRUE);
            this.penjualanView.getLast().setEnabled(Boolean.TRUE);
        }

        this.penjualanView.getLabelPaging().setText("Page " + pageNumberPenjualan + " of " + totalPagePenjualan);
        this.penjualanView.getLabelTotalRecord().setText("Total Record " + totalRowsPenjualan);

        penjualanAbstractTableModel = new PenjualanAbstractTableModel(App.penjualanService().ambilPenjualans(pageNumberPenjualan, rowsPerPagePenjualan));
        this.penjualanView.getTabelPenjualan().setModel(penjualanAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanView.getTabelPenjualan());
        LOGGER.info("Paging : {}", pageNumberPenjualan);

        //inisialisasi tabel penjualan detail kosong
        List<PenjualanDetail> penjualanDetails = new ArrayList<>();
        penjualanDetailAbstractTableModel = new PenjualanDetailAbstractTableModel(penjualanDetails);
        this.penjualanView.getTabelPenjualanDetail().setModel(penjualanDetailAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanView.getTabelPenjualanDetail());
    }

    public void firstPagingPenjualan() {
        pageNumberPenjualan = 1;
        if (this.penjualanView.getValue().getText().isEmpty()) {
            ambilDataPenjualan();
        } else {
            cariDataPenjualanPadaPenjualanView();
        }
        LOGGER.info("Paging awal : {}", pageNumberPenjualan);
    }

    public void PreviousPagingPenjualan() {
        if (pageNumberPenjualan > 1) {
            pageNumberPenjualan -= 1;
            if (this.penjualanView.getValue().getText().isEmpty()) {
                ambilDataPenjualan();
            } else {
                cariDataPenjualanPadaPenjualanView();
            }
            LOGGER.info("Paging sebelum : {}", pageNumberPenjualan);
        }
    }

    public void nextPagingPenjualan() {
        if (pageNumberPenjualan < totalPagePenjualan) {
            pageNumberPenjualan += 1;
            if (this.penjualanView.getValue().getText().isEmpty()) {
                ambilDataPenjualan();
            } else {
                cariDataPenjualanPadaPenjualanView();
            }
            LOGGER.info("Paging selanjutnya : {}", pageNumberPenjualan);
        }
    }

    public void lastPagingPenjualan() {
        pageNumberPenjualan = totalPagePenjualan;
        if (this.penjualanView.getValue().getText().isEmpty()) {
            ambilDataPenjualan();
        } else {
            cariDataPenjualanPadaPenjualanView();
        }
        LOGGER.info("Paging akhir : {}", pageNumberPenjualan);
    }

    public void refreshPenjualan() {
        ambilDataPenjualan();
        LOGGER.info("refresh paging : {}", pageNumberPenjualan);
    }
    //end paging penjualan

    //end penjualan
    //penjualan detail
    public void tampilkanDataPenjualanDetail() {
        Integer index = this.penjualanView.getTabelPenjualan().getSelectedRow();
        String kodeTransaksiPenjualan = String.valueOf(this.penjualanView.getTabelPenjualan().getValueAt(index, 1));
        List<PenjualanDetail> penjualanDetails = App.penjualanDetailService().ambilPenjualanDetails(kodeTransaksiPenjualan);
        penjualanDetailAbstractTableModel = new PenjualanDetailAbstractTableModel(penjualanDetails);
        this.penjualanView.getTabelPenjualanDetail().setModel(penjualanDetailAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanView.getTabelPenjualanDetail());
    }
    //end penjualan detail

    //cari data penjualan
    public void cariDataPenjualanPadaPenjualanView() {
        totalRowsPenjualan = 0;
        pageNumberPenjualan = 1;
        totalPagePenjualan = 1;
        rowsPerPagePenjualan = 10;

        if (this.penjualanView.getValue().getText().isEmpty()) {
            ambilDataPenjualan();
        } else {
            String value = this.penjualanView.getValue().getText();
            String key = null;

            if (this.penjualanView.getKey().getSelectedIndex() == 0) {
                key = "kodeTransaksiPenjualan";
            } else if (this.penjualanView.getKey().getSelectedIndex() == 1) {
                key = "namaPembeli";
            }

            LOGGER.info("cari data penjualan");
            rowsPerPagePenjualan = Integer.valueOf(this.penjualanView.getPerPage().getSelectedItem().toString());
            totalRowsPenjualan = App.penjualanService().jumlahCariPenjualan(key, value);
            Double dbTotalPage = Math.ceil(totalRowsPenjualan.doubleValue() / rowsPerPagePenjualan.doubleValue());
            totalPagePenjualan = dbTotalPage.intValue();

            if (pageNumberPenjualan == 1) {
                this.penjualanView.getFirst().setEnabled(Boolean.FALSE);
                this.penjualanView.getPrevious().setEnabled(Boolean.FALSE);
            } else {
                this.penjualanView.getFirst().setEnabled(Boolean.TRUE);
                this.penjualanView.getPrevious().setEnabled(Boolean.TRUE);
            }

            if (pageNumberPenjualan.equals(totalPagePenjualan)) {
                this.penjualanView.getNext().setEnabled(Boolean.FALSE);
                this.penjualanView.getLast().setEnabled(Boolean.FALSE);
            } else {
                this.penjualanView.getNext().setEnabled(Boolean.TRUE);
                this.penjualanView.getLast().setEnabled(Boolean.TRUE);
            }

            this.penjualanView.getLabelPaging().setText("Page " + pageNumberPenjualan + " of " + totalPagePenjualan);
            this.penjualanView.getLabelTotalRecord().setText("Total Record " + totalRowsPenjualan);

            penjualanAbstractTableModel = new PenjualanAbstractTableModel(App.penjualanService().cariPenjualan(key, value, pageNumberPenjualan, rowsPerPagePenjualan));
            this.penjualanView.getTabelPenjualan().setModel(penjualanAbstractTableModel);
            tableAutoResizeColumn.autoResizeColumn(this.penjualanView.getTabelPenjualan());
            LOGGER.info("Paging : {}", pageNumberPenjualan);
        }
    }
    //end cari data penjualan
    //end halaman penjualan view
}
