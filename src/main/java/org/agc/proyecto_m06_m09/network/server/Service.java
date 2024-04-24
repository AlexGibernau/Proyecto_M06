package org.agc.proyecto_m06_m09.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Service {
    protected BufferedReader reader;
    protected PrintWriter writer;

    public void onConnection(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        System.out.println();

        handleConnection(socket);

        reader.close();
        writer.close();
    }

    public abstract void handleConnection(Socket socket) throws IOException;

    public void onError(Socket socket, IOException e) {
        System.out.println("Service error: " + e.getMessage());

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException closeException) {
                System.err.println("Error closing socket: " + closeException.getMessage());
            }
        }
    }
}