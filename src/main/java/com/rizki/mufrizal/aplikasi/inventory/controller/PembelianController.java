package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PembelianAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PembelianDetailAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PembelianSementaraAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.TableAutoResizeColumn;
import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.domain.JenisBarang;
import com.rizki.mufrizal.aplikasi.inventory.domain.Pembelian;
import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianDetail;
import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianSementara;
import com.rizki.mufrizal.aplikasi.inventory.view.PembelianSimpanView;
import com.rizki.mufrizal.aplikasi.inventory.view.PembelianView;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 5:03:06 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.controller
 *
 */
public class PembelianController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PembelianController.class);

    private PembelianView pembelianView;
    private PembelianSimpanView pembelianSimpanView;

    private final TableAutoResizeColumn tableAutoResizeColumn = new TableAutoResizeColumn();

    @SuppressWarnings("FieldMayBeFinal")
    private List<PembelianSementara> pembelianSementaras = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> isiComboBox = new ArrayList<>();

    private PembelianAbstractTableModel pembelianAbstractTableModel;
    private PembelianDetailAbstractTableModel pembelianDetailAbstractTableModel;
    private PembelianSementaraAbstractTableModel pembelianSementaraAbstractTableModel;

    public PembelianController(PembelianView pembelianView) {
        this.pembelianView = pembelianView;
    }

    public PembelianController(PembelianSimpanView pembelianSimpanView) {
        this.pembelianSimpanView = pembelianSimpanView;
    }

    // halaman pembelian view
    // paging pembelian
    private Integer totalRowsPembelian = 0;
    private Integer pageNumberPembelian = 1;
    private Integer totalPagePembelian = 1;
    private Integer rowsPerPagePembelian = 10;

    public void ambilDataPembelian() {
        LOGGER.info("Ambil data barang");
        rowsPerPagePembelian
                = Integer.valueOf(this.pembelianView.getPerPage().getSelectedItem().toString());
        totalRowsPembelian = App.pembelianService().jumlahPembelian();
        Double dbTotalPage
                = Math.ceil(totalRowsPembelian.doubleValue() / rowsPerPagePembelian.doubleValue());
        totalPagePembelian = dbTotalPage.intValue();

        if (pageNumberPembelian == 1) {
            this.pembelianView.getFirst().setEnabled(Boolean.FALSE);
            this.pembelianView.getPrevious().setEnabled(Boolean.FALSE);
        } else {
            this.pembelianView.getFirst().setEnabled(Boolean.TRUE);
            this.pembelianView.getPrevious().setEnabled(Boolean.TRUE);
        }

        if (pageNumberPembelian.equals(totalPagePembelian)) {
            this.pembelianView.getNext().setEnabled(Boolean.FALSE);
            this.pembelianView.getLast().setEnabled(Boolean.FALSE);
        } else {
            this.pembelianView.getNext().setEnabled(Boolean.TRUE);
            this.pembelianView.getLast().setEnabled(Boolean.TRUE);
        }

        this.pembelianView.getLabelPaging()
                .setText("Page " + pageNumberPembelian + " of " + totalPagePembelian);

        pembelianAbstractTableModel = new PembelianAbstractTableModel(
                App.pembelianService().ambilPembelians(pageNumberPembelian, rowsPerPagePembelian));
        this.pembelianView.getTabelPembelian().setModel(pembelianAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelian());
        LOGGER.info("Paging : {}", pageNumberPembelian);

        // inisialisasi tabel pembelian detail kosong
        List<PembelianDetail> pembelianDetails = new ArrayList<>();
        pembelianDetailAbstractTableModel = new PembelianDetailAbstractTableModel(pembelianDetails);
        this.pembelianView.getTabelPembelianDetail().setModel(pembelianAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelianDetail());
    }

    public void firstPagingPembelian() {
        pageNumberPembelian = 1;
        if (this.pembelianView.getValue().getText().isEmpty()) {
            ambilDataPembelian();
        } else {
            cariDataPembelianPadaPembelianView();
        }
        LOGGER.info("Paging awal : {}", pageNumberPembelian);
    }

    public void PreviousPagingPembelian() {
        if (pageNumberPembelian > 1) {
            pageNumberPembelian -= 1;
            if (this.pembelianView.getValue().getText().isEmpty()) {
                ambilDataPembelian();
            } else {
                cariDataPembelianPadaPembelianView();
            }
            LOGGER.info("Paging sebelum : {}", pageNumberPembelian);
        }
    }

    public void nextPagingPembelian() {
        if (pageNumberPembelian < totalPagePembelian) {
            pageNumberPembelian += 1;
            if (this.pembelianView.getValue().getText().isEmpty()) {
                ambilDataPembelian();
            } else {
                cariDataPembelianPadaPembelianView();
            }
            LOGGER.info("Paging selanjutnya : {}", pageNumberPembelian);
        }
    }

    public void lastPagingPembelian() {
        pageNumberPembelian = totalPagePembelian;
        if (this.pembelianView.getValue().getText().isEmpty()) {
            ambilDataPembelian();
        } else {
            cariDataPembelianPadaPembelianView();
        }
        LOGGER.info("Paging akhir : {}", pageNumberPembelian);
    }

    public void refreshPembelian() {
        ambilDataPembelian();
        LOGGER.info("refresh paging : {}", pageNumberPembelian);
    }
    // end paging pembelian

    // cari data pembelian
    public void cariDataPembelianPadaPembelianView() {
        totalRowsPembelian = 0;
        pageNumberPembelian = 1;
        totalPagePembelian = 1;
        rowsPerPagePembelian = 10;

        if (this.pembelianView.getValue().getText().isEmpty()) {
            ambilDataPembelian();
        } else {
            String value = this.pembelianView.getValue().getText();
            String key = null;

            if (this.pembelianView.getKey().getSelectedIndex() == 0) {
                key = "kodeTransaksiPembelian";
            } else if (this.pembelianView.getKey().getSelectedIndex() == 1) {
                key = "namaSuplier";
            }

            LOGGER.info("cari data pembelian");
            rowsPerPagePembelian
                    = Integer.valueOf(this.pembelianView.getPerPage().getSelectedItem().toString());
            totalRowsPembelian = App.pembelianService().jumlahCariPembelian(key, value);
            Double dbTotalPage
                    = Math.ceil(totalRowsPembelian.doubleValue() / rowsPerPagePembelian.doubleValue());
            totalPagePembelian = dbTotalPage.intValue();

            if (pageNumberPembelian == 1) {
                this.pembelianView.getFirst().setEnabled(Boolean.FALSE);
                this.pembelianView.getPrevious().setEnabled(Boolean.FALSE);
            } else {
                this.pembelianView.getFirst().setEnabled(Boolean.TRUE);
                this.pembelianView.getPrevious().setEnabled(Boolean.TRUE);
            }

            if (pageNumberPembelian.equals(totalPagePembelian)) {
                this.pembelianView.getNext().setEnabled(Boolean.FALSE);
                this.pembelianView.getLast().setEnabled(Boolean.FALSE);
            } else {
                this.pembelianView.getNext().setEnabled(Boolean.TRUE);
                this.pembelianView.getLast().setEnabled(Boolean.TRUE);
            }

            this.pembelianView.getLabelPaging()
                    .setText("Page " + pageNumberPembelian + " of " + totalPagePembelian);
            this.pembelianView.getLabelTotalRecord().setText("Total Record " + pembelianView);

            pembelianAbstractTableModel = new PembelianAbstractTableModel(App.pembelianService()
                    .cariPembelian(key, value, pageNumberPembelian, rowsPerPagePembelian));
            this.pembelianView.getTabelPembelian().setModel(pembelianAbstractTableModel);
            tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelian());
            LOGGER.info("Paging : {}", pageNumberPembelian);
        }
    }
    // end cari data pembelian

    // pembelian detail
    public void tampilkanDataPembelianDetail() {
        Integer index = this.pembelianView.getTabelPembelian().getSelectedRow();
        String kodeTransaksiPembelian
                = String.valueOf(this.pembelianView.getTabelPembelian().getValueAt(index, 1));
        List<PembelianDetail> pembelianDetails
                = App.pembelianDetailService().ambilPembelianDetails(kodeTransaksiPembelian);
        pembelianDetailAbstractTableModel = new PembelianDetailAbstractTableModel(pembelianDetails);
        this.pembelianView.getTabelPembelianDetail().setModel(pembelianDetailAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelianDetail());
    }
    // end pembelian detail

    // end halaman pembelian view
    // halaman simpan pembelian view
    public List<String> tampilkanDataBarangComboBox() {
        List<Barang> barangs = App.barangService().getSemuaBarang();

        isiComboBox.add("--Pilih--");

        barangs.stream().forEach((barang) -> {
            isiComboBox.add(barang.getIdBarang());
        });
        return isiComboBox;
    }

    private String checkContains(String idBarang, List<String> barangs) {
        Iterator<String> iterator = barangs.iterator();
        while (iterator.hasNext()) {
            String b = iterator.next();
            if (b.equals(idBarang)) {
                LOGGER.info("id sama : {}", b);
                return b;
            }
        }
        LOGGER.info("Beda id");
        return null;
    }

    public void tampilkanDataComboBox() {
        List<Barang> barangs = App.barangService().getSemuaBarang();

        barangs.stream().forEach((barang) -> {
            String b = checkContains(barang.getIdBarang(), isiComboBox);
            if (b == null) {
                this.pembelianSimpanView.getIdBarang().addItem(barang.getIdBarang());
            }
        });

    }

    public void inisialisasiTabelPembelianKosong() {
        // inisialisasi tabel pembelian kosong
        pembelianSementaraAbstractTableModel
                = new PembelianSementaraAbstractTableModel(pembelianSementaras);
        this.pembelianSimpanView.getTabelPembelianSementara()
                .setModel(pembelianSementaraAbstractTableModel);
        tableAutoResizeColumn
                .autoResizeColumn(this.pembelianSimpanView.getTabelPembelianSementara());
    }

    public void tampilkanIsiDataBarangBerdasarkanIdBarang() {
        if (this.pembelianSimpanView.getIdBarang().getSelectedIndex() == 0) {
            this.pembelianSimpanView.getNamaBarang().setText(null);
            this.pembelianSimpanView.getJenisBarang().setSelectedIndex(0);
            this.pembelianSimpanView.getTanggalKadaluarsa().setDate(null);
            this.pembelianSimpanView.getHargaSatuan().setText(null);
            this.pembelianSimpanView.getJumlahBarang().setText(null);
        } else {
            Barang barang = App.barangService()
                    .getBarang(this.pembelianSimpanView.getIdBarang().getSelectedItem().toString());
            this.pembelianSimpanView.getNamaBarang().setText(barang.getNamaBarang());
            this.pembelianSimpanView.getJenisBarang()
                    .setSelectedItem(barang.getJenisBarang().toString());
            this.pembelianSimpanView.getTanggalKadaluarsa().setDate(barang.getTanggalKadaluarsa());
            this.pembelianSimpanView.getHargaSatuan()
                    .setText(barang.getHargaSatuanBarang().toString());
            this.pembelianSimpanView.getJumlahBarang().setText(barang.getJumlahBarang().toString());
        }
    }

    public void tambahPembelianSementara() {
        PembelianSementara pembelianSementara = new PembelianSementara();

        if (this.pembelianSimpanView.getIdBarang().getSelectedIndex() == 0) {
            pembelianSementara.setIdBarang(null);
        } else {
            pembelianSementara.setIdBarang(this.pembelianSimpanView.getIdBarang().getSelectedItem().toString());
        }

        pembelianSementara.setNamaBarang(this.pembelianSimpanView.getNamaBarang().getText());
        pembelianSementara.setTanggalKadaluarsa(this.pembelianSimpanView.getTanggalKadaluarsa().getDate());
        pembelianSementara.setJenisBarang(JenisBarang.valueOf(this.pembelianSimpanView.getJenisBarang().getSelectedItem().toString()));
        pembelianSementara.setJumlahBarang(Integer.parseInt(this.pembelianSimpanView.getJumlahBarang().getText()));
        pembelianSementara.setHargaSatuanBarang(BigDecimal.valueOf(Double.parseDouble(this.pembelianSimpanView.getHargaSatuan().getText())));

        pembelianSementaras.add(pembelianSementara);
        tampilPembelianSementara();
        clearForm();
    }

    public void tampilPembelianSementara() {
        pembelianSementaraAbstractTableModel = new PembelianSementaraAbstractTableModel(pembelianSementaras);
        this.pembelianSimpanView.getTabelPembelianSementara().setModel(pembelianSementaraAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianSimpanView.getTabelPembelianSementara());
    }

    public void clearForm() {
        this.pembelianSimpanView.getNamaBarang().setText(null);
        this.pembelianSimpanView.getJenisBarang().setSelectedIndex(0);
        this.pembelianSimpanView.getTanggalKadaluarsa().setDate(null);
        this.pembelianSimpanView.getHargaSatuan().setText(null);
        this.pembelianSimpanView.getJumlahBarang().setText(null);
    }

    public void simpanTransaksi() {
        if (this.pembelianSimpanView.getTanggalTransaksi().getDate() == null) {
            JOptionPane.showMessageDialog(null, "Tanggal Pembelian belum dipilih", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (this.pembelianSimpanView.getNamaSuplier().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama Suplier belum diisi", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Pembelian pembelian = new Pembelian();
            List<PembelianDetail> pembelianDetails = new ArrayList<>();
            BigDecimal totalHarga = new BigDecimal(BigInteger.ZERO);
            for (PembelianSementara pembelianSementara : pembelianSementaras) {

                BigDecimal totalHargaBarang = pembelianSementara.getHargaSatuanBarang().multiply(BigDecimal.valueOf(pembelianSementara.getJumlahBarang().doubleValue()));
                totalHarga = totalHarga.add(totalHargaBarang);

                LOGGER.debug("id barang : {}", pembelianSementara.getIdBarang());

                @SuppressWarnings("UnusedAssignment")
                Barang barang = new Barang();
                Barang barangBaru = new Barang();
                PembelianDetail pembelianDetail = new PembelianDetail();

                if (pembelianSementara.getIdBarang() == null) {
                    barangBaru.setNamaBarang(pembelianSementara.getNamaBarang());
                    barangBaru.setJenisBarang(pembelianSementara.getJenisBarang());
                    barangBaru.setTanggalKadaluarsa(pembelianSementara.getTanggalKadaluarsa());
                    barangBaru.setHargaSatuanBarang(pembelianSementara.getHargaSatuanBarang());
                    barangBaru.setJumlahBarang(pembelianSementara.getJumlahBarang());

                    App.barangService().simpanBarang(barangBaru);
                    pembelianDetail.setBarang(barangBaru);
                } else {
                    barang = App.barangService().getBarang(pembelianSementara.getIdBarang());
                    barang.setNamaBarang(pembelianSementara.getNamaBarang());
                    barang.setJenisBarang(pembelianSementara.getJenisBarang());
                    barang.setTanggalKadaluarsa(pembelianSementara.getTanggalKadaluarsa());
                    barang.setHargaSatuanBarang(pembelianSementara.getHargaSatuanBarang());

                    if (!Objects.equals(barang.getJumlahBarang(), pembelianSementara.getJumlahBarang())) {
                        barang.setJumlahBarang(barang.getJumlahBarang() + pembelianSementara.getJumlahBarang());
                    } else {
                        barang.setJumlahBarang(pembelianSementara.getJumlahBarang());
                    }

                    App.barangService().editBarang(barang);
                    pembelianDetail.setBarang(barang);
                }

                pembelianDetail.setJumlahBarang(pembelianSementara.getJumlahBarang());
                pembelianDetail.setTotalHargaPerBarang(totalHargaBarang);

                pembelianDetail.setPembelian(pembelian);

                pembelianDetails.add(pembelianDetail);
            }

            pembelian.setTanggalTransaksi(this.pembelianSimpanView.getTanggalTransaksi().getDate());
            pembelian.setNamaSuplier(this.pembelianSimpanView.getNamaSuplier().getText());
            pembelian.setTotalHarga(totalHarga);
            pembelian.setPembelianDetails(pembelianDetails);

            App.pembelianService().simpanPembelian(pembelian);

            JOptionPane.showMessageDialog(null, "Data Pembelian Tersimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
            pembelianSementaras.clear();
            tampilPembelianSementara();
            clearForm();
            tampilkanDataComboBox();
            this.pembelianSimpanView.getTanggalTransaksi().setDate(null);
            this.pembelianSimpanView.getNamaSuplier().setText(null);
        }
    }

    // end halaman simpan pembelian view
}
