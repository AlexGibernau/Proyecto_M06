package org.agc.proyecto_m06_m09.network;

import org.agc.proyecto_m06_m09.bbdd.DatabaseConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatService extends Service {

    public static void onStartService(ServerSocket serverSocket) {
        Service.onStartService(serverSocket);

        System.out.println("Chat service started");
    }

    @Override
    public void handleConnection(Socket socket) throws IOException {
        if (/*new user*/) {
            // Create new user in DDBB
            DatabaseConnection.createNewUser(/*username from socket*/socket.);
        }
    }
}
