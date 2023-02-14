package LogicTier.Bookster.Book;

import DataTier.Bookster.Book.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class    BookTitleTest {

    private static Book book;

    @BeforeAll
    public static void init() {
        book = Mockito.mock(Book.class);
    }

    @Test
    void TitleTest1() {
        String inputTitle = "";

        Mockito.when(book.validateTitle(inputTitle)).thenReturn(false);
        assertFalse(book.validateTitle(inputTitle));
    }

    @Test
    void TitleTest2() {
        String inputTitle = "33451588654";

        Mockito.when(book.validateTitle(inputTitle)).thenReturn(false);
        assertFalse(book.validateTitle(inputTitle));
    }

    @Test
    void TitleTest3() {

        String inputTitle = "Il grande libro dei gialli di Natale"; //Lunghezza maggiore di 0, minore di 100 e caratteri validi

        Mockito.when(book.validateTitle(inputTitle)).thenReturn(true);
        assertTrue(book.validateTitle(inputTitle));
    }
}
