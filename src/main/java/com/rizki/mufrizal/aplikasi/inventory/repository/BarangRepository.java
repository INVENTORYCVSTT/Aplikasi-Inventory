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

    public void simpanBarang(Barang barang);

    public void editBarang(Barang barang);

    public void hapusBarang(Barang barang);

    public Integer jumlahBarang();

    public List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage);

    public Barang getBarang(String idBarang);

    public Integer jumlahCariBarang(String key, String value);

    public List<Barang> cariBarang(String key, String value, Integer pageNumber, Integer rowsPerPage);

}
