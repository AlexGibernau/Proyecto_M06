package org.agc.proyecto_m06_m09.data;

import java.time.LocalDateTime;

public class Message implements Comparable<Message> {
    private final String user;
    private final String receiver;
    private final String message;
    private final LocalDateTime time;

    public Message(String user, String receiver, String message) {
        this.user = user;
        this.receiver = receiver;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getReceiver() {
        return receiver;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    @Override
    public int compareTo(Message other) {
        return time.compareTo(other.time);
    }

    @Override
    public String toString() {
        return message;
    }
}
