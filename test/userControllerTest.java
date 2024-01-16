package test;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class userControllerTest {

    private userController<User> userController;
    ArrayList<Manager> managers = new ArrayList<>();
    ArrayList<Librarian> librarians = new ArrayList<>();
    ArrayList<Administrator> admins = new ArrayList<>();

    //INITIALIZATION/PRE-CONDITION SETUP
    @BeforeEach
    void setUp() {
        userController = new userController<>();
    }

    //UNIT/FUNCTIONALITY TESTING
    @Test
    void testAddUser() {
        User user = new User("John", "Doe", "john@example.com", "password");
        userController.addUser(user);
        ArrayList<User> users = userController.getUsers();
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }

    //boundary testing, signing in with valid/invalid credentials
    @Test
    void testSignIn() {

        Manager manager = new Manager("Manager", "One", "manager@example.com", "password");
        Librarian librarian = new Librarian("Librarian", "One", "librarian@example.com", "password");
        Administrator admin = new Administrator("Admin", "One", "admin@example.com", "password");

        managers.add(manager);
        librarians.add(librarian);
        admins.add(admin);

        // Test signing in with valid credentials
        User signedInUser = userController.signIn("manager@example.com", "password", "manager", managers, librarians, admins);
        assertEquals(manager, signedInUser);

        // Test signing in with invalid credentials
        signedInUser = userController.signIn("manager@example.com", "wrong_password", "manager", managers, librarians, admins);
        assertNull(signedInUser);
    }
    //boundary testing, signing in with valid/invalid credentials

    @Test
    void testSignUp() {
        Manager manager = new Manager("Manager", "One", "manager@example.com", "password");
        Librarian librarian = new Librarian("Librarian", "One", "librarian@example.com", "password");
        Administrator admin = new Administrator("Admin", "One", "admin@example.com", "password");

        managers.add(manager);
        librarians.add(librarian);
        admins.add(admin);


        // Test signing up with valid data
        User newUser = userController.signUp("John", "Doe", "john@example.com", "password", "password", "manager", "", "", managers, librarians, admins);
        assertEquals("John", newUser.getName());

        // Test signing up with invalid password
        newUser = userController.signUp("John", "Doe", "john@example.com", "password", "wrong_password", "manager", "", "", managers, librarians, admins);
        assertNull(newUser);

        newUser = userController.signUp("John", "Doe", "librarian@example.com", "password", "password", "librarian", "", "", managers, librarians, admins);
        assertNull(newUser);

        // Test signing up with already existing email
        newUser = userController.signUp("John", "Doe", "manager@example.com", "password", "password", "manager", "", "", managers, librarians, admins);
        assertNull(newUser);

    }

    // Add more tests as needed

}
