package org.agc.proyecto_m06_m09.network;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 6000;

    private static Client INSTANCE;

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    private Client() {
    }

    public static void initClient() {
        if (INSTANCE == null) {
            INSTANCE = new Client();
        }
    }

    public static Client getInstance() {
        return INSTANCE;
    }

    public void connectUser(String username) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }

    // Communication channels
    private void openStreams(InputStream in, OutputStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintWriter(out, true);
    }

    private void closeStreams() throws IOException {
        in.close();
        out.close();
    }

    private void sendMessage(String message) {
        out.println(message);
    }

    private String readMessage() throws IOException {
        return in.readLine();
    }
}
