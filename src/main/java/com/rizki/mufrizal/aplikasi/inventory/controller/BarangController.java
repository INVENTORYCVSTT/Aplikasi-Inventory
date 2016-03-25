package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.BarangAbstractTableModel;
import com.rizki.mufrizal.aplikasi.inventory.abstractTableModel.TableAutoResizeColumn;
import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.domain.JenisBarang;
import com.rizki.mufrizal.aplikasi.inventory.view.BarangView;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:56:00 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.controller
 *
 */
public class BarangController {

    private final BarangView barangView;
    private static final Logger LOGGER = LoggerFactory.getLogger(BarangController.class);
    private BarangAbstractTableModel barangAbstractTableModel;
    private final TableAutoResizeColumn tableAutoResizeColumn = new TableAutoResizeColumn();

    public BarangController(BarangView barangView) {
        this.barangView = barangView;
    }

    // paging
    private Integer totalRows = 0;
    private Integer pageNumber = 1;
    private Integer totalPage = 1;
    private Integer rowsPerPage = 10;

    public void ambilDataBarang() {
        LOGGER.info("Ambil data barang");
        rowsPerPage = Integer.valueOf(this.barangView.getPerPage().getSelectedItem().toString());
        totalRows = App.barangService().jumlahBarang();
        Double dbTotalPage = Math.ceil(totalRows.doubleValue() / rowsPerPage.doubleValue());
        totalPage = dbTotalPage.intValue();

        if (pageNumber == 1) {
            this.barangView.getFirst().setEnabled(Boolean.FALSE);
            this.barangView.getPrevious().setEnabled(Boolean.FALSE);
        } else {
            this.barangView.getFirst().setEnabled(Boolean.TRUE);
            this.barangView.getPrevious().setEnabled(Boolean.TRUE);
        }

        if (pageNumber.equals(totalPage)) {
            this.barangView.getNext().setEnabled(Boolean.FALSE);
            this.barangView.getLast().setEnabled(Boolean.FALSE);
        } else {
            this.barangView.getNext().setEnabled(Boolean.TRUE);
            this.barangView.getLast().setEnabled(Boolean.TRUE);
        }

        this.barangView.getLabelPaging().setText("Page " + pageNumber + " of " + totalPage);
        this.barangView.getLabelTotalRecord().setText("Total Record " + totalRows);

        barangAbstractTableModel = new BarangAbstractTableModel(App.barangService().ambilBarangs(pageNumber, rowsPerPage));
        this.barangView.getTabelBarang().setModel(barangAbstractTableModel);
        tableAutoResizeColumn.autoResizeColumn(this.barangView.getTabelBarang());
        LOGGER.info("Paging : {}", pageNumber);
    }

    public void firstPaging() {
        if (this.barangView.getValue().getText().isEmpty()) {
            ambilDataBarang();
        } else {
            cariDataBarang();
        }
        pageNumber = 1;

        LOGGER.info("Paging awal : {}", pageNumber);
    }

    public void PreviousPaging() {
        if (pageNumber > 1) {
            pageNumber -= 1;
            if (this.barangView.getValue().getText().isEmpty()) {
                ambilDataBarang();
            } else {
                cariDataBarang();
            }
            LOGGER.info("Paging sebelum : {}", pageNumber);
        }
    }

    public void nextPaging() {
        if (pageNumber < totalPage) {
            pageNumber += 1;
            if (this.barangView.getValue().getText().isEmpty()) {
                ambilDataBarang();
            } else {
                cariDataBarang();
            }
            LOGGER.info("Paging selanjutnya : {}", pageNumber);
        }
    }

    public void lastPaging() {
        pageNumber = totalPage;
        if (this.barangView.getValue().getText().isEmpty()) {
            ambilDataBarang();
        } else {
            cariDataBarang();
        }
        LOGGER.info("Paging akhir : {}", pageNumber);
    }

    public void refresh() {
        totalRows = 0;
        pageNumber = 1;
        totalPage = 1;
        rowsPerPage = 10;
        ambilDataBarang();
        LOGGER.info("refresh paging : {}", pageNumber);
    }
    // end paging

    public void tampilkanBarang() {
        try {
            Integer index = this.barangView.getTabelBarang().getSelectedRow();
            this.barangView.getIdBarang()
                    .setText(String.valueOf(this.barangView.getTabelBarang().getValueAt(index, 1)));
            this.barangView.getNamaBarang()
                    .setText(String.valueOf(this.barangView.getTabelBarang().getValueAt(index, 2)));
            this.barangView.getJenisBarang().setSelectedItem(
                    String.valueOf(this.barangView.getTabelBarang().getValueAt(index, 3)));
            java.util.Date tanggal = new SimpleDateFormat("yyyy-MM-d")
                    .parse(String.valueOf(this.barangView.getTabelBarang().getValueAt(index, 4)));
            this.barangView.getTanggalKadaluarsa().setDate(tanggal);

            String hargaSatuanBarang
                    = this.barangView.getTabelBarang().getValueAt(index, 5).toString();
            this.barangView.getHargaSatuan().setText(hargaSatuanBarang.split(" ")[1]);
            this.barangView.getJumlahBarang()
                    .setText(String.valueOf(this.barangView.getTabelBarang().getValueAt(index, 6)));
        } catch (ParseException ex) {
            LOGGER.error("error di : {}", ex);
        }
    }

    public void clear() {
        this.barangView.getIdBarang().setText(null);
        this.barangView.getNamaBarang().setText(null);
        this.barangView.getJenisBarang().setSelectedIndex(0);
        this.barangView.getTanggalKadaluarsa().setDate(null);
        this.barangView.getHargaSatuan().setText(null);
        this.barangView.getJumlahBarang().setText(null);
    }

    public void simpanBarang() {
        if (this.barangView.getNamaBarang().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama barang belum diisi", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (this.barangView.getTanggalKadaluarsa().getDate() == null) {
            JOptionPane.showMessageDialog(null, "tanggal belum dipilih", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (this.barangView.getHargaSatuan().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harga satuan barang belum diisi", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (this.barangView.getJumlahBarang().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jumlah barang belum diisi", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Barang barang = new Barang();
            barang.setNamaBarang(this.barangView.getNamaBarang().getText());
            barang.setJenisBarang(
                    JenisBarang.valueOf(this.barangView.getJenisBarang().getSelectedItem().toString()));
            barang.setTanggalKadaluarsa(this.barangView.getTanggalKadaluarsa().getDate());
            barang.setHargaSatuanBarang(
                    BigDecimal.valueOf(Long.parseLong(this.barangView.getHargaSatuan().getText())));
            barang.setJumlahBarang(Integer.parseInt(this.barangView.getJumlahBarang().getText()));

            App.barangService().simpanBarang(barang);

            LOGGER.debug("prosess simpan barang : {}", barang);

            JOptionPane.showMessageDialog(null, "Data barang berhasil disimpan", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            ambilDataBarang();
            clear();
            disableComponent();
        }
    }

    public void editBarang() {
        if (this.barangView.getNamaBarang().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama barang belum diisi", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (this.barangView.getTanggalKadaluarsa().getDate() == null) {
            JOptionPane.showMessageDialog(null, "tanggal belum dipilih", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (this.barangView.getHargaSatuan().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harga satuan barang belum diisi", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (this.barangView.getJumlahBarang().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jumlah barang belum diisi", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Barang barang = new Barang();
            barang.setIdBarang(this.barangView.getIdBarang().getText());
            barang.setNamaBarang(this.barangView.getNamaBarang().getText());
            barang.setJenisBarang(
                    JenisBarang.valueOf(this.barangView.getJenisBarang().getSelectedItem().toString()));
            barang.setTanggalKadaluarsa(this.barangView.getTanggalKadaluarsa().getDate());
            barang.setHargaSatuanBarang(
                    BigDecimal.valueOf(Double.valueOf(this.barangView.getHargaSatuan().getText())));
            barang.setJumlahBarang(Integer.parseInt(this.barangView.getJumlahBarang().getText()));

            App.barangService().editBarang(barang);

            LOGGER.debug("prosess edit barang : {}", barang);

            JOptionPane.showMessageDialog(null, "Data barang berhasil diedit", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            ambilDataBarang();
            clear();
            disableComponent();
        }
    }

    public void hapusBarang() {
        if (this.barangView.getIdBarang().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Barang belum dipilih", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Barang barang = new Barang();
            barang.setIdBarang(this.barangView.getIdBarang().getText());
            barang.setNamaBarang(this.barangView.getNamaBarang().getText());
            barang.setJenisBarang(
                    JenisBarang.valueOf(this.barangView.getJenisBarang().getSelectedItem().toString()));
            barang.setTanggalKadaluarsa(this.barangView.getTanggalKadaluarsa().getDate());
            barang.setHargaSatuanBarang(
                    BigDecimal.valueOf(Double.valueOf(this.barangView.getHargaSatuan().getText())));
            barang.setJumlahBarang(Integer.parseInt(this.barangView.getJumlahBarang().getText()));

            int pilih = JOptionPane.showConfirmDialog(null, "Apakah data ingin dihapus ?",
                    "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (pilih == JOptionPane.YES_OPTION) {

                App.barangService().hapusBarang(barang);
                LOGGER.debug("prosess hapus barang : {}", barang);

                JOptionPane.showMessageDialog(null, "Data barang berhasil dihapus", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                ambilDataBarang();
                clear();
            }
        }
    }

    // disable or enable
    public void disableComponent() {
        this.barangView.getTambah().setEnabled(Boolean.TRUE);
        this.barangView.getHapus().setEnabled(Boolean.TRUE);
        this.barangView.getEdit().setEnabled(Boolean.TRUE);

        this.barangView.getNamaBarang().setEnabled(Boolean.FALSE);
        this.barangView.getJenisBarang().setEnabled(Boolean.FALSE);
        this.barangView.getTanggalKadaluarsa().setEnabled(Boolean.FALSE);
        this.barangView.getHargaSatuan().setEnabled(Boolean.FALSE);
        this.barangView.getJumlahBarang().setEnabled(Boolean.FALSE);
        this.barangView.getSimpan().setEnabled(Boolean.FALSE);
        this.barangView.getUpdate().setEnabled(Boolean.FALSE);
        this.barangView.getCancel().setEnabled(Boolean.FALSE);
    }

    public void disableAdd() {
        this.barangView.getSimpan().setEnabled(Boolean.TRUE);
        this.barangView.getNamaBarang().setEnabled(Boolean.TRUE);
        this.barangView.getJenisBarang().setEnabled(Boolean.TRUE);
        this.barangView.getTanggalKadaluarsa().setEnabled(Boolean.TRUE);
        this.barangView.getHargaSatuan().setEnabled(Boolean.TRUE);
        this.barangView.getJumlahBarang().setEnabled(Boolean.TRUE);
        this.barangView.getCancel().setEnabled(Boolean.TRUE);

        this.barangView.getTambah().setEnabled(Boolean.FALSE);
        this.barangView.getUpdate().setEnabled(Boolean.FALSE);
        this.barangView.getHapus().setEnabled(Boolean.FALSE);
        this.barangView.getEdit().setEnabled(Boolean.FALSE);
    }

    public void disableEdit() {
        this.barangView.getUpdate().setEnabled(Boolean.TRUE);
        this.barangView.getNamaBarang().setEnabled(Boolean.TRUE);
        this.barangView.getJenisBarang().setEnabled(Boolean.TRUE);
        this.barangView.getTanggalKadaluarsa().setEnabled(Boolean.TRUE);
        this.barangView.getHargaSatuan().setEnabled(Boolean.TRUE);
        this.barangView.getJumlahBarang().setEnabled(Boolean.TRUE);
        this.barangView.getCancel().setEnabled(Boolean.TRUE);

        this.barangView.getTambah().setEnabled(Boolean.FALSE);
        this.barangView.getSimpan().setEnabled(Boolean.FALSE);
        this.barangView.getHapus().setEnabled(Boolean.FALSE);
        this.barangView.getEdit().setEnabled(Boolean.FALSE);
    }
    // end disable or enable

    // cari data barang
    public void cariDataBarang() {
        totalRows = 0;
        pageNumber = 1;
        totalPage = 1;
        rowsPerPage = 10;

        if (this.barangView.getValue().getText().isEmpty()) {
            ambilDataBarang();
        } else {

            String value = this.barangView.getValue().getText();
            String key = null;

            if (this.barangView.getKey().getSelectedIndex() == 0) {
                key = "idBarang";
            } else if (this.barangView.getKey().getSelectedIndex() == 1) {
                key = "namaBarang";
            }

            LOGGER.info("cari data barang");
            rowsPerPage
                    = Integer.valueOf(this.barangView.getPerPage().getSelectedItem().toString());
            totalRows = App.barangService().jumlahCariBarang(key, value);
            Double dbTotalPage = Math.ceil(totalRows.doubleValue() / rowsPerPage.doubleValue());
            totalPage = dbTotalPage.intValue();

            if (pageNumber == 1) {
                this.barangView.getFirst().setEnabled(Boolean.FALSE);
                this.barangView.getPrevious().setEnabled(Boolean.FALSE);
            } else {
                this.barangView.getFirst().setEnabled(Boolean.TRUE);
                this.barangView.getPrevious().setEnabled(Boolean.TRUE);
            }

            if (pageNumber.equals(totalPage)) {
                this.barangView.getNext().setEnabled(Boolean.FALSE);
                this.barangView.getLast().setEnabled(Boolean.FALSE);
            } else {
                this.barangView.getNext().setEnabled(Boolean.TRUE);
                this.barangView.getLast().setEnabled(Boolean.TRUE);
            }

            this.barangView.getLabelPaging().setText("Page " + pageNumber + " of " + totalPage);
            this.barangView.getLabelTotalRecord().setText("Total Record " + totalRows);

            barangAbstractTableModel = new BarangAbstractTableModel(
                    App.barangService().cariBarang(key, value, pageNumber, rowsPerPage));
            this.barangView.getTabelBarang().setModel(barangAbstractTableModel);
            tableAutoResizeColumn.autoResizeColumn(this.barangView.getTabelBarang());
            LOGGER.info("Paging : {}", pageNumber);
        }

    }
    // end cari data barang
}
