package org.agc.proyecto_m06_m09.network.server;

public class ChatServiceFactory extends ServiceFactory<ChatService> {

    @Override
    public ChatService createService() {
        return new ChatService();
    }
}
