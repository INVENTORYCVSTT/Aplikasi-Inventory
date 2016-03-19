package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.BarangService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:55:09 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("BarangService")
@Transactional(readOnly = true)
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepository;

    @Transactional
    @Override
    public void simpanBarang(Barang barang) {
        barangRepository.simpanBarang(barang);
    }

    @Transactional
    @Override
    public void editBarang(Barang barang) {
        barangRepository.editBarang(barang);
    }

    @Transactional
    @Override
    public void hapusBarang(Barang barang) {
        barangRepository.hapusBarang(barang);
    }

    @Override
    public List<Barang> ambilBarangs() {
        return barangRepository.ambilBarangs();
    }

}