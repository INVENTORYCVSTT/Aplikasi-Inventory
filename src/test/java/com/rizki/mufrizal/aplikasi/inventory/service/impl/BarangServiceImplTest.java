package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.domain.JenisBarang;
import com.rizki.mufrizal.aplikasi.inventory.service.BarangService;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import org.junit.After;
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
public class BarangServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarangServiceImplTest.class);

    @Autowired
    private BarangService barangService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void simpanBarang() throws SQLException {
        Barang barang = new Barang();
        barang.setNamaBarang("tango");
        barang.setJenisBarang(JenisBarang.padat);
        barang.setHargaSatuanBarang(BigDecimal.valueOf(1000));
        barang.setJumlahBarang(50);
        barang.setTanggalKadaluarsa(new Date());

        barangService.simpanBarang(barang);

        String sql = "select count(*) as jumlah from tb_barang where nama_barang = 'tango'";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());

            Long jumlah = resultSet.getLong("jumlah");

            LOGGER.debug("hasil jumlah : {}", jumlah);

            Assert.assertEquals(1L, jumlah.longValue());

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }

    }

    @Test
    public void editBarang() throws Exception {
        Barang barang = new Barang();
        barang.setNamaBarang("tango");
        barang.setJenisBarang(JenisBarang.padat);
        barang.setHargaSatuanBarang(BigDecimal.valueOf(1000));
        barang.setJumlahBarang(50);
        barang.setTanggalKadaluarsa(new Date());

        barangService.simpanBarang(barang);

        Barang barangUpdate = new Barang();
        barangUpdate.setIdBarang(barang.getIdBarang());
        barangUpdate.setNamaBarang(barang.getNamaBarang());
        barangUpdate.setJenisBarang(barang.getJenisBarang());
        barangUpdate.setHargaSatuanBarang(BigDecimal.valueOf(2000));
        barangUpdate.setJumlahBarang(10);
        barangUpdate.setTanggalKadaluarsa(barang.getTanggalKadaluarsa());

        barangService.editBarang(barangUpdate);

        String sql = "select * from tb_barang where nama_barang = 'tango'";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());

            Long jumlah = resultSet.getLong("jumlah_barang");
            BigDecimal hargaSatuan = resultSet.getBigDecimal("harga_satuan_barang");

            LOGGER.debug("hasil jumlah : {}", jumlah);
            LOGGER.debug("harga satuan barang : {}", hargaSatuan);

            Assert.assertEquals(10L, jumlah.longValue());
            Assert.assertEquals(hargaSatuan, BigDecimal.valueOf(200000, 2));

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }

    }

    @Test
    public void hapusBarang() throws Exception {
        Barang barang = new Barang();
        barang.setNamaBarang("tango");
        barang.setJenisBarang(JenisBarang.padat);
        barang.setHargaSatuanBarang(BigDecimal.valueOf(1000));
        barang.setJumlahBarang(50);
        barang.setTanggalKadaluarsa(new Date());

        barangService.simpanBarang(barang);

        barangService.hapusBarang(barang);

        String sql = "select count(*) as jumlah from tb_barang where nama_barang = 'tango'";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());

            Long jumlah = resultSet.getLong("jumlah");

            LOGGER.debug("hasil jumlah : {}", jumlah);

            Assert.assertEquals(0L, jumlah.longValue());

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }
    }

    @Test
    public void jumlahBarang() throws Exception {

        String sql = "select count(*) as jumlah from tb_barang";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());

            Long jumlah = resultSet.getLong("jumlah");

            LOGGER.debug("hasil jumlah : {}", jumlah);

            Assert.assertEquals(3L, jumlah.longValue());
            Assert.assertTrue(barangService.jumlahBarang() == jumlah.intValue());

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }

    }

    @Test
    public void ambilBarangs() throws Exception {
        Integer pageNumber = 1;
        Integer rowsPerPage = 2;
        String sql = "select * from tb_barang limit " + rowsPerPage * (pageNumber - 1) + "," + rowsPerPage;
        String sqlCount = "select count(*) from (select * from tb_barang limit " + rowsPerPage * (pageNumber - 1) + "," + rowsPerPage + ") as jumlah";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            ResultSet resultSetCount = connection.createStatement().executeQuery(sqlCount);

            Assert.assertTrue(resultSet.next());
            Assert.assertTrue(resultSetCount.next());

            Assert.assertEquals(resultSet.getString("id_barang"), barangService.ambilBarangs(pageNumber, rowsPerPage).get(0).getIdBarang());
            Assert.assertEquals(resultSetCount.getInt("jumlah"), barangService.ambilBarangs(pageNumber, rowsPerPage).size());
            Assert.assertEquals(2L, barangService.ambilBarangs(pageNumber, rowsPerPage).size());

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }
    }

    @Test
    public void getBarang() throws Exception {
        Barang barang = barangService.getBarang("b01");

        Assert.assertNotNull(barang);
        Assert.assertEquals("rinso", barang.getNamaBarang());
        Assert.assertEquals(BigDecimal.valueOf(100000, 2), barang.getHargaSatuanBarang());

        Barang b = barangService.getBarang("xxx");
        Assert.assertNull(b);
    }

    @Test
    public void jumlahCariBarang() throws Exception {
        String sql = "select count(*) as jumlah from tb_barang where id_barang = 'b01'";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());

            Long jumlah = resultSet.getLong("jumlah");

            LOGGER.debug("hasil jumlah : {}", jumlah);

            Assert.assertEquals(1L, jumlah.longValue());
            Assert.assertTrue(barangService.jumlahCariBarang("id_barang", "b01") == jumlah.intValue());

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }
    }

    @Test
    public void cariBarang() throws Exception {
        Integer pageNumber = 1;
        Integer rowsPerPage = 2;
        String sql = "select * from tb_barang where id_barang = 'b01' limit " + rowsPerPage * (pageNumber - 1) + "," + rowsPerPage;
        String sqlCount = "select count(*) from (select * from tb_barang where id_barang = 'b01' limit " + rowsPerPage * (pageNumber - 1) + "," + rowsPerPage + ") as jumlah";

        try (Connection connection = dataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            ResultSet resultSetCount = connection.createStatement().executeQuery(sqlCount);

            Assert.assertTrue(resultSet.next());
            Assert.assertTrue(resultSetCount.next());

            Assert.assertEquals(resultSet.getString("id_barang"), barangService.cariBarang("id_barang", "b01", pageNumber, rowsPerPage).get(0).getIdBarang());
            Assert.assertEquals(resultSetCount.getInt("jumlah"), barangService.cariBarang("id_barang", "b01", pageNumber, rowsPerPage).size());
            Assert.assertEquals(1L, barangService.cariBarang("id_barang", "b01", pageNumber, rowsPerPage).size());

        } catch (Exception e) {
            LOGGER.error("error di : {}", e);
        }
    }

    @After
    public void hapusData() throws Exception {
        String sql = "delete from tb_barang where nama_barang = 'tango'";
        try (Connection connection = dataSource.getConnection()) {
            connection.createStatement().executeUpdate(sql);
        }
    }
}
