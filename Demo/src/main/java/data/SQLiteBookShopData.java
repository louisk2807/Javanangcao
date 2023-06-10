package data;

import model.BookShop;

import java.util.List;

public class SQLiteBookShopData implements BookShopData {
    public SQLiteBookShopData(){
        throw new RuntimeException("SQLite not support!");
    }
    @Override
    public List<BookShop> getAllBook() {
        return null;
    }
}
