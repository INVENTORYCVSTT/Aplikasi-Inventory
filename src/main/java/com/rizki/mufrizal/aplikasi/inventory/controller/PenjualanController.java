package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.BarangAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.PenjualanSementaraAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.TableAutoResizeColumn;
import com.rizki.mufrizal.aplikasi.inventory.domain.JenisBarang;
import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanSementara;
import com.rizki.mufrizal.aplikasi.inventory.view.PenjualanSimpanView;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
    private PenjualanSementaraAbstractTableModel penjualanSementaraAbstractTableModel;
    private final TableAutoResizeColumn tableAutoResizeColumn = new TableAutoResizeColumn();

    private final List<PenjualanSementara> penjualanSementaras = new ArrayList<>();

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

    private PenjualanSementara checkContains(PenjualanSementara penjualanSementara1, List<PenjualanSementara> penjualanSementaras1) {
        Iterator<PenjualanSementara> iterator = penjualanSementaras1.iterator();
        while (iterator.hasNext()) {
            PenjualanSementara ps = iterator.next();
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
            penjualanSementara.setHargaSatuanBarang(BigDecimal.valueOf(Double.parseDouble(this.penjualanSimpanView.getTabelBarang().getValueAt(index, 5).toString())));

            PenjualanSementara ps = checkContains(penjualanSementara, penjualanSementaras);

            if (ps != null) {
                penjualanSementara.setJumlahBarang(ps.getJumlahBarang() + 1);
                int indexItem = penjualanSementaras.indexOf(ps);
                penjualanSementaras.set(indexItem, penjualanSementara);
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
        penjualanSementaraAbstractTableModel = new PenjualanSementaraAbstractTableModel(penjualanSementaras);
        this.penjualanSimpanView.getTabelPenjualanSementara().setModel(penjualanSementaraAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.penjualanSimpanView.getTabelPenjualanSementara());
    }

}
