/**
 * Copyright (C) 2016 Rizki Mufrizal (https://rizkimufrizal.github.io/) (mufrizalrizki@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Pembelian;
import com.rizki.mufrizal.aplikasi.inventory.repository.PembelianRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 4:43:23 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class PembelianRepositoryImpl implements PembelianRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void simpanPembelian(Pembelian pembelian) {
        sessionFactory.getCurrentSession().save(pembelian);
    }

    @Override
    public Integer jumlahPembelian() {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Pembelian.class)
                .setCacheable(Boolean.TRUE)
                .list()
                .size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pembelian> ambilPembelians(Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Pembelian.class)
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE)
                .list();
    }

    @Override
    public Integer jumlahCariPembelian(String key, String value) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Pembelian.class)
                .add(Restrictions.like(key, "%" + value + "%"))
                .setCacheable(Boolean.TRUE)
                .list()
                .size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pembelian> cariPembelian(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Pembelian.class)
                .add(Restrictions.like(key, "%" + value + "%"))
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE)
                .list();
    }

}
