package com.rizki.mufrizal.aplikasi.inventory.service;

import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanDetail;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 22, 2016
 * @Time 9:42:15 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service
 *
 */
public interface PenjualanDetailService {

    public List<PenjualanDetail> ambilPenjualanDetails(Integer pageNumber, Integer rowsPerPage, String kodeTransaksiPenjualan);
}
