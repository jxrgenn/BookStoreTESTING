import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testGetBooks() {
        // File I/O Testing - Ensure correct conversion of file data to ArrayList<Book>
        ArrayList<Book> books = Main.getBooks();
        assertNotNull(books); // Ensure the ArrayList is not null
        assertEquals(3, books.size()); // Assuming there are three books in the test file
        // Add more assertions based on the expected contents of the ArrayList<Book>
    }

    @Test
    public void testCheckQuantity() {
        // Unit Testing - Test removing duplicates and updating quantities
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(123, "Book A", "category", "Supplier", 20.0));
        books.add(new Book(456, "Book B", "category", "Supplier", 25.0));
        books.add(new Book(123, "Book C", "category", "Supplier", 20.0));

        Main.checkQuantity(books);

        assertEquals(3, books.size());
    }

    @Test
    public void testBillFile() throws IOException {
        // File I/O Testing - Test bill file creation
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(123, "Book X", "category", "Supplier", 20.0));
        books.add(new Book(456, "Book Y", "category", "Supplier", 25.0));

        int billNo = 1001; // Test bill number
        Main.BillFile(books, billNo);

        File billFile = new File("bill 1001.txt"); //
        assertTrue(billFile.exists()); // Check if the file exists
    }

    @Test
    public void testOpenFile() {
        // Behavioral Testing - mocking opening a file
        File mockFile = new File("mockFile.txt");
        assertFalse(mockFile.exists()); // Assuming the mockFile doesnt exist initially

        // make sure file does not throw exceptions when opening
        assertDoesNotThrow(() -> Main.openFile(mockFile));

        assertTrue(mockFile.exists());
    }
}
