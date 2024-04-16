package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.agc.proyecto_m06_m09.data.Chat;
import org.agc.proyecto_m06_m09.fx.ChatManager;
import org.agc.proyecto_m06_m09.fx.LoginController;
import org.agc.proyecto_m06_m09.network.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DatabaseConnection {
    private boolean exist = false;
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = entityManagerFactory.createEntityManager();

    public static User getUser(String username) {

    }


    public static boolean createNewUser(String username) {
        EntityTransaction transaction = em.getTransaction();

        if (em.find(User.class, username) == null) {
            transaction.begin();
            User user = new User();
            user.setNombre(username);
            em.persist(user);
            transaction.commit();
            return true;
        }
        else {
            getUser(username);
        }

        return false;
    }


    public static void creteNewMessage(String message, int userFrom, int userTo, LocalDateTime time) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Mensaje mensaje = new Mensaje();
        mensaje.setIdUserSender(userFrom);
        mensaje.setIdUserReciver(userTo);
        mensaje.setText(message);
        mensaje.setDateTime(time);
        em.persist(message);

    }
}