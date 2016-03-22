package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;

/**
 * Created by labti on 22/03/16.
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