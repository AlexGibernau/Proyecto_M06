package org.agc.proyecto_m06_m09.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

public class Server<S extends Service> {
    private final Supplier<S> serviceSupplier;
    private final int port;

    public Server(Supplier<S> serviceSupplier, int port) {
        this.serviceSupplier = serviceSupplier;
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            S.onStartService(serverSocket);

            while (true) {
                Socket socket = serverSocket.accept();
                Thread connectionThread = generateConnectionThread(socket);
                connectionThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Thread generateConnectionThread(Socket socket) {
        return new Thread(() -> {
            S service = serviceSupplier.get();

            try {
                service.onConnection(socket);
            } catch (IOException e) {
                service.onError(socket, e);
            }
        });
    }

}
