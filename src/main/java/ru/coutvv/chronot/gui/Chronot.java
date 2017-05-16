package ru.coutvv.chronot.gui;/**
 * @author coutvv
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class Chronot extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("chronot.fxml"));
        VBox root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
//        primaryStage.setIconified(true);
        TimeUnit.SECONDS.sleep(2);
        primaryStage.setIconified(false);
        ChronotController.main = primaryStage;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("icon.jpg");
        Image icon = new Image(is);
//        primaryStage.getIcons().add(new Image("https://www.google.com/s2/favicons?domain=coutvv.ru"));
        primaryStage.getIcons().add(icon);
    }
}
