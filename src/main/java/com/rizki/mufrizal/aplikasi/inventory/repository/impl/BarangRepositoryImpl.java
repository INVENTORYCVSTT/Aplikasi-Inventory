package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.Barang;
import com.rizki.mufrizal.aplikasi.inventory.repository.BarangRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:00 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class BarangRepositoryImpl implements BarangRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(BarangRepositoryImpl.class);

    @Override
    public void simpanIndexBarang() {
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            LOGGER.error("error di {}", ex);
        }
    }

    @Override
    public void simpanBarang(Barang barang) {
        sessionFactory.getCurrentSession().save(barang);
    }

    @Override
    public void editBarang(Barang barang) {
        sessionFactory.getCurrentSession().update(barang);
    }

    @Override
    public void hapusBarang(Barang barang) {
        sessionFactory.getCurrentSession().delete(barang);
    }

    @Override
    public Integer jumlahBarang() {
        return sessionFactory.getCurrentSession().createCriteria(Barang.class).list().size();
    }

    @Override
    public List<Barang> ambilBarangs(Integer pageNumber, Integer rowsPerPage) {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(Barang.class)
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE)
                .list();
    }

    @Override
    public Barang getBarang(String idBarang) {
        return sessionFactory.getCurrentSession().get(Barang.class, idBarang);
    }

    @Override
    public Integer jumlahCariBarang(String value) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        QueryBuilder queryBuilder = fullTextSession
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Barang.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .fuzzy()
                .onFields("idBarang", "namaBarang")
                .matching(value)
                .createQuery();

        org.hibernate.Query hibernateQuery = fullTextSession
                .createFullTextQuery(luceneQuery, Barang.class)
                .setCacheable(Boolean.TRUE);

        return hibernateQuery.list().size();
    }

    @Override
    public List<Barang> cariBarang(String value, Integer pageNumber, Integer rowsPerPage) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        QueryBuilder queryBuilder = fullTextSession
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Barang.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .fuzzy()
                .onFields("idBarang", "namaBarang")
                .matching(value)
                .createQuery();

        org.hibernate.Query hibernateQuery = fullTextSession
                .createFullTextQuery(luceneQuery, Barang.class)
                .setFirstResult(rowsPerPage * (pageNumber - 1))
                .setMaxResults(rowsPerPage)
                .setCacheable(Boolean.TRUE);

        return hibernateQuery.list();
    }

}
