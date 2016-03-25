package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PembelianAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PembelianDetailAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PembelianSementaraAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.TableAutoResizeColumn;
import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianDetail;
import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianSementara;
import com.rizki.mufrizal.aplikasi.inventory.view.PembelianSimpanView;
import com.rizki.mufrizal.aplikasi.inventory.view.PembelianView;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<PembelianSementara> pembelianSementaras = new ArrayList<>();
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

    //halaman pembelian view
    //paging pembelian
    private Integer totalRowsPembelian = 0;
    private Integer pageNumberPembelian = 1;
    private Integer totalPagePembelian = 1;
    private Integer rowsPerPagePembelian = 10;

    public void ambilDataPembelian() {
        LOGGER.info("Ambil data barang");
        rowsPerPagePembelian = Integer.valueOf(this.pembelianView.getPerPage().getSelectedItem().toString());
        totalRowsPembelian = App.pembelianService().jumlahPembelian();
        Double dbTotalPage = Math.ceil(totalRowsPembelian.doubleValue() / rowsPerPagePembelian.doubleValue());
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

        this.pembelianView.getLabelPaging().setText("Page " + pageNumberPembelian + " of " + totalPagePembelian);

        pembelianAbstractTableModel = new PembelianAbstractTableModel(App.pembelianService().ambilPembelians(pageNumberPembelian, rowsPerPagePembelian));
        this.pembelianView.getTabelPembelian().setModel(pembelianAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelian());
        LOGGER.info("Paging : {}", pageNumberPembelian);
        
        //inisialisasi tabel pembelian detail kosong
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
    //end paging pembelian
    
        //cari data pembelian
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
            rowsPerPagePembelian = Integer.valueOf(this.pembelianView.getPerPage().getSelectedItem().toString());
            totalRowsPembelian = App.pembelianService().jumlahCariPembelian(key, value);
            Double dbTotalPage = Math.ceil(totalRowsPembelian.doubleValue() / rowsPerPagePembelian.doubleValue());
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

            this.pembelianView.getLabelPaging().setText("Page " + pageNumberPembelian + " of " + totalPagePembelian);
            this.pembelianView.getLabelTotalRecord().setText("Total Record " + pembelianView);

            pembelianAbstractTableModel = new PembelianAbstractTableModel(App.pembelianService().cariPembelian(key, value, pageNumberPembelian, rowsPerPagePembelian));
            this.pembelianView.getTabelPembelian().setModel(pembelianAbstractTableModel);
            tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelian());
            LOGGER.info("Paging : {}", pageNumberPembelian);
        }
    }
    //end cari data pembelian
    
    //pembelian detail
    public void tampilkanDataPembelianDetail() {
        Integer index = this.pembelianView.getTabelPembelian().getSelectedRow();
        String kodeTransaksiPembelian = String.valueOf(this.pembelianView.getTabelPembelian().getValueAt(index, 1));
        List<PembelianDetail> pembelianDetails = App.pembelianDetailService().ambilPembelianDetails(kodeTransaksiPembelian);
        pembelianDetailAbstractTableModel = new PembelianDetailAbstractTableModel(pembelianDetails);
        this.pembelianView.getTabelPembelianDetail().setModel(pembelianDetailAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianView.getTabelPembelianDetail());
    }
    //end pembelian detail
    
    //end halaman pembelian view
    
    //halaman simpan pembelian view
    
    public List<String> tampilkanDataBarangComboBox(){
        List<Barang> barangs = App.barangService().getSemuaBarang();
        
        isiComboBox.add("--Pilih--");
        
        barangs.stream().map((barang) -> {
            isiComboBox.add(barang.getIdBarang());
            return barang;
        }).forEach((barang) -> {
            LOGGER.debug("cek isi id barang : {}", barang.getIdBarang());
        });

        return isiComboBox;
    }
    
    public void inisialisasiTabelPembelianKosong(){
        //inisialisasi tabel pembelian kosong
        pembelianSementaraAbstractTableModel = new PembelianSementaraAbstractTableModel(pembelianSementaras);
        this.pembelianSimpanView.getTabelPembelianSementara().setModel(pembelianSementaraAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.pembelianSimpanView.getTabelPembelianSementara());
    }
    
    //end halaman simpan pembelian view
    
}
