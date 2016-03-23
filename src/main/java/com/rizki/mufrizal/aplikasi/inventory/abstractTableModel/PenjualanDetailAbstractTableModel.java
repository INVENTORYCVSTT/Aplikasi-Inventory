package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel;

import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanDetail;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 22, 2016
 * @Time 9:36:05 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class PenjualanDetailAbstractTableModel extends AbstractTableModel {

    private List<PenjualanDetail> penjualanDetails = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "Kode Transaksi Penjualan Detail",
        "Nama Barang",
        "Harga Satuan Barang",
        "Jumlah Barang",
        "Total Harga Per Barang"
    };

    public PenjualanDetailAbstractTableModel(List<PenjualanDetail> penjualanDetails) {
        this.penjualanDetails = penjualanDetails;
    }

    @Override
    public int getRowCount() {
        return penjualanDetails.size();
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
        PenjualanDetail penjualanDetail = penjualanDetails.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return penjualanDetail.getKodeTransaksiPenjualanDetail();
            case 2:
                return penjualanDetail.getBarang().getNamaBarang();
            case 3:
                return "Rp " + penjualanDetail.getBarang().getHargaSatuanBarang();
            case 4:
                return penjualanDetail.getJumlahBarang();
            case 5:
                return "Rp " + penjualanDetail.getTotalHargaPerBarang();
            default:
                return null;
        }
    }
}
