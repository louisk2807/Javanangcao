import data.BookShopData;
import data.FactoryBookShopData;
import data.TypeBookShopData;
import model.BookShop;

import java.util.*;

public class Application {
    private static void menu() {
        System.out.println("--- BOOK MANAGER ---");
        System.out.println("1. List of books");
        System.out.println("2. Search books by author, ISBN, or title");
        System.out.println("3. Sort by increasing rating");
        System.out.println("4. Get top 10 highest-rated books");
        System.out.println("5. Display Total Number of Books");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        BookShopData bookData = FactoryBookShopData.getBookData(TypeBookShopData.CSV);


        List<BookShop> bookShops = bookData.getAllBook();
        Scanner in = new Scanner(System.in);
        int choice = -1;
        do {
            menu();
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("List of books:");
                    for (int i = 0; i < bookShops.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s]\n", i + 1, bookShops.get(i).getIsbn(), bookShops.get(i).getTitle());
                    }
                    break;
                case 2:
                    System.out.println("Enter author, ISBN, or title: ");
                    String keyword = in.nextLine();
                    for (BookShop bookShop : bookShops) {
                        if (bookShop.getAuthors().contains(keyword) || bookShop.getTitle().contains(keyword) || bookShop.getIsbn().contains(keyword)) {
                            System.out.println(bookShop);
                        }
                    }
                    break;
                case 3:

                    Collections.sort(bookShops, Comparator.comparing(BookShop::getAverageRating));
                    System.out.println("List of books sorted by increasing rating:");
                    for (BookShop bookShop : bookShops) {
                        System.out.println(bookShop);
                    }
                    break;
                case 4:
                    // Lấy ra 10 cuốn sách có rating cao nhất
                    Collections.sort(bookShops, Comparator.comparing(BookShop::getAverageRating).reversed());
                    int topN = Math.min(10, bookShops.size());
                    System.out.printf("Top %d highest-rated books:\n", topN);
                    for (int i = 0; i < topN; i++) {
                        System.out.println(bookShops.get(i));
                    }
                    break;
                case 5:
                    int totalBooks = bookShops.size();
                    System.out.println("Total number of books: " + totalBooks);
                    break;
                default:
                    break;
            }
        } while (choice != 0);
    }
    static List<BookShop> searchBooks(List<BookShop> bookShops, String keyword) {
        List<BookShop> searchResults = new ArrayList<>();
        for (BookShop bookShop : bookShops) {
            if (bookShop.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    bookShop.getAuthors().toLowerCase().contains(keyword.toLowerCase()) ||
                    bookShop.getIsbn().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(bookShop);
            }
        }
        return searchResults;
    }

    static void sortBooksByRating(List<BookShop> bookShops) {
        Collections.sort(bookShops, Comparator.comparingDouble(BookShop::getAverageRating));
    }

    static List<BookShop> getTopRatedBooks(List<BookShop> bookShops, int count) {
        List<BookShop> topRatedBookShops = new ArrayList<>(bookShops);
        topRatedBookShops.sort(Comparator.comparingDouble(BookShop::getAverageRating).reversed());
        return topRatedBookShops.subList(0, Math.min(count, topRatedBookShops.size()));
    }


}
