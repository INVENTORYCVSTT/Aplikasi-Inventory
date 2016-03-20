package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 20, 2016
 * @Time 1:26:12 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class BarangAbstractTableModel extends AbstractTableModel {

    private List<Barang> barangs = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "ID Barang",
        "Nama Barang",
        "Jenis Barang",
        "Tanggal Kadaluarsa",
        "Harga Satuan",
        "Jumlah"
    };

    public BarangAbstractTableModel(List<Barang> barangs) {
        this.barangs = barangs;
    }

    @Override
    public int getRowCount() {
        return barangs.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang barang = barangs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return barang.getIdBarang();
            case 2:
                return barang.getNamaBarang();
            case 3:
                return barang.getJenisBarang();
            case 4:
                return barang.getTanggalKadaluarsa();
            case 5:
                return barang.getHargaSatuanBarang();
            case 6:
                return barang.getJumlahBarang();
            default:
                return null;
        }
    }

}
