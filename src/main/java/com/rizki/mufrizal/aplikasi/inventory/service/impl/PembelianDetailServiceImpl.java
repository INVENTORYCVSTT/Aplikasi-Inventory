package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianDetail;
import com.rizki.mufrizal.aplikasi.inventory.repository.PembelianDetailRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.PembelianDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 5:27:06 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("PembelianDetailService")
@Transactional(readOnly = true)
public class PembelianDetailServiceImpl implements PembelianDetailService {

    @Autowired
    private PembelianDetailRepository pembelianDetailRepository;

    @Override
    public List<PembelianDetail> ambilPembelianDetails(String kodeTransaksiPembelian) {
        return pembelianDetailRepository.ambilPembelianDetails(kodeTransaksiPembelian);
    }

}
