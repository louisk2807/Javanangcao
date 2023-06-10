package data;

import model.BookShop;

import java.util.List;

public class JacksonBookShopData implements BookShopData {
    public JacksonBookShopData(){
        throw new RuntimeException("Jackson not support!");
    }
    @Override
    public List<BookShop> getAllBook() {
        return null;
    }
}
