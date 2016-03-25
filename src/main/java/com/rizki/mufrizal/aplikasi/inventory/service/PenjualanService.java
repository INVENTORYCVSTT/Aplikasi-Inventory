package com.rizki.mufrizal.aplikasi.inventory.service;

import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:48 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service
 *
 */
public interface PenjualanService {

    void simpanPenjualan(Penjualan penjualan);

    Integer jumlahPenjualan();

    List<Penjualan> ambilPenjualans(Integer pageNumber, Integer rowsPerPage);

    Integer jumlahCariPenjualan(String key, String value);

    List<Penjualan> cariPenjualan(String key, String value, Integer pageNumber, Integer rowsPerPage);

}
