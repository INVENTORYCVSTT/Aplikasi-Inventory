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

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.BarangService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:55:09 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("BarangService")
@Transactional(readOnly = true)
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepository;

    @Transactional
    @Override
    public void simpanBarang(Barang barang) {
        barangRepository.simpanBarang(barang);
    }

    @Transactional
    @Override
    public void editBarang(Barang barang) {
        barangRepository.editBarang(barang);
    }

    @Transactional
    @Override
    public void hapusBarang(Barang barang) {
        barangRepository.hapusBarang(barang);
    }

    @Override
    public Integer jumlahBarang() {
        return barangRepository.jumlahBarang();
    }

    @Override
    public List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage) {
        return barangRepository.ambilBarangs(pageNumber, rowsPerPage);
    }

    @Override
    public Barang getBarang(String idBarang) {
        return barangRepository.getBarang(idBarang);
    }

    @Override
    public Integer jumlahCariBarang(String key, String value) {
        return barangRepository.jumlahCariBarang(key, value);
    }

    @Override
    public List<Barang> cariBarang(String key, String value, Integer pageNumber, Integer rowsPerPage) {
        return barangRepository.cariBarang(key, value, pageNumber, rowsPerPage);
    }

    @Override
    public List<Barang> getSemuaBarang() {
        return barangRepository.getSemuaBarang();
    }

}
