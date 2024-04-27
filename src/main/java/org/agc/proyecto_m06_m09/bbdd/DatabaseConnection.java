package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.*;

import java.util.List;

public class DatabaseConnection {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = entityManagerFactory.createEntityManager();

    public static User getUser(String username) {
        User user = null;
        try {
            user = em.createQuery("SELECT u FROM User u WHERE u.name = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }

        if (user == null) {
            createNewUser(username);
            return getUser(username);
        }
        return user;
    }

    public static void createNewUser(String username) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            User user = new User();
            user.setName(username);
            em.persist(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static boolean createNewMessage(Message message) {
        return createNewMessage(message.getMessage(), message.getIdFrom(), message.getIdTo(), message.getDateTime());
    }

    public static boolean createNewMessage(String body, Long userFrom, Long userTo, Long time) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Message message = new Message();
            message.setIdFrom(userFrom);
            message.setIdTo(userTo);
            message.setMessage(body);
            message.setDateTime(time);

            em.persist(message);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public static List getAllMessages(User user) {
        List<Message> messages = null;
        try {
            messages = em.createQuery(
                            "SELECT m FROM Message m WHERE m.idFrom = :userid OR m.idTo = :userid", Message.class
                    )
                    .setParameter("userid", user)
                    .getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
