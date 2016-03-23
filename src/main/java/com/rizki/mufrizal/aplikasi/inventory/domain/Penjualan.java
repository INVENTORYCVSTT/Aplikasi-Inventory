package com.rizki.mufrizal.aplikasi.inventory.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 16, 2016
 * @Time 7:56:46 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.domain
 *
 */
@Entity
@Indexed
@AnalyzerDef(name = "PenjualanAnalyzer",
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class)
    }
)
@Table(name = "tb_penjualan", indexes = {
    @Index(columnList = "kode_transaksi_penjualan", name = "kodeTransaksiPenjualan")
})
public class Penjualan implements Serializable {

    @Id
    @DocumentId
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "kode_transaksi_penjualan", length = 150)
    private String kodeTransaksiPenjualan;

    @Temporal(TemporalType.DATE)
    @Analyzer(definition = "PenjualanAnalyzer")
    @Column(name = "tanggal_transaksi", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private Date tanggalTransaksi;

    @Column(name = "total_harga")
    private BigDecimal totalHarga;

    @Analyzer(definition = "PenjualanAnalyzer")
    @Column(name = "nama_pembeli", length = 50)
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String namaPembeli;

    @OneToMany(mappedBy = "penjualan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenjualanDetail> penjualanDetails;

    /**
     * @return the kodeTransaksiPenjualan
     */
    public String getKodeTransaksiPenjualan() {
        return kodeTransaksiPenjualan;
    }

    /**
     * @param kodeTransaksiPenjualan the kodeTransaksiPenjualan to set
     */
    public void setKodeTransaksiPenjualan(String kodeTransaksiPenjualan) {
        this.kodeTransaksiPenjualan = kodeTransaksiPenjualan;
    }

    /**
     * @return the tanggalTransaksi
     */
    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    /**
     * @param tanggalTransaksi the tanggalTransaksi to set
     */
    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    /**
     * @return the totalHarga
     */
    public BigDecimal getTotalHarga() {
        return totalHarga;
    }

    /**
     * @param totalHarga the totalHarga to set
     */
    public void setTotalHarga(BigDecimal totalHarga) {
        this.totalHarga = totalHarga;
    }

    /**
     * @return the namaPembeli
     */
    public String getNamaPembeli() {
        return namaPembeli;
    }

    /**
     * @param namaPembeli the namaPembeli to set
     */
    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    /**
     * @return the penjualanDetails
     */
    public List<PenjualanDetail> getPenjualanDetails() {
        return penjualanDetails;
    }

    /**
     * @param penjualanDetails the penjualanDetails to set
     */
    public void setPenjualanDetails(List<PenjualanDetail> penjualanDetails) {
        this.penjualanDetails = penjualanDetails;
    }

}
