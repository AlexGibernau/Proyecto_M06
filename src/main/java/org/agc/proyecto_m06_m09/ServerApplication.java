package org.agc.proyecto_m06_m09;

import org.agc.proyecto_m06_m09.network.server.ChatService;
import org.agc.proyecto_m06_m09.network.server.Server;
import org.agc.proyecto_m06_m09.network.server.ChatServiceFactory;

// Run this file to launch server app
public class ServerApplication {
    public static void main(String[] args) {
        Server<ChatService> server = new Server<>(new ChatServiceFactory(), 6000);
        server.startServer();
    }
}
