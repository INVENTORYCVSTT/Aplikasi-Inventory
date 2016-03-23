package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanDetail;
import com.rizki.mufrizal.aplikasi.inventory.repository.PenjualanDetailRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.PenjualanDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 22, 2016
 * @Time 10:14:53 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("PenjualanDetailService")
@Transactional
public class PenjualanDetailServiceImpl implements PenjualanDetailService {

    @Autowired
    private PenjualanDetailRepository penjualanDetailRepository;

    @Override
    public List<PenjualanDetail> ambilPenjualanDetails(String kodeTransaksiPenjualan) {
        return penjualanDetailRepository.ambilPenjualanDetails(kodeTransaksiPenjualan);
    }

}
