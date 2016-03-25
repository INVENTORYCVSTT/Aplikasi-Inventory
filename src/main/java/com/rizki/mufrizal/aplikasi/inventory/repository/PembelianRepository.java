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

    public void simpanPembelian(Pembelian pembelian);

    public Integer jumlahPembelian();

    public List<Pembelian> ambilPembelians(Integer pageNumber, Integer rowsPerPage);

    public Integer jumlahCariPembelian(String key, String value);

    public List<Pembelian> cariPembelian(String key, String value, Integer pageNumber, Integer rowsPerPage);

}
