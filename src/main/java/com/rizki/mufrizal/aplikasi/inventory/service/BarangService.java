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
package com.rizki.mufrizal.aplikasi.inventory.service;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:41 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service
 *
 */
public interface BarangService {

    void simpanBarang(Barang barang);

    void editBarang(Barang barang);

    void hapusBarang(Barang barang);

    Integer jumlahBarang();

    List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage);

    Barang getBarang(String idBarang);

    Integer jumlahCariBarang(String key, String value);

    List<Barang> cariBarang(String key, String value, Integer pageNumber, Integer rowsPerPage);

    List<Barang> getSemuaBarang();

}
