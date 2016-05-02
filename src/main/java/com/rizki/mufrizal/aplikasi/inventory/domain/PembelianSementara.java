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
package com.rizki.mufrizal.aplikasi.inventory.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 25, 2016
 * @Time 6:55:52 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.domain
 *
 */
public class PembelianSementara {

    private String idBarang;

    private String namaBarang;

    private JenisBarang jenisBarang;

    private Integer jumlahBarang;

    private Date tanggalKadaluarsa;

    private BigDecimal hargaSatuanBarang;

    /**
     * @return the idBarang
     */
    public String getIdBarang() {
        return idBarang;
    }

    /**
     * @param idBarang the idBarang to set
     */
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    /**
     * @return the namaBarang
     */
    public String getNamaBarang() {
        return namaBarang;
    }

    /**
     * @param namaBarang the namaBarang to set
     */
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    /**
     * @return the jenisBarang
     */
    public JenisBarang getJenisBarang() {
        return jenisBarang;
    }

    /**
     * @param jenisBarang the jenisBarang to set
     */
    public void setJenisBarang(JenisBarang jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    /**
     * @return the jumlahBarang
     */
    public Integer getJumlahBarang() {
        return jumlahBarang;
    }

    /**
     * @param jumlahBarang the jumlahBarang to set
     */
    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    /**
     * @return the tanggalKadaluarsa
     */
    public Date getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    /**
     * @param tanggalKadaluarsa the tanggalKadaluarsa to set
     */
    public void setTanggalKadaluarsa(Date tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    /**
     * @return the hargaSatuanBarang
     */
    public BigDecimal getHargaSatuanBarang() {
        return hargaSatuanBarang;
    }

    /**
     * @param hargaSatuanBarang the hargaSatuanBarang to set
     */
    public void setHargaSatuanBarang(BigDecimal hargaSatuanBarang) {
        this.hargaSatuanBarang = hargaSatuanBarang;
    }

}
