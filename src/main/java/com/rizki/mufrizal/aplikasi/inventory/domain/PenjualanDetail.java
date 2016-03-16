package com.rizki.mufrizal.aplikasi.inventory.domain;

import java.io.Serializable;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "kodeTransaksiPenjualan", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Penjualan penjualan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idBarang", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
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
