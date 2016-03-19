package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import com.rizki.mufrizal.aplikasi.inventory.repository.PenjualanRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:11 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class PenjualanRepositoryImpl implements PenjualanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void simpanPenjualan(Penjualan penjualan) {
        sessionFactory.getCurrentSession().save(penjualan);
    }

    @Override
    public void editPenjualan(Penjualan penjualan) {
        sessionFactory.getCurrentSession().update(penjualan);
    }

    @Override
    public void hapusPenjualan(Penjualan penjualan) {
        sessionFactory.getCurrentSession().delete(penjualan);
    }

    @Override
    public List<Penjualan> ambilPenjualans() {
        return sessionFactory.getCurrentSession().createCriteria(Penjualan.class).list();
    }

}
