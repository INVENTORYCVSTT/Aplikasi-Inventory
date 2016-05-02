/**
 * Copyright (C) 2016 Rizki Mufrizal (https://rizkimufrizal.github.io/) (mufrizalrizki@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel;

import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 20, 2016
 * @Time 1:26:45 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class PenjualanAbstractTableModel extends AbstractTableModel {

    private List<Penjualan> penjualans = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "Kode Transaksi Penjualan",
        "Tanggal Transaksi",
        "Total Harga",
        "Nama Pembeli"
    };

    public PenjualanAbstractTableModel(List<Penjualan> penjualans) {
        this.penjualans = penjualans;
    }

    @Override
    public int getRowCount() {
        return penjualans.size();
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
        Penjualan penjualan = penjualans.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return penjualan.getKodeTransaksiPenjualan();
            case 2:
                return penjualan.getTanggalTransaksi();
            case 3:
                return "Rp " + penjualan.getTotalHarga();
            case 4:
                return penjualan.getNamaPembeli();
            default:
                return null;
        }
    }

}
