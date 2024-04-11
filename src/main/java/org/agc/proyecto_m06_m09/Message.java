package org.agc.proyecto_m06_m09;

import java.time.LocalTime;

public class Message {
    private final String message;
    private final LocalTime time;
    private final String user;

    public Message(String user, String message) {
        this.message = message;
        this.user = user;
        this.time = LocalTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return message;
    }
}
