package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;

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
@Sql(
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
    scripts = {"/data/barang.sql"}
)
public class BarangRepositoryImplTest {

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void simpanBarang() throws Exception {

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
