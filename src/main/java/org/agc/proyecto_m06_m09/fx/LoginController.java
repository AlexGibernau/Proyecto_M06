package org.agc.proyecto_m06_m09.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.agc.proyecto_m06_m09.network.client.Client;

import java.awt.*;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat-view.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Client.getInstance().connectUser(username.getText());
    }
}
