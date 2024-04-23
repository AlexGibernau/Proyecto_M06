package org.agc.proyecto_m06_m09.network;

import org.agc.proyecto_m06_m09.bbdd.DatabaseConnection;
import org.agc.proyecto_m06_m09.bbdd.Message;
import org.agc.proyecto_m06_m09.bbdd.User;

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

    private static void sendMessageTo(User user, Message message) {
        ChatService chatService = services
                .stream()
                .filter(service -> service.getUser().equals(user))
                .findFirst()
                .orElse(null);

        chatService.sendMessage(message);
    }

    @Override
    public void handleConnection(Socket socket) throws IOException {
        String username = reader.readLine();
//        User user = DatabaseConnection.getUser(username);

//        if (user == null) {
//            user = register(username);
//        }

        sendChats();
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
        sendMessageTo(message.getIdTo(), message);
    }
}
