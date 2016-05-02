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
