package org.agc.proyecto_m06_m09.network.server;

import org.agc.proyecto_m06_m09.bbdd.DatabaseConnection;
import org.agc.proyecto_m06_m09.bbdd.Message;
import org.agc.proyecto_m06_m09.bbdd.User;
import org.agc.proyecto_m06_m09.network.protocol.Protocol;

import java.io.IOException;
import java.net.Socket;


public class ChatService extends Service {
    private User user;

    @Override
    public void handleConnection(Socket socket) {
        String action;
        try {
            while ((action = reader.readLine()) != null) {
                switch (action) {
                    case Protocol.LOGIN -> handleLogin();
                    case Protocol.GET_MESSAGES -> handleGetMessages();
                    case Protocol.SEND_MESSAGE -> handleMessage();
                    case Protocol.LOGOUT -> {
                        handleLogout();
                        return;
                    }
                    default -> throw new IllegalArgumentException("Invalid action: " + action);
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void handleLogin() throws IllegalArgumentException, IOException {
        String username = reader.readLine();
        if (username == null) {
            throw new IllegalArgumentException("Username not specified");
        }

        user = DatabaseConnection.getUser(username);
    }

    private void handleGetMessages() throws IllegalArgumentException, IOException {
        DatabaseConnection.getAllMessages(user).forEach(writer::println);
        writer.println(Protocol.RESPONSE_END);
    }

    private void handleMessage() throws IllegalArgumentException, IOException {
        String stringifiedMessage = reader.readLine();
        if (stringifiedMessage == null) {
            throw new IllegalArgumentException("Message not specified");
        }
        Message message = Message.from(stringifiedMessage);

        DatabaseConnection.createNewMessage(message);
    }

    // Maybe add implementation if needed to do something on log out (o_O)
    private void handleLogout() {
    }
}
