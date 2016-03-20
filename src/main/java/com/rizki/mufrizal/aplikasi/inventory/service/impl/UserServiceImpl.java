package com.rizki.mufrizal.aplikasi.inventory.service.impl;

import com.rizki.mufrizal.aplikasi.inventory.domain.User;
import com.rizki.mufrizal.aplikasi.inventory.domain.UserRole;
import com.rizki.mufrizal.aplikasi.inventory.repository.UserRepository;
import com.rizki.mufrizal.aplikasi.inventory.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:55:29 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.service.impl
 *
 */
@Service("UserService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loginUser(String email, String password) {

        User user = userRepository.loginUser(email);

        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public void simpanUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));

        LOGGER.debug("hasil generate bcrypt {}", hashed);
        UserRole userRole = new UserRole();
        userRole.setUser(user);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setUserRoles(userRoles);
        user.setPassword(hashed);

        userRepository.simpanUser(user);
    }

    @Override
    public User cariUser(String email) {
        User user = userRepository.cariUser(email);
        if (user != null) {
            return user;
        }
        return null;
    }

}
