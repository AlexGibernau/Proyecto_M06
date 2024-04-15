package org.agc.proyecto_m06_m09.fx;

import org.agc.proyecto_m06_m09.bbdd.ChatRegistry;
import org.agc.proyecto_m06_m09.bbdd.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatManager {
    private static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        ChatManager.currentUser = currentUser;
    }

    // TODO Should fetch messages from database
    public static List<Message> loadChatMessages() {
        return ChatRegistry.MESSAGES;
    }

    // TODO Should post message to database
    // TODO Should send message to other users
    public static void postMessage(String receiver, @NotNull String message) {
        Message msg = new Message(currentUser, receiver, message);
        ChatRegistry.MESSAGES.add(msg);
    }
}
