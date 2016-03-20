package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.BarangAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.TableAutoResizeColumn;
import com.rizki.mufrizal.aplikasi.inventory.view.PenjualanSimpanView;
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

    private final PenjualanSimpanView penjualanSimpanView;
    private static final Logger LOGGER = LoggerFactory.getLogger(PenjualanController.class);
    private BarangAbstractTableModel barangAbstractTableModel;
    private final TableAutoResizeColumn tableAutoResizeColumn = new TableAutoResizeColumn();

    public PenjualanController(PenjualanSimpanView penjualanSimpanView) {
        this.penjualanSimpanView = penjualanSimpanView;
    }

    //paging
    private Integer totalRows = 0;
    private Integer pageNumber = 1;
    private Integer totalPage = 1;
    private Integer rowsPerPage = 10;

    public void ambilDataBarang() {
        LOGGER.info("Ambil data barang");
        rowsPerPage = Integer.valueOf(this.penjualanSimpanView.getPerPage().getSelectedItem().toString());
        totalRows = App.barangService().jumlahBarang();
        Double dbTotalPage = Math.ceil(totalRows.doubleValue() / rowsPerPage.doubleValue());
        totalPage = dbTotalPage.intValue();

        if (pageNumber == 1) {
            this.penjualanSimpanView.getFirst().setEnabled(Boolean.FALSE);
            this.penjualanSimpanView.getPrevious().setEnabled(Boolean.FALSE);
        } else {
            this.penjualanSimpanView.getFirst().setEnabled(Boolean.TRUE);
            this.penjualanSimpanView.getPrevious().setEnabled(Boolean.TRUE);
        }

        if (pageNumber.equals(totalPage)) {
            this.penjualanSimpanView.getNext().setEnabled(Boolean.FALSE);
            this.penjualanSimpanView.getLast().setEnabled(Boolean.FALSE);
        } else {
            this.penjualanSimpanView.getNext().setEnabled(Boolean.TRUE);
            this.penjualanSimpanView.getLast().setEnabled(Boolean.TRUE);
        }

        this.penjualanSimpanView.getLabelPaging().setText("Page " + pageNumber + " of " + totalPage);

        barangAbstractTableModel = new BarangAbstractTableModel(App.barangService().ambilBarangs(pageNumber, rowsPerPage));
        this.penjualanSimpanView.getTabelBarang().setModel(barangAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanSimpanView.getTabelBarang());
        LOGGER.info("Paging : {}", pageNumber);
    }

    public void firstPaging() {
        pageNumber = 1;
        ambilDataBarang();
        LOGGER.info("Paging awal : {}", pageNumber);
    }

    public void PreviousPaging() {
        if (pageNumber > 1) {
            pageNumber -= 1;
            ambilDataBarang();
            LOGGER.info("Paging sebelum : {}", pageNumber);
        }
    }

    public void nextPaging() {
        if (pageNumber < totalPage) {
            pageNumber += 1;
            ambilDataBarang();
            LOGGER.info("Paging selanjutnya : {}", pageNumber);
        }
    }

    public void lastPaging() {
        pageNumber = totalPage;
        ambilDataBarang();
        LOGGER.info("Paging akhir : {}", pageNumber);
    }

    public void refresh() {
        ambilDataBarang();
        LOGGER.info("refresh paging : {}", pageNumber);
    }
    //end paging

}
