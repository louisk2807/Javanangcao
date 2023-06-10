package data;

import model.BookShop;

import java.util.List;

public class OracleBookShopData implements BookShopData {
    public OracleBookShopData(){
        throw new RuntimeException("Oracle not support!");
    }
    @Override
    public List<BookShop> getAllBook() {
        return null;
    }
}
