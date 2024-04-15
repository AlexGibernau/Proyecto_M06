package org.agc.proyecto_m06_m09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fx/chat-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
