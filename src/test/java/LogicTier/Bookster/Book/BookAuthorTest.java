package LogicTier.Bookster.Book;

import DataTier.Bookster.Book.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookAuthorTest {

    private static Book book;

    @BeforeAll
    public static void init() {
        book = Mockito.mock(Book.class);
    }

    @Test
    void AuthorTest1() {
        String inputAuthor = ""; //Lunghezza 0

        Mockito.when(book.validateLenghtBookAuthor(inputAuthor)).thenReturn(false);
        assertFalse(book.validateLenghtBookAuthor(inputAuthor));
    }

    @Test
    void AuthorTest2() { //Lunghezza maggiore di 30
        String inputAuthor = "Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa.";

        Mockito.when(book.validateLenghtBookAuthor(inputAuthor)).thenReturn(false);
        assertFalse(book.validateLenghtBookAuthor(inputAuthor));
    }

    @Test
    void AuthorTest3() {

        String inputAuthor = "Pi3ro Ang3l4"; //Lunghezza maggiore di 0 e caratteri validi

        Mockito.when(book.validateAuthor(inputAuthor)).thenReturn(false);
        assertFalse(book.validateAuthor(inputAuthor));
    }

    @Test
    void AuthorTest4() {
        String inputAuthor = "Stephen King"; //Lunghezza maggiore di 0 e caratteri validi

        Mockito.when(book.validateAuthor(inputAuthor)).thenReturn(true);
        assertTrue(book.validateAuthor(inputAuthor));
    }
}
