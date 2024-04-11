package org.agc.proyecto_m06_m09;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController {
    @FXML
    private VBox chatBox;
    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private TextArea messageInput;

    @FXML
    public void initialize() {
        loadChatMessages();
        messageInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
    }

    @FXML
    protected void sendMessage() {
        String message = messageInput.getText().trim();
        if (message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Must enter a valid message");
            alert.showAndWait();
            return;
        }

        ChatManager.postMessage(message);

        loadMessage(message);
        messageInput.clear();
        chatScrollPane.setVvalue(1d);
    }

    private void loadMessage(Message message) {
        loadMessage(message.getMessage());
    }

    private void loadMessage(String message) {
        // TODO Set max width properly
        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("message-bubble");
        chatBox.getChildren().add(messageLabel);
    }


    private void loadChatMessages() {
        List<Message> messages = ChatManager.loadChatMessages();
        messages.forEach(this::loadMessage);

        chatScrollPane.setVvalue(1d);
    }
}