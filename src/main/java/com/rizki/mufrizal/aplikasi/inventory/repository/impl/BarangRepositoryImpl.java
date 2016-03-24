package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BarangRepositoryImpl.class);

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
    public Integer jumlahBarang() {
        return sessionFactory.getCurrentSession().createCriteria(Barang.class).list().size();
    }

    @Override
    public List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Barang.class)
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE)
                .list();
    }

    @Override
    public Barang getBarang(String idBarang) {
        return sessionFactory.getCurrentSession().get(Barang.class, idBarang);
    }

    @Override
    public Integer jumlahCariBarang(String value) {
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery("from Barang b where b.")
//                .list()
//                .size();
        return null;
    }

    @Override
    public List<Barang> cariBarang(String value, Integer pageNumber, Integer rowsPerPage) {
        return null;
    }

}
