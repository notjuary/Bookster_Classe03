package LogicTier.Bookster.Libreria;

import DataTier.Bookster.Book.Book;
import LogicTier.Bookster.Signup.SignupSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ManuallyInsertTest {

    private static Book book;

    @BeforeAll
    public static void init() {
        book = Mockito.mock(Book.class);
    }

    @Test
    void InsertISBNTest1() {
        String inputISBN = "88310033"; //8 cifre

        Mockito.when(book.validateInsert(inputISBN)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN));
    }

    @Test
    void InsertISBNTest2() {
        String inputISBN = "883100k380"; //10 cifre ma caratteri non accettati

        Mockito.when(book.validateInsert(inputISBN)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN));
    }

    @Test
    void InsertISBNTest3() {
        String inputISBN = "8831003380"; //ISBN Corretto

        Mockito.when(book.validateInsert(inputISBN)).thenReturn(true);
        assertTrue(book.validateInsert(inputISBN));
    }

    @Test
    void InsertTitleTest4() {
        String inputISBN = "8831003380";
        String inputTitle = "H@rry Potter";

        Mockito.when(book.validateInsert(inputISBN, inputTitle)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN, inputTitle));
    }

    @Test
    void InsertTitleTest5() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";

        Mockito.when(book.validateInsert(inputISBN, inputTitle)).thenReturn(true);
        assertTrue(book.validateInsert(inputISBN, inputTitle));
    }

    @Test
    void InsertAuthorTest6() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";
        String inputAuthor = "";

        Mockito.when(book.validateInsert(inputISBN, inputTitle, inputAuthor)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN, inputTitle, inputAuthor));
    }

    @Test
    void InsertAuthorTest7() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";
        String inputAuthor = "J. K. Rowling-";

        Mockito.when(book.validateInsert(inputISBN, inputTitle, inputAuthor)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN, inputTitle, inputAuthor));
    }

    @Test
    void InsertAuthorTest8() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";
        String inputAuthor = "J. K. Rowling";

        Mockito.when(book.validateInsert(inputISBN, inputTitle, inputAuthor)).thenReturn(true);
        assertTrue(book.validateInsert(inputISBN, inputTitle, inputAuthor));
    }

    @Test
    void InsertPagesTest8() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";
        String inputAuthor = "J. K. Rowling";
        String inputNumberPages = "0";

        Mockito.when(book.validateInsert(inputISBN, inputTitle, inputAuthor, inputNumberPages)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN, inputTitle, inputAuthor, inputNumberPages));
    }

    @Test
    void InsertPagesTest9() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";
        String inputAuthor = "J. K. Rowling";
        String inputNumberPages = "2k";

        Mockito.when(book.validateInsert(inputISBN, inputTitle, inputAuthor, inputNumberPages)).thenReturn(false);
        assertFalse(book.validateInsert(inputISBN, inputTitle, inputAuthor, inputNumberPages));
    }

    @Test
    void InsertPagesTest10() {
        String inputISBN = "8831003380";
        String inputTitle = "Harry Potter e la pietra filosofale";
        String inputAuthor = "J. K. Rowling";
        String inputNumberPages = "320";

        Mockito.when(book.validateInsert(inputISBN, inputTitle, inputAuthor, inputNumberPages)).thenReturn(true);
        assertTrue(book.validateInsert(inputISBN, inputTitle, inputAuthor, inputNumberPages));
    }
}
