package org.agc.proyecto_m06_m09.network.server;

import org.agc.proyecto_m06_m09.bbdd.DatabaseConnection;
import org.agc.proyecto_m06_m09.bbdd.Message;
import org.agc.proyecto_m06_m09.bbdd.User;
import org.agc.proyecto_m06_m09.network.protocol.Protocol;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatService extends Service {
    private static final List<ChatService> services = new ArrayList<>();

    private User user;

    public ChatService() {
        services.add(this);
    }

    public User getUser() {
        return user;
    }

    @Override
    public void handleConnection(Socket socket) throws IOException {
        boolean validAction = true;
        do {
            String action = reader.readLine();

            switch (action) {
                case Protocol.LOGIN -> handleLogin();
                case Protocol.SEND_MESSAGE -> handleMessage();
                case Protocol.LOGOUT -> handleLogout();

                case null, default -> validAction = false;
            }
        } while (validAction);
    }

    @Override
    public void onError(Socket socket, IOException e) {
        super.onError(socket, e);
        try {
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void handleLogin() throws IOException {
        String username = reader.readLine();
        User user = DatabaseConnection.getUser(username);
    }

    private void handleMessage() {
    }

    private void handleLogout() {
    }


    private User register(String username) {
        boolean created = DatabaseConnection.createNewUser(username);
        return DatabaseConnection.getUser(username);
    }

    public void sendMessage(Message message) {
        writer.println(message);
    }

    private void sendChats() {
        List<String> messages = List.of(
                "Hola", "Que tal", "Baibai"
        );
        messages.forEach(writer::println);
    }

    private void receiveMessage(Message message) {
//        sendMessageTo(message.getIdTo(), message);
    }
}
