package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanDetail;
import com.rizki.mufrizal.aplikasi.inventory.repository.PenjualanDetailRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 22, 2016
 * @Time 9:41:43 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class PenjualanDetailRepositoryImpl implements PenjualanDetailRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<PenjualanDetail> ambilPenjualanDetails(String kodeTransaksiPenjualan) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select pd from PenjualanDetail pd left join fetch pd.penjualan p left join fetch pd.barang where p.kodeTransaksiPenjualan = :kodeTransaksiPenjualan")
                .setParameter("kodeTransaksiPenjualan", kodeTransaksiPenjualan)
                .setCacheable(Boolean.TRUE)
                .list();
    }

}
