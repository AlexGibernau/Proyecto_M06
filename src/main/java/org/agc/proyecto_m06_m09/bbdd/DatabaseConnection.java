package org.agc.proyecto_m06_m09.bbdd;

import jakarta.persistence.*;

import java.time.LocalDate;
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
        } catch (NoResultException ignored) {}

        return user;
    }

    public static boolean createNewUser(String username) {
        EntityTransaction transaction = em.getTransaction();
        try {
            if (getUser(username) == null) {
                transaction.begin();
                User user = new User();
                user.setName(username);
                em.persist(user);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteUser(String username) {
        EntityTransaction transaction = em.getTransaction();
        try {
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createNewMessage(String body, User userFrom, User userTo, LocalDate time) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Message message = new Message();
            message.setIdFrom(userFrom);
            message.setIdTo(userTo);
            message.setMessage(body);
            message.setDateTime(time.toString());

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
        List messages = null;
        try {
            messages = em.createQuery(
                            "SELECT m FROM Message m WHERE m.idFrom = :userid OR m.idTo = :userid"
                    )
                    .setParameter("userid", user)
                    .getResultList();
        } catch (NoResultException ignored) {}

        return messages;
    }
}
