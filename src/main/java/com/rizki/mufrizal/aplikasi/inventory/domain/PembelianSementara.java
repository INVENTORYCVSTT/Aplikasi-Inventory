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
