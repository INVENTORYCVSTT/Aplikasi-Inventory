package com.rizki.mufrizal.aplikasi.inventory.service;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:41 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service
 *
 */
public interface BarangService {

    public void simpanBarang(Barang barang);

    public void editBarang(Barang barang);

    public void hapusBarang(Barang barang);

    public Integer jumlahBarang();

    public List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage);

    public Barang getBarang(String idBarang);
}
