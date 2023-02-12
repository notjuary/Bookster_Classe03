package DataTier.Bookster.Book;

public class BookTest {
    public BookTest(){};

    public boolean validateBookISBN(String ISBN) {
        return ISBN.length() == 10 || ISBN.length() == 13;
    }
}
