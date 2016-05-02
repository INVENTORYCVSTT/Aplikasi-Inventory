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

import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianSementara;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 6:59:25 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.abstractTableModel
 *
 */
public class PembelianSementaraAbstractTableModel extends AbstractTableModel {

    private List<PembelianSementara> pembelianSementaras = new ArrayList<>();
    private static final String HEADER[] = {
        "No",
        "ID Barang",
        "Nama Barang",
        "Jenis Barang",
        "Tanggal Kadaluarsa",
        "Harga Satuan",
        "Jumlah Barang",
        "Total Harga"
    };

    public PembelianSementaraAbstractTableModel(List<PembelianSementara> pembelianSementaras) {
        this.pembelianSementaras = pembelianSementaras;
    }

    @Override
    public int getRowCount() {
        return pembelianSementaras.size();
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
        PembelianSementara pembelianSementara = pembelianSementaras.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                if (pembelianSementara.getIdBarang() == null) {
                    return "Barang Baru";
                } else {
                    return pembelianSementara.getIdBarang();
                }
            case 2:
                return pembelianSementara.getNamaBarang();
            case 3:
                return pembelianSementara.getJenisBarang();
            case 4:
                return pembelianSementara.getTanggalKadaluarsa();
            case 5:
                return "Rp " + pembelianSementara.getHargaSatuanBarang();
            case 6:
                return pembelianSementara.getJumlahBarang();
            case 7:
                return "Rp " + (pembelianSementara.getHargaSatuanBarang().intValue() * pembelianSementara.getJumlahBarang());
            default:
                return null;
        }
    }

}
