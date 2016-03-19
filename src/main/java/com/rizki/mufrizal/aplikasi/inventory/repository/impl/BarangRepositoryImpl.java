package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:00 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class BarangRepositoryImpl implements BarangRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void simpanBarang(Barang barang) {
        sessionFactory.getCurrentSession().save(barang);
    }

    @Override
    public void editBarang(Barang barang) {
        sessionFactory.getCurrentSession().update(barang);
    }

    @Override
    public void hapusBarang(Barang barang) {
        sessionFactory.getCurrentSession().delete(barang);
    }

    @Override
    public List<Barang> ambilBarangs() {
        return sessionFactory.getCurrentSession().createCriteria(Barang.class).list();
    }

}