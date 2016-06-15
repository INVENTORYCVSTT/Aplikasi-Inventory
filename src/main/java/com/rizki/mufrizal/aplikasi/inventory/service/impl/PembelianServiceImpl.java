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
package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Pembelian;
import com.rizki.mufrizal.aplikasi.inventory.repository.PembelianRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.PembelianService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 4:48:40 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("PembelianService")
@Transactional(readOnly = true)
public class PembelianServiceImpl implements PembelianService {

    @Autowired
    private PembelianRepository pembelianRepository;

    @Transactional
    @Override
    public void simpanPembelian(Pembelian pembelian) {
        pembelianRepository.simpanPembelian(pembelian);
    }

    @Override
    public Integer jumlahPembelian() {
        return pembelianRepository.jumlahPembelian();
    }

    @Override
    public List<Pembelian> ambilPembelians(Integer pageNumber, Integer rowsPerPage) {
        return pembelianRepository.ambilPembelians(pageNumber, rowsPerPage);
    }

    @Override
    public Integer jumlahCariPembelian(String key, String value) {
        return pembelianRepository.jumlahCariPembelian(key, value);
    }

    @Override
    public List<Pembelian> cariPembelian(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return pembelianRepository.cariPembelian(key, value, pageNumber, rowsPerPage);
    }

}
