package org.agc.proyecto_m06_m09.data;

import java.util.List;

public class Chat implements Comparable<Chat> {
    private List<Message> messages;

    public Message getLastMessage() {
        return messages.get(messages.size() - 1);
    }

    @Override
    public int compareTo(Chat other) {
        return 0;
    }
}
