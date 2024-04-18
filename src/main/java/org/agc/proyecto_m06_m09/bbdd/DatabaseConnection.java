package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class DatabaseConnection {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = entityManagerFactory.createEntityManager();

    public static User getUser(String username) {
        return em.find(User.class, username);
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
        } else {
            getUser(username);
        }

        return false;
    }


    public static void createNewMessage(String message, User userFrom, User userTo, LocalDateTime time) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Mensaje mensaje = new Mensaje();
        mensaje.setIdUserSender(userFrom);
        mensaje.setIdUserReciver(userTo);
        mensaje.setText(message);
        mensaje.setDateTime(time);
        em.persist(message);
    }

    public static void getAllMessages(User user) {
        em.createQuery(
                        "SELECT * FROM mensajes m WHERE m.ID_USER_SENDER = :userid OR m.ID_USER_RECIVER = userid"
                )
                .setParameter("userid", user.getId())
                .getResultList();
    }
}
