/**
 * Copyright (C) 2016 Rizki Mufrizal (https://rizkimufrizal.github.io/) (mufrizalrizki@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.springframework.orm.hibernate5.HibernateTransactionManager;
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

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }

}
