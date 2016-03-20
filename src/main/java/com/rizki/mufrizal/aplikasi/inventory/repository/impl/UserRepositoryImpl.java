package com.rizki.mufrizal.aplikasi.inventory.repository.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.User;
import com.rizki.mufrizal.aplikasi.inventory.repository.UserRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:54:23 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository.impl
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User loginUser(String email) {
        List<User> users = sessionFactory
                .getCurrentSession()
                .createQuery("select u from User u left join fetch u.userRoles pd where u.email = :email")
                .setParameter("email", email)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void simpanUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User cariUser(String email) {
        return sessionFactory.getCurrentSession().get(User.class, email);
    }

}
