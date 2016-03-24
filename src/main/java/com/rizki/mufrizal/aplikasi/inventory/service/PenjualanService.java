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

    public void simpanPenjualan(Penjualan penjualan);

    public Integer jumlahPenjualan();

    public List<Penjualan> ambilPenjualans(Integer pageNumber, Integer rowsPerPage);

    public Integer jumlahCariPenjualan(String value);

    public List<Penjualan> cariPenjualan(String value, Integer pageNumber, Integer rowsPerPage);

}
