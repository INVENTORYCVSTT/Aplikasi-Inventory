package com.rizki.mufrizal.aplikasi.inventory;

import com.rizki.mufrizal.aplikasi.inventory.service.BarangService;
import com.rizki.mufrizal.aplikasi.inventory.service.PenjualanService;
import com.rizki.mufrizal.aplikasi.inventory.service.UserService;
import com.rizki.mufrizal.aplikasi.inventory.view.MenuUtama;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Since Mar 18, 2016
 * @Time 10:12:46 PM
 * @Encoding UTF-8
 * @Project Aplikasi-Inventory
 * @Package com.rizki.mufrizal.aplikasi.inventory
 *
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        LOGGER.info("load konfigurasi spring hibernate annotation");
        applicationContext = new AnnotationConfigApplicationContext("com.rizki.mufrizal.aplikasi.inventory.configuration");

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.error("error di : {}", ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            LOGGER.info("jalankan menu utama");
            MenuUtama menuUtama = new MenuUtama();
            menuUtama.setExtendedState(JFrame.MAXIMIZED_BOTH);
            menuUtama.setVisible(Boolean.TRUE);
        });
    }

    public static UserService userService() {
        LOGGER.info("inisialisasi bean user service pada spring");
        return (UserService) applicationContext.getBean("UserService");
    }

    public static BarangService barangService() {
        LOGGER.info("inisialisasi bean barang service pada spring");
        return (BarangService) applicationContext.getBean("BarangService");
    }

    public static PenjualanService penjualanService() {
        LOGGER.info("inisialisasi bean penjualan service pada spring");
        return (PenjualanService) applicationContext.getBean("PenjualanService");
    }

}
