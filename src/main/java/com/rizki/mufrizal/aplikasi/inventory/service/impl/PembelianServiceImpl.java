package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Pembelian;
import com.rizki.mufrizal.aplikasi.inventory.repository.PembelianRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.PembelianService;
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
 * @Since Mar 25, 2016
 * @Time 4:48:40 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("PembelianService")
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "barang")
public class PembelianServiceImpl implements PembelianService {

    @Autowired
    private PembelianRepository pembelianRepository;

    @CacheEvict(key = "#pembelian", allEntries = true)
    @Transactional
    @Override
    public void simpanPembelian(Pembelian pembelian) {
        pembelianRepository.simpanPembelian(pembelian);
    }

    @Cacheable
    @Override
    public Integer jumlahPembelian() {
        return pembelianRepository.jumlahPembelian();
    }

    @Cacheable(key = "#pageNumber")
    @Override
    public List<Pembelian> ambilPembelians(Integer pageNumber, Integer rowsPerPage) {
        return pembelianRepository.ambilPembelians(pageNumber, rowsPerPage);
    }

    @Cacheable
    @Override
    public Integer jumlahCariPembelian(String key, String value) {
        return pembelianRepository.jumlahCariPembelian(key, value);
    }

    @Cacheable
    @Override
    public List<Pembelian> cariPembelian(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return pembelianRepository.cariPembelian(key, value, pageNumber, rowsPerPage);
    }

}
