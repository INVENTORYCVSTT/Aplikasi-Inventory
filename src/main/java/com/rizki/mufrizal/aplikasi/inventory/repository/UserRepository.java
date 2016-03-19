package com.rizki.mufrizal.aplikasi.inventory.repository;

import com.rizki.mufrizal.aplikasi.inventory.domain.User;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:52:44 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.repository
 *
 */
public interface UserRepository {

    public User loginUser(String email, String password);

}
