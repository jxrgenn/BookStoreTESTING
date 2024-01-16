package test;

import main.Book;
import main.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final String TEST_FILE_PATH = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\testBookList.txt";

    //INITIALIZATION/PRE-CONDITION SETUP
    @BeforeEach
    void setUp() {
        // Prepare a test file with sample data
        try {
            FileWriter writer = new FileWriter(TEST_FILE_PATH);
            writer.write("12345,Book1,Category1,Supplier1,50.0\n");
            writer.write("67890,Book2,Category2,Supplier2,30.0\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CLEANUP/POST-CONDITION SETUP
    @AfterEach
    void tearDown() {
        // Delete the test file after each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    //UNIT/FUNCTIONALITY TESTING
    @Test
    void testGetBooks() {
        ArrayList<Book> books = Main.getBooks(TEST_FILE_PATH);

        assertNotNull(books);
        assertEquals(2, books.size());

        // Verify the content of the first book
        Book book1 = books.get(0);
        assertEquals(12345, book1.getISBN());
        assertEquals("Book1", book1.getTitle());
        assertEquals("Category1", book1.getCategory());
        assertEquals("Supplier1", book1.getSupplier());
        assertEquals(50.0, book1.getPurchasedPrice());
        assertEquals(1, book1.getQuantity());

        // Verify the content of the second book
        Book book2 = books.get(1);
        assertEquals(67890, book2.getISBN());
        assertEquals("Book2", book2.getTitle());
        assertEquals("Category2", book2.getCategory());
        assertEquals("Supplier2", book2.getSupplier());
        assertEquals(30.0, book2.getPurchasedPrice());
        assertEquals(1, book2.getQuantity());
    }

    //UNIT/FUNCTIONALITY TESTING
    @Test
    void testCheckQuantity() {
        ArrayList<Book> books = Main.getBooks(TEST_FILE_PATH);

        // Add a duplicate book to simulate multiple books with the same ISBN
        Book duplicateBook = new Book(12345, "DuplicateBook", "Category3", "Supplier3", 40.0);
        books.add(duplicateBook);

        // Call the method to check and update quantities
        Main.checkQuantity(books);

        // Verify that the quantity of the first book has increased to 2
        Book updatedBook = books.get(0);
        assertEquals(2, updatedBook.getQuantity());

        // Verify that the duplicate book has been removed
        assertFalse(books.contains(duplicateBook));
    }

}
