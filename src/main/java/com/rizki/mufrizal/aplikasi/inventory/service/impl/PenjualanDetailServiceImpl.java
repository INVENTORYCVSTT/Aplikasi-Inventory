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

import com.rizki.mufrizal.aplikasi.inventory.domain.PenjualanDetail;
import com.rizki.mufrizal.aplikasi.inventory.repository.PenjualanDetailRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.PenjualanDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 22, 2016
 * @Time 10:14:53 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("PenjualanDetailService")
@Transactional
public class PenjualanDetailServiceImpl implements PenjualanDetailService {

    @Autowired
    private PenjualanDetailRepository penjualanDetailRepository;

    @Override
    public List<PenjualanDetail> ambilPenjualanDetails(String kodeTransaksiPenjualan) {
        return penjualanDetailRepository.ambilPenjualanDetails(kodeTransaksiPenjualan);
    }

}
