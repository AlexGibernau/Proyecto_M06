package org.agc.proyecto_m06_m09.fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.agc.proyecto_m06_m09.bbdd.Message;
import org.agc.proyecto_m06_m09.network.client.Client;

import java.io.IOException;
import java.util.List;

public class ChatController {
    @FXML
    private VBox chatBox;
    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private TextArea messageInput;

    @FXML
    public void initialize() throws IOException {
        loadChatMessages();
        messageInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    sendMessage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    protected void sendMessage() throws IOException {
        String text = messageInput.getText().trim();
        if (text.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Must enter a valid message");
            alert.showAndWait();
            return;
        }

        long idTo = Client.getInstance().getUser().getId() == 4 ? 6 : 4;

        Message message = new Message();
        message.setMessage(text);
        message.setIdFrom(Client.getInstance().getUser().getId());
        message.setIdTo(idTo); // Switching between 2 users until chat management is done
        message.setDateTime(System.currentTimeMillis());

        Client.getInstance().sendMessage(message);
        messageInput.clear();

        loadChatMessages();
    }

    @FXML
    private void logout(ActionEvent event){
        try {
            Client.getInstance().logout();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("logout.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        loadChatMessages();
    }

    private void loadChatMessages() {
        chatBox.getChildren().clear();
        new Thread(() -> {
            List<Message> messages = Client.getInstance().refresh();
            messages.forEach(this::loadMessage);

            Platform.runLater(() -> chatScrollPane.setVvalue(1d));
        }).start();
    }

    private void loadMessage(Message message) {
        Platform.runLater(() -> {
            HBox hbox = new HBox();
            Label messageLabel = new Label(message.getMessage());
            if (message.getIdFrom() == Client.getInstance().getUser().getId()) {
                hbox.getStyleClass().add("message-right");
                messageLabel.getStyleClass().add("message-bubble-right");
            } else {
                hbox.getStyleClass().add("message");
                messageLabel.getStyleClass().add("message-bubble");
            }
            hbox.getChildren().add(messageLabel);
            chatBox.getChildren().add(hbox);
        });
    }
}