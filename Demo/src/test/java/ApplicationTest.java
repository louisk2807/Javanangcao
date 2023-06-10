
import model.BookShop;
import model.BookShopImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    private static List<BookShop> bookShops;

    @BeforeAll
    static void setup() {
        // Mocking the book data for testing
        bookShops = new ArrayList<>();
        bookShops.add(new BookShopImpl(1, "1234567890", "Book 1", "Author 1", 4.5, "English", 2020));
        bookShops.add(new BookShopImpl(2, "0987654321", "Book 2", "Author 2", 4.2, "Spanish", 2019));
        bookShops.add(new BookShopImpl(3, "9876543210", "Book 3", "Author 1", 4.8, "English", 2021));
    }

    @Test
    void testSearchBooks() {
        List<BookShop> searchResults = Application.searchBooks(bookShops, "Author 1");
        assertEquals(2, searchResults.size());
        assertEquals("Book 1", searchResults.get(0).getTitle());
        assertEquals("Book 3", searchResults.get(1).getTitle());
    }

    @Test
    void testSortBooksByRating() {
        List<BookShop> sortedBookShops = new ArrayList<>(bookShops);
        Application.sortBooksByRating(sortedBookShops);
        assertEquals(4.2, sortedBookShops.get(0).getAverageRating());
        assertEquals(4.5, sortedBookShops.get(1).getAverageRating());
        assertEquals(4.8, sortedBookShops.get(2).getAverageRating());
    }

    @Test
    void testGetTopRatedBooks() {
        List<BookShop> topRatedBookShops = Application.getTopRatedBooks(bookShops, 2);
        assertEquals(2, topRatedBookShops.size());
        assertEquals("Book 3", topRatedBookShops.get(0).getTitle());
        assertEquals("Book 1", topRatedBookShops.get(1).getTitle());
    }
}