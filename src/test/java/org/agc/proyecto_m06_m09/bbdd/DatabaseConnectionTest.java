package org.agc.proyecto_m06_m09.bbdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    static final String insertMessage = "No need to insert items for each test";

    // FETCHS //

    @Test
    void getUser() {
        User user = DatabaseConnection.getUser("Alex");
        assertNotNull(user);
        assertEquals("Alex", user.getName());

        user = DatabaseConnection.getUser("aifhlnsdmvjñsnhgñdf");
        assertNull(user);
    }

    @Test
    void getAllMessages() {
        User user = DatabaseConnection.getUser("Alex");
        List messages = DatabaseConnection.getAllMessages(user);

        if (user != null) {
            assertNotNull(messages);
            assertFalse(messages.isEmpty());

            System.out.println(messages);
        } else {
            assertNull(messages);
            assertTrue(messages.isEmpty());
        }
    }

    // INSERTS //

    @Test
    @Disabled(insertMessage)
    void createNewUser() {
        assertTrue(DatabaseConnection.createNewUser("Pol"));
    }

    @Test
    @Disabled(insertMessage)
    void createNewMessage() {
        User user1 = DatabaseConnection.getUser("Alex");
        User user2 = DatabaseConnection.getUser("Pol");

        assertTrue(DatabaseConnection.createNewMessage("hey",user2,user1, LocalDate.now()));
    }

    // DELETE //
}