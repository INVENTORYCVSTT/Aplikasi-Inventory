package com.rizki.mufrizal.aplikasi.inventory;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.rizki.mufrizal.aplikasi.inventory.repository.impl.BarangRepositoryImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 21, 2016
 * @Time 11:03:02 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/ApplicationTestRepositoryContext.xml"})
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@Suite.SuiteClasses({
        BarangRepositoryImplTest.class
})
public class AppTest {

}
