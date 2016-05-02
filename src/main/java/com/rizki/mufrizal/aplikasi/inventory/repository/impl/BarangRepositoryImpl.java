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

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public Integer jumlahBarang() {
        return sessionFactory.getCurrentSession().createCriteria(Barang.class).list().size();
    }

    @SuppressWarnings("unchecked")
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
    public Integer jumlahCariBarang(String key, String value) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Barang.class)
                .add(Restrictions.like(key, "%" + value + "%"))
                .setCacheable(true)
                .list()
                .size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Barang> cariBarang(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Barang.class)
                .add(Restrictions.like(key, "%" + value + "%"))
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(true)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Barang> getSemuaBarang() {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Barang.class)
                .setCacheable(Boolean.TRUE)
                .list();
    }

}
