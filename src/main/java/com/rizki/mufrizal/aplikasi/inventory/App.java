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
package com.rizki.mufrizal.aplikasi.inventory;

import com.rizki.mufrizal.aplikasi.inventory.service.BarangService;
import com.rizki.mufrizal.aplikasi.inventory.service.PembelianDetailService;
import com.rizki.mufrizal.aplikasi.inventory.service.PembelianService;
import com.rizki.mufrizal.aplikasi.inventory.service.PenjualanDetailService;
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

    public static PenjualanDetailService penjualanDetailService() {
        LOGGER.info("inisialisasi bean penjualan detail service");
        return (PenjualanDetailService) applicationContext.getBean("PenjualanDetailService");
    }

    public static PembelianService pembelianService() {
        LOGGER.info("inisialisasi bean pembelian service pada spring");
        return (PembelianService) applicationContext.getBean("PembelianService");
    }

    public static PembelianDetailService pembelianDetailService() {
        LOGGER.info("inisialisasi bean pembelian detail service");
        return (PembelianDetailService) applicationContext.getBean("PembelianDetailService");
    }

}
