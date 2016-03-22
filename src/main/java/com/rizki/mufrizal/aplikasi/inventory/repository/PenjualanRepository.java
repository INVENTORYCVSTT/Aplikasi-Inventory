package com.rizki.mufrizal.aplikasi.inventory.repository;

import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:52:54 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository
 *
 */
public interface PenjualanRepository {

    public void simpanPenjualan(Penjualan penjualan);

    public Integer jumlahPenjualan();

    public List<Penjualan> ambilPenjualans(Integer pageNumber, Integer rowsPerPage);

}
