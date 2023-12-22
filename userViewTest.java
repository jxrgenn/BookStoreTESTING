import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.ListView;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class userViewTest {
    userView Test = new userView();

    @Test
    public void testReWriteBooks() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(123, "Book A", "category", "Supplier", 20.0));
        books.add(new Book(456, "Book B", "category", "Supplier", 25.0));
        books.add(new Book(123, "Book C", "category", "Supplier", 20.0));

        String tempFilePath = "C:\\Users\\YourUsername\\tempTestFile.txt";

        // Performing the method call within a try-catch block to handle exceptions
        try {
            assertDoesNotThrow(() -> Test.reWriteBooks(books));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            File tempFile = new File(tempFilePath);
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

}

