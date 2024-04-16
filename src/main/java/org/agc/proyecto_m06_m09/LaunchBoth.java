package org.agc.proyecto_m06_m09;

// Run this file to launch both the server app and the client app
public class LaunchBoth {
    public static void main(String[] args) {
        new Thread(() -> ChatApplication.main(new String[0])).start();
        new Thread(() -> ServerApplication.main(new String[0])).start();
    }
}
