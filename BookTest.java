import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBookCreation() {
        // Create book object
        Book book = new Book(1234567890, "Sample Book", "Fiction", "Supplier A", 25.0);

        // initial value validation
        assertEquals(1234567890, book.getISBN());
        assertEquals("Sample Book", book.getTitle());
        assertEquals("Fiction", book.getCategory());
        assertEquals("Supplier A", book.getSupplier());
        assertEquals(25.0, book.getPurchasedPrice());
        assertEquals(0.0, book.getOriginalPrice()); //
        assertEquals(0.0, book.getSellingPrice()); //
        assertEquals(1, book.getQuantity()); //
        assertEquals(0, book.getSoldQuantity()); //

        // setter method testing
        book.setSellingPrice(35.0);
        book.setOriginalPrice(30.0);
        assertEquals(35.0, book.getSellingPrice());
        assertEquals(30.0, book.getOriginalPrice());
    }

    @Test
    public void testToStringMethod() {
        Book book = new Book(987654321, "Test Book", "Non-fiction", "Supplier B", 20.0);

        // toString method validation
        String expectedString = "title: Test Book, Category: Non-fiction, ISBN: 987654321, Supplier: Supplier B Price:20.0In stock: 1Sold number: 0";
        assertEquals(expectedString, book.toString());
    }

    @Test
    public void testBillStringMethod() {
        Book book = new Book(111222333, "Sold Book", "Science", "Supplier C", 15.0);
        book.setSoldQuantity(3);

        // billString method validation
        String expectedBillString = "Title: Sold Book, Category: Science, Price: 3 x 15.0 = 45.0, On: " + book.getDateCreated();
        assertEquals(expectedBillString, book.BillString());
    }
}
