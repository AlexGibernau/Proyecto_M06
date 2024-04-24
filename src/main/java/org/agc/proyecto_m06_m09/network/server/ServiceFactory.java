package org.agc.proyecto_m06_m09.network.server;

import java.net.ServerSocket;

public abstract class ServiceFactory<S extends Service> {
    public void onStartService(ServerSocket serverSocket) {
        System.out.println("Service started at port " + serverSocket.getLocalPort());
    }

    public abstract S createService();
}
