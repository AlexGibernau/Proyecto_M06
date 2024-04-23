package org.agc.proyecto_m06_m09;

import org.agc.proyecto_m06_m09.network.ChatService;
import org.agc.proyecto_m06_m09.network.Server;
import org.agc.proyecto_m06_m09.network.ChatServiceFactory;

// Run this file to launch server app
public class ServerApplication {
    public static void main(String[] args) {
        Server<ChatService> server = new Server<>(new ChatServiceFactory(), 6000);
        server.startServer();
    }
}
