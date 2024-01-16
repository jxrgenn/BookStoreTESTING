package test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ListView;
import main.Book;
import main.Librarian;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class LibrarianTest {

    private static final String TEST_FILE_PATH = "C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\testBookList.txt";
    private static final File FILE_PATH = new File("C:\\Users\\Maçoku\\OneDrive\\Desktop\\Second Yr Assignments\\bookStore101\\src\\main\\files\\bookList.txt");

    private static final File testFile = new File(TEST_FILE_PATH);
    //INITIALIZATION/PRE-CONDITION SETUP
    @BeforeEach
    void setUp() {
        new JFXPanel();
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

        if (testFile.exists()) {
            testFile.delete();
        }
    }

    //FUNCTIONAL TESTING
    @Test
    void testAddBooks() {
        Librarian librarian = new Librarian("John", "Doe", "john@example.com", "password", 0.0, 0.0);

        // Creating sample books
        Book book1 = new Book(12345, "Book1", "Category1", "Supplier1", 50.0);
        Book book2 = new Book(67890, "Book2", "Category2", "Supplier2", 30.0);

        // Creating ListView instances
        ListView<Book> booksInStore = new ListView<>();
        ListView<Book> listView = new ListView<>();

        // Adding books to booksInStore ListView
        booksInStore.getItems().addAll(book1, book2);

        // Simulating the selection of a book in booksInStore
        booksInStore.getSelectionModel().select(book1);

        librarian.addBooks(booksInStore, listView, FILE_PATH);

        // Verify the content of  ListView after addBooks
        assertEquals(1, listView.getItems().size());
        assertTrue(listView.getItems().contains(book1));

        // Verify the content of booksInStore ListView after addBooks
        assertEquals(1, booksInStore.getItems().size());
        assertFalse(booksInStore.getItems().contains(book1));
    }

    //INTEGRATION TESTING (FILE-HANDLING)
    @Test
    void testReWriteBooks() {
        Librarian librarian = new Librarian("John", "Doe", "john@example.com", "password", 0.0, 0.0);

        // Creating sample books
        Book book1 = new Book(12345, "Book1", "Category1", "Supplier1", 50.0);
        Book book2 = new Book(67890, "Book2", "Category2", "Supplier2", 30.0);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        librarian.reWriteBooks(books, testFile);

        // Verify the content of the test file after reWriteBooks
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(TEST_FILE_PATH));
            assertEquals(2, lines.size());

            // Verify the content of the test file
            assertTrue(lines.contains("12345, Book1, Category1, Supplier1, 50.0"));
            assertTrue(lines.contains("67890, Book2, Category2, Supplier2, 30.0"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
