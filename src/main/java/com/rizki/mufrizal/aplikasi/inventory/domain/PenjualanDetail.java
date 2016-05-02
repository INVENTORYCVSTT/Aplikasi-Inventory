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

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 16, 2016
 * @Time 8:00:30 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.domain
 *
 */
@Entity
@Audited
@Table(name = "tb_penjualan_detail", indexes = {
    @Index(columnList = "kode_transaksi_penjualan_detail", name = "kodeTransaksiPenjualanDetail")
})
public class PenjualanDetail implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "kode_transaksi_penjualan_detail", length = 150)
    private String kodeTransaksiPenjualanDetail;

    @Column(name = "jumlah_barang")
    private Integer jumlahBarang;

    @Column(name = "total_harga_per_barang")
    private BigDecimal totalHargaPerBarang;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_transaksi_penjualan", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Penjualan penjualan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_barang", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Barang barang;

    /**
     * @return the kodeTransaksiPenjualanDetail
     */
    public String getKodeTransaksiPenjualanDetail() {
        return kodeTransaksiPenjualanDetail;
    }

    /**
     * @param kodeTransaksiPenjualanDetail the kodeTransaksiPenjualanDetail to set
     */
    public void setKodeTransaksiPenjualanDetail(String kodeTransaksiPenjualanDetail) {
        this.kodeTransaksiPenjualanDetail = kodeTransaksiPenjualanDetail;
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
     * @return the totalHargaPerBarang
     */
    public BigDecimal getTotalHargaPerBarang() {
        return totalHargaPerBarang;
    }

    /**
     * @param totalHargaPerBarang the totalHargaPerBarang to set
     */
    public void setTotalHargaPerBarang(BigDecimal totalHargaPerBarang) {
        this.totalHargaPerBarang = totalHargaPerBarang;
    }

    /**
     * @return the penjualan
     */
    public Penjualan getPenjualan() {
        return penjualan;
    }

    /**
     * @param penjualan the penjualan to set
     */
    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    /**
     * @return the barang
     */
    public Barang getBarang() {
        return barang;
    }

    /**
     * @param barang the barang to set
     */
    public void setBarang(Barang barang) {
        this.barang = barang;
    }

}
