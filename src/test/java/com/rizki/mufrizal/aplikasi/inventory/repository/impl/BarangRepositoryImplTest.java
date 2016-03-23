package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.domain.JenisBarang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 23, 2016
 * @Time 7:40:00 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationTestRepositoryContext.xml"})
@TestExecutionListeners(listeners = {
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class
})
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
            "/data/barang.sql"
        }
)
public class BarangRepositoryImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarangRepositoryImplTest.class);

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private DataSource dataSource;

    @Transactional
    @Test
    public void simpanBarang() throws Exception {
        Barang barang = new Barang();
        barang.setNamaBarang("tango");
        barang.setJenisBarang(JenisBarang.padat);
        barang.setHargaSatuanBarang(BigDecimal.valueOf(1000));
        barang.setJumlahBarang(50);
        barang.setTanggalKadaluarsa(new Date());

        barangRepository.simpanBarang(barang);

        String sql = "select count(*) as jumlah from tb_barang";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());

            LOGGER.debug("hasil jumlah : {}", resultSet.getInt("jumlah"));

            Assert.assertEquals(String.valueOf(resultSet.getInt("jumlah")), String.valueOf(3));

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }

    }

    @Test
    public void editBarang() throws Exception {

    }

    @Test
    public void hapusBarang() throws Exception {

    }

    @Test
    public void jumlahBarang() throws Exception {

    }

    @Test
    public void ambilBarangs() throws Exception {

    }

    @Test
    public void getBarang() throws Exception {

    }
}
