package org.agc.proyecto_m06_m09.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Server<S extends Service> {
    private final ServiceFactory<S> serviceFactory;
    private final int port;

    public Server(ServiceFactory<S> serviceFactory, int port) {
        this.serviceFactory = serviceFactory;
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serviceFactory.onStartService(serverSocket);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getRemoteSocketAddress());
                Thread connectionThread = generateConnectionThread(socket);
                connectionThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Thread generateConnectionThread(Socket socket) {
        return new Thread(() -> {
            S service = serviceFactory.createService();

            try {
                service.onConnection(socket);
            } catch (IOException e) {
                service.onError(socket, e);
            }
        });
    }

}
