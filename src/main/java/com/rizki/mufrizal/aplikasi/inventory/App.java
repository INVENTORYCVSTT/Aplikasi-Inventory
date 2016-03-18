package com.rizki.mufrizal.aplikasi.inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext("com.rizki.mufrizal.aplikasi.inventory.configuration");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        LOGGER.info("aplikasi is starting");
        
        LOGGER.debug("load file fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BarangView.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Aplikasi Inventory");
        stage.setScene(scene);
        stage.show();
    }

}
