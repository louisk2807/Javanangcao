package data;

import com.opencsv.CSVReader;
import model.BookShop;
import model.BookShopImpl;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVBookShopData implements BookShopData {
    private String path;

    public CSVBookShopData(String path) {
        this.path = path;
    }

    @Override
    public List<BookShop> getAllBook() {
        List<BookShop> bookShopList = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        final int COL_ID = 0,
                COL_ISBN = 5,
                COL_TITLE = 9,
                COL_AUTHORS = 7,
                COL_RATING = 12,
                COL_LANGUAGE = 11,
                COL_YEAR = 8;

        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] row;
            // Đọc lần đầu để bỏ qua tiêu đề cột
            csvReader.readNext();

            while ((row = csvReader.readNext()) != null) {
                BookShopImpl book = new BookShopImpl(
                        Long.parseLong(row[COL_ID]),
                        row[COL_ISBN],
                        row[COL_TITLE],
                        row[COL_AUTHORS],
                        Double.parseDouble(row[COL_RATING]),
                        row[COL_LANGUAGE],
                        row[COL_YEAR].isEmpty() ? 0 : (int) Float.parseFloat(row[COL_YEAR])
                );
                bookShopList.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bookShopList;
    }
}