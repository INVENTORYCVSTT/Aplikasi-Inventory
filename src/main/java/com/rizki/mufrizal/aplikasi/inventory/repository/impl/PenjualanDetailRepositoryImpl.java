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

    @Override
    public List<PenjualanDetail> ambilPenjualanDetails(Integer pageNumber, Integer rowsPerPage, String kodeTransaksiPenjualan) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(PenjualanDetail.class)
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE)
                .list();
    }

}
