package test;

import main.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    //ALL METHODS ARE FOR UNIT TESTING
    @Test
    public void testConstructorAndGetters() {
        User user = new User("John", "Doe", "john.doe@example.com", "password");

        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetters() {
        User user = new User();

        user.setName("Jane");
        user.setSurname("Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("newpassword");

        assertEquals("Jane", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testToString() {
        User user = new User("Alice", "Smith", "alice.smith@example.com", "securepwd");

        String expected = "Name: Alice| Surname: Smith| Email: alice.smith@example.com";
        assertEquals(expected, user.toString());
    }
}
