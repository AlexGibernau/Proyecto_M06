package org.agc.proyecto_m06_m09.data;

import java.util.ArrayList;
import java.util.List;

// CLASE TEMPORAL PARA SUSTITUIR LA BASE DE DATOS
public class ChatRegistry {
    public static final List<Message> MESSAGES = new ArrayList<>();

    static {
        MESSAGES.add(new Message("Carlos Soplete", "Ana Mena", "Buenas socio"));
        MESSAGES.add(new Message("Ana Mena", "Carlos Soplete", "Si"));
    }
}
