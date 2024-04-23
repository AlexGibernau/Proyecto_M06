package org.agc.proyecto_m06_m09.network;

public class ChatServiceFactory extends ServiceFactory<ChatService> {

    @Override
    public ChatService createService() {
        return new ChatService();
    }
}
