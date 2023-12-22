import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdministratorControllerTest {

    private AdministratorController test;

    @BeforeEach
    public void setUp() {
        test = new AdministratorController();
    }

    @Test
    public void testAddAdmin() {
        Administrator admin = new Administrator("John", "Doe", "john@example.com", "password");
        test.addAdmin(admin);
        assertEquals(1, test.getAdmins().size());
    }

    @Test
    public void testSignUpValid() {
        assertTrue(test.signUp("Filan", "fisteku", "filan@fisteku.com", "password", "password"));
        assertEquals(1, test.getAdmins().size());
    }

    @Test
    public void testSignUpInvalid() {
        assertFalse(test.signUp("Alice", "Johnson", "alice@example.com", "pass123", "password"));
        assertEquals(0, test.getAdmins().size());
    }

    @Test
    public void testGetAdmin() {
        Administrator admin = new Administrator("Sam", "Wilson", "sam@example.com", "securePass");
        test.addAdmin(admin);
        assertEquals(admin, test.getAdmin("sam@example.com", "securePass"));
    }
    // In the future we will also add tests for edge cases and error handling
}
