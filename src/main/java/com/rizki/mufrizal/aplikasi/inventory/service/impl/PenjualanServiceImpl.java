package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import com.rizki.mufrizal.aplikasi.inventory.repository.PenjualanRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.PenjualanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:55:21 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("PenjualanService")
@Transactional(readOnly = true)
public class PenjualanServiceImpl implements PenjualanService {

    @Autowired
    private PenjualanRepository penjualanRepository;

    @Transactional
    @Override
    public void simpanPenjualan(Penjualan penjualan) {
        penjualanRepository.simpanPenjualan(penjualan);
    }

    @Override
    public Integer jumlahPenjualan() {
        return penjualanRepository.jumlahPenjualan();
    }

    @Override
    public List<Penjualan> ambilPenjualans(Integer pageNumber, Integer rowsPerPage) {
        return penjualanRepository.ambilPenjualans(pageNumber, rowsPerPage);
    }

    @Override
    public Integer jumlahCariPenjualan(String value) {
        return penjualanRepository.jumlahCariPenjualan(value);
    }

    @Override
    public List<Penjualan> cariPenjualan(String value, Integer pageNumber, Integer rowsPerPage) {
        return penjualanRepository.cariPenjualan(value, pageNumber, rowsPerPage);
    }

}
