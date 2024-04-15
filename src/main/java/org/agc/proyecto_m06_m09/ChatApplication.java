package org.agc.proyecto_m06_m09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.agc.proyecto_m06_m09.network.Client;

// Run this file to launch client app
public class ChatApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fx/login.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Client.initClient();
        launch(args);
    }
}
