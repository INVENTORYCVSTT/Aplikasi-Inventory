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
