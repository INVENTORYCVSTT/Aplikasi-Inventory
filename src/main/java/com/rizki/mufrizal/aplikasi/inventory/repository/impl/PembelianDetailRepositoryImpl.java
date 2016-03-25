package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.PembelianDetail;
import com.rizki.mufrizal.aplikasi.inventory.repository.PembelianDetailRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 5:24:21 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class PembelianDetailRepositoryImpl implements PembelianDetailRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<PembelianDetail> ambilPembelianDetails(String kodeTransaksiPembelian) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select pd from PembelianDetail pd left join fetch pd.pembelian p left join fetch pd.barang where p.kodeTransaksiPembelian = :kodeTransaksiPembelian")
                .setParameter("kodeTransaksiPembelian", kodeTransaksiPembelian)
                .setCacheable(Boolean.TRUE)
                .list();
    }

}
