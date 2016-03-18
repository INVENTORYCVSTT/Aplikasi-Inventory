package com.rizki.mufrizal.aplikasi.inventory.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 9:53:28 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.configuration
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DataSourceConfiguration {

    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
        dataSourceConfig.setJdbcUrl(environment.getRequiredProperty("spring.datasource.url"));
        dataSourceConfig.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        dataSourceConfig.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        dataSourceConfig.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("spring.datasource.maximumPoolSize")));
        dataSourceConfig.setMinimumIdle(Integer.parseInt(environment.getRequiredProperty("spring.datasource.minimumIdle")));
        dataSourceConfig.setMaxLifetime(Integer.parseInt(environment.getRequiredProperty("spring.datasource.maxLifetime")));
        dataSourceConfig.setConnectionTimeout(Long.parseLong(environment.getRequiredProperty("spring.datasource.connectionTimeout")));
        dataSourceConfig.setIdleTimeout(Long.parseLong(environment.getRequiredProperty("spring.datasource.idleTimeout")));
        dataSourceConfig.addDataSourceProperty("poolName", environment.getRequiredProperty("spring.datasource.poolName"));
        dataSourceConfig.addDataSourceProperty("cachePrepStmts", "true");
        dataSourceConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        dataSourceConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(dataSourceConfig);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("spring.datasource.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.datasource.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.datasource.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("spring.datasource.format_sql"));
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setPackagesToScan("com.rizki.mufrizal.aplikasi.inventory.domain");
        return localSessionFactoryBean;
    }

}
