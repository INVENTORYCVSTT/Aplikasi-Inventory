package com.rizki.mufrizal.aplikasi.inventory.controller;

import com.rizki.mufrizal.aplikasi.inventory.App;
import com.rizki.mufrizal.aplikasi.inventory.domain.User;
import com.rizki.mufrizal.aplikasi.inventory.view.LoginView;
import com.rizki.mufrizal.aplikasi.inventory.view.RegisterView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:56:16 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory.controller
 *
 */
public class UserController {

    private LoginView loginView;
    private RegisterView registerView;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(LoginView loginView) {
        this.loginView = loginView;
    }

    public UserController(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void login() {
        LOGGER.info("set visible form login");
        this.registerView.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            LoginView login = new LoginView(new javax.swing.JFrame(), Boolean.TRUE);
            login.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            login.setVisible(Boolean.TRUE);
        });
    }

    public User loginUser() {
        LOGGER.info("prosess login");
        if (this.loginView.getEmail().getText().isEmpty()) {
            this.loginView.getError().setText("Silahkan isikan email anda");
            return null;
        } else if (String.valueOf(this.loginView.getPassword().getPassword()).isEmpty()) {
            this.loginView.getError().setText("Silahkan isikan password anda");
            return null;
        } else if ((this.loginView.getEmail().getText().isEmpty()) && (String.valueOf(this.loginView.getPassword().getPassword()).isEmpty())) {
            this.loginView.getError().setText("Silahkan isikan email dan password anda");
            return null;
        } else {
            User user = App.userService().loginUser(this.loginView.getEmail().getText(), String.valueOf(this.loginView.getPassword().getPassword()));
            if (user != null) {
                this.loginView.getError().setText("email dan password anda benar");
                return user;
            } else {
                this.loginView.getError().setText("email dan password anda salah");
            }
        }
        return null;
    }

    public void register() {
        LOGGER.info("set visible form register");
        this.loginView.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            RegisterView register = new RegisterView(new javax.swing.JFrame(), Boolean.TRUE);
            register.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            register.setVisible(Boolean.TRUE);
        });
    }

    public User registerUser() {
        LOGGER.info("prosess register");
        if (this.registerView.getEmail().getText().isEmpty()) {
            this.registerView.getError().setText("Silahkan isikan email anda");
            return null;
        } else if (String.valueOf(this.registerView.getPassword().getPassword()).isEmpty()) {
            this.registerView.getError().setText("Silahkan isikan password anda");
            return null;
        } else if ((this.registerView.getEmail().getText().isEmpty()) && (String.valueOf(this.registerView.getPassword().getPassword()).isEmpty())) {
            this.registerView.getError().setText("Silahkan isikan email dan password anda");
            return null;
        } else {
            User user = App.userService().cariUser(this.registerView.getEmail().getText());
            if (user == null) {
                User userRegiUser = new User();
                userRegiUser.setEmail(this.registerView.getEmail().getText());
                userRegiUser.setPassword(String.valueOf(this.registerView.getPassword().getPassword()));
                App.userService().simpanUser(userRegiUser);
                this.registerView.getError().setText("Anda berhasil melakukan registrasi, silahkan login");
                return userRegiUser;
            } else {
                this.registerView.getError().setText("email sudah digunakan");
            }
        }
        return null;
    }

}
