package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel;

import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanSementara;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 20, 2016
 * @Time 9:29:20 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class PenjualanSementaraAbstractTableModel extends AbstractTableModel {

    private List<PenjualanSementara> penjualanSementaras = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "ID Barang",
        "Nama Barang",
        "Jenis Barang",
        "Tanggal Kadaluarsa",
        "Harga Satuan",
        "Jumlah Barang"
    };

    public PenjualanSementaraAbstractTableModel(List<PenjualanSementara> penjualanSementaras) {
        this.penjualanSementaras = penjualanSementaras;
    }

    @Override
    public int getRowCount() {
        return penjualanSementaras.size();
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
        PenjualanSementara penjualanSementara = penjualanSementaras.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return penjualanSementara.getIdBarang();
            case 2:
                return penjualanSementara.getNamaBarang();
            case 3:
                return penjualanSementara.getJenisBarang();
            case 4:
                return penjualanSementara.getTanggalKadaluarsa();
            case 5:
                return penjualanSementara.getHargaSatuanBarang();
            case 6:
                return penjualanSementara.getJumlahBarang();
            default:
                return null;
        }
    }

}
