package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Penjualan;
import com.rizki.mufrizal.aplikasi.inventory.repository.PenjualanRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PenjualanRepositoryImpl.class);

    @Override
    public void simpanPenjualan(Penjualan penjualan) {
        sessionFactory.getCurrentSession().save(penjualan);
    }

    @Override
    public Integer jumlahPenjualan() {
        return sessionFactory.getCurrentSession().createCriteria(Penjualan.class).list().size();
    }

    @Override
    public List<Penjualan> ambilPenjualans(Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Penjualan.class)
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE)
                .list();
    }

    @Override
    public Integer jumlahCariPenjualan(String key, String value) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Penjualan.class)
                .add(Restrictions.like(key, "%" + value + "%"))
                .setCacheable(true)
                .list()
                .size();
    }

    @Override
    public List<Penjualan> cariPenjualan(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Penjualan.class)
                .add(Restrictions.like(key, "%" + value + "%"))
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(true)
                .list();
    }

}
