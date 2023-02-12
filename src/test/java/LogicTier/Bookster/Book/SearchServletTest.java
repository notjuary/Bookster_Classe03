package LogicTier.Bookster.Book;

import DataTier.Bookster.Book.Book;
import DataTier.Bookster.Book.BookTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;

class SearchServletTest {


    private static BookTest bookTest;

    @BeforeAll
    public static void init() {
        bookTest = Mockito.mock(BookTest.class);
    }

    @Test
    void lenghtErrorISBN() {
        String inputISBN = "58039102";
        //String inputISBN = "5803910245625"; //-> ISBN con 13 cifre

        Book book = new Book();
        book.setIsbn(inputISBN);

        if(book.bookValidate(inputISBN)) {
            Mockito.when(bookTest.validateBookISBN(book.getIsbn())).thenReturn(true);
            assertTrue(true);
        } else if (!book.bookValidate(inputISBN)) {
            Mockito.when(bookTest.validateBookISBN(book.getIsbn())).thenReturn(false);
            assertTrue(false);
        } else {
            String error = "Risultato inaspettato!";
            fail(error);
        }
    }

    /*
    @Test
    void typeErrorISBN() {

    }

    @Test
    void validateISBN() {

    }

    @Test
    void lenghtBookTitleTooShort() {

    }

    @Test
    void numericBookTitle() {

    }

    @Test
    void validateBookTitle() {

    }

    @Test
    void lenghtAuthoritleTooShort() {

    }
    @Test
    void validateAuthorTitle() {

    }*/
}