import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    @Test
    public void testAddProfit() {
        //Unit testing
        Librarian librarian = new Librarian();
        librarian.addProfit(100.0);
        assertEquals(100.0, librarian.getProfit());
    }

    @Test
    public void testProfitAndBooksNOIntegration() {
        //Integration testing
        Librarian librarian = new Librarian();
        librarian.addProfit(50.0);
        librarian.addBooksNO(3);

        assertEquals(50.0, librarian.getProfit());
        assertEquals(3.0, librarian.getbooksNO());
    }

    @Test
    public void testCreateBillFile() {
        Librarian librarian = new Librarian();
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(123, "Title", "category", "Supplier", 20.0));
        books.add(new Book(456, "Title2", "category", "Supplier", 25.0));

        librarian.CreateBill(books);
       }
}