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

import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianDetail;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 4:51:21 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class PembelianDetailAbstractTableModel extends AbstractTableModel {

    private List<PembelianDetail> pembelianDetails = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "Kode Transaksi Pembelian Detail",
        "Nama Barang",
        "Harga Satuan Barang",
        "Jumlah Barang",
        "Total Harga Per Barang"
    };

    public PembelianDetailAbstractTableModel(List<PembelianDetail> pembelianDetails) {
        this.pembelianDetails = pembelianDetails;
    }

    @Override
    public int getRowCount() {
        return pembelianDetails.size();
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
        PembelianDetail pembelianDetail = pembelianDetails.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return pembelianDetail.getKodeTransaksiPembelianDetail();
            case 2:
                return pembelianDetail.getBarang().getNamaBarang();
            case 3:
                return "Rp " + pembelianDetail.getBarang().getHargaSatuanBarang();
            case 4:
                return pembelianDetail.getJumlahBarang();
            case 5:
                return "Rp " + pembelianDetail.getTotalHargaPerBarang();
            default:
                return null;
        }
    }

}
