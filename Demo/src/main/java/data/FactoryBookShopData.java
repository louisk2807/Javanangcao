package data;

public class FactoryBookShopData {
    private FactoryBookShopData(){}
    public static BookShopData getBookData(TypeBookShopData typeBookData){
        switch (typeBookData){
            case CSV:
                return new CSVBookShopData("books.csv");
            case SQLite:
                return new SQLiteBookShopData();
            case Jackson:
                return new JacksonBookShopData();
            case Oracle:
                return new OracleBookShopData();
            default:
                throw new RuntimeException("not support!");
        }
    }
}
