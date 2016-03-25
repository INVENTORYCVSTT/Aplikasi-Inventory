package com.rizki.mufrizal.aplikasi.inventory.repository;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:52:36 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository
 *
 */
public interface BarangRepository {

    void simpanBarang(Barang barang);

    void editBarang(Barang barang);

    void hapusBarang(Barang barang);

    Integer jumlahBarang();

    List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage);

    Barang getBarang(String idBarang);

    Integer jumlahCariBarang(String key, String value);

    List<Barang> cariBarang(String key, String value, Integer pageNumber, Integer rowsPerPage);

    List<Barang> getSemuaBarang();
}
