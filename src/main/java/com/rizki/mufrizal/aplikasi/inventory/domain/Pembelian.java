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
 * @Since Mar 22, 2016
 * @Time 10:17:51 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.domain
 *
 */
@Entity
@Indexed
@AnalyzerDef(name = "PembelianAnalyzer",
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class)
    }
)
@Table(name = "tb_pembelian", indexes = {
    @Index(columnList = "kode_transaksi_pembelian", name = "kodeTransaksiPembelian")
})
public class Pembelian implements Serializable {

    @Id
    @DocumentId
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "kode_transaksi_pembelian", length = 150)
    private String kodeTransaksiPembelian;

    @Temporal(TemporalType.DATE)
    @Analyzer(definition = "PembelianAnalyzer")
    @Column(name = "tanggal_transaksi", nullable = false)
    private Date tanggalTransaksi;

    @Column(name = "total_harga")
    private BigDecimal totalHarga;

    @Analyzer(definition = "PembelianAnalyzer")
    @Column(name = "nama_suplier", length = 50)
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String namaSuplier;

    @OneToMany(mappedBy = "pembelian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PembelianDetail> pembelianDetails;

    /**
     * @return the kodeTransaksiPembelian
     */
    public String getKodeTransaksiPembelian() {
        return kodeTransaksiPembelian;
    }

    /**
     * @param kodeTransaksiPembelian the kodeTransaksiPembelian to set
     */
    public void setKodeTransaksiPembelian(String kodeTransaksiPembelian) {
        this.kodeTransaksiPembelian = kodeTransaksiPembelian;
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
     * @return the namaSuplier
     */
    public String getNamaSuplier() {
        return namaSuplier;
    }

    /**
     * @param namaSuplier the namaSuplier to set
     */
    public void setNamaSuplier(String namaSuplier) {
        this.namaSuplier = namaSuplier;
    }

    /**
     * @return the pembelianDetails
     */
    public List<PembelianDetail> getPembelianDetails() {
        return pembelianDetails;
    }

    /**
     * @param pembelianDetails the pembelianDetails to set
     */
    public void setPembelianDetails(List<PembelianDetail> pembelianDetails) {
        this.pembelianDetails = pembelianDetails;
    }
}
