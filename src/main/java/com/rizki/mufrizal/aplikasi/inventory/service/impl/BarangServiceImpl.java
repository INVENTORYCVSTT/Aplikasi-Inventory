package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.BarangService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "barang")
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepository;

    @CacheEvict(value = "barang", key = "#buku", allEntries = true)
    @Transactional
    @Override
    public void simpanBarang(Barang barang) {
        barangRepository.simpanBarang(barang);
    }

    @CacheEvict(key = "#barang", allEntries = true)
    @Transactional
    @Override
    public void editBarang(Barang barang) {
        barangRepository.editBarang(barang);
    }

    @CacheEvict(key = "#barang", allEntries = true)
    @Transactional
    @Override
    public void hapusBarang(Barang barang) {
        barangRepository.hapusBarang(barang);
    }

    @Override
    public Integer jumlahBarang() {
        return barangRepository.jumlahBarang();
    }

    @Cacheable(key = "#pageNumber")
    @Override
    public List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage) {
        return barangRepository.ambilBarangs(pageNumber, rowsPerPage);
    }

    @Cacheable(key = "#idBarang")
    @Override
    public Barang getBarang(String idBarang) {
        return barangRepository.getBarang(idBarang);
    }

    @Cacheable
    @Override
    public Integer jumlahCariBarang(String key, String value) {
        return barangRepository.jumlahCariBarang(key, value);
    }

    @Cacheable
    @Override
    public List<Barang> cariBarang(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return barangRepository.cariBarang(key, value, pageNumber, rowsPerPage);
    }

    @Cacheable
    @Override
    public List<Barang> getSemuaBarang() {
        return barangRepository.getSemuaBarang();
    }

}
