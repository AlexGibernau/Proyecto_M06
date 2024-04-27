package org.agc.proyecto_m06_m09.network.client;

import org.agc.proyecto_m06_m09.bbdd.Message;
import org.agc.proyecto_m06_m09.bbdd.User;
import org.agc.proyecto_m06_m09.network.protocol.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 6000;

    private static Client INSTANCE;

    private Socket socket;

    private User user;

    private BufferedReader reader;
    private PrintWriter writer;

    private Client() {}

    public static void initClient() {
        if (INSTANCE == null) {
            INSTANCE = new Client();
        }
    }

    public static Client getInstance() {
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void login(String username) throws IOException {
        connectClient();
        writeLines(Protocol.LOGIN, username);

        String stringifiedUser = reader.readLine();
        user = User.from(stringifiedUser);
    }

    public List<Message> refresh() {
        writer.println(Protocol.GET_MESSAGES);
        List<Message> messages = new ArrayList<>();

        String line;
        try {
            while (!(line = reader.readLine()).equals(Protocol.RESPONSE_END)) {
                Message message = Message.from(line);
                messages.add(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public void sendMessage(Message message) {
        writeLines(Protocol.SEND_MESSAGE, message);
    }

    public void logout() {
        user = null;
        writeLines(Protocol.LOGOUT);
        closeClient();
    }

    private void connectClient() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            openStreams(socket);
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
    private void closeClient(){
        try {
            closeStreams();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Communication channels
    private void openStreams(Socket socket) throws IOException {
        openStreams(socket.getInputStream(), socket.getOutputStream());
    }

    private void openStreams(InputStream in, OutputStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.writer = new PrintWriter(out, true);
    }

    private void closeStreams() throws IOException {
        reader.close();
        writer.close();
    }

    private void writeLines(Object... lines) {
        for (Object line : lines) {
            writer.println(line);
        }
    }
}
