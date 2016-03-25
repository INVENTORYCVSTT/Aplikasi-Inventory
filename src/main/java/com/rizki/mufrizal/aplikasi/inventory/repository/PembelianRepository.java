package com.rizki.mufrizal.aplikasi.inventory.repository;

import com.rizki.mufrizal.aplikasi.inventory.domain.Pembelian;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 4:42:13 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository
 *
 */
public interface PembelianRepository {

    void simpanPembelian(Pembelian pembelian);

    Integer jumlahPembelian();

    List<Pembelian> ambilPembelians(Integer pageNumber, Integer rowsPerPage);

    Integer jumlahCariPembelian(String key, String value);

    List<Pembelian> cariPembelian(String key, String value, Integer pageNumber, Integer rowsPerPage);

}
