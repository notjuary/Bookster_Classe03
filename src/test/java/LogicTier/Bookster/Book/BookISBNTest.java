package LogicTier.Bookster.Book;

import DataTier.Bookster.Book.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BookISBNTest {

    private static Book book;

    @BeforeAll
    public static void init() {
        book = Mockito.mock(Book.class);
    }

    @Test
    void ISBNTest1() {
        String inputISBN = "58039102"; //8 cifre

        Mockito.when(book.validateLenghtBookISBN(inputISBN)).thenReturn(false);
        assertFalse(book.validateLenghtBookISBN(inputISBN));
    }

    @Test
    void ISBNTest2() {
        String inputISBN = "58039102645"; //11 cifre

        Mockito.when(book.validateLenghtBookISBN(inputISBN)).thenReturn(false);
        assertFalse(book.validateLenghtBookISBN(inputISBN));
    }

    @Test
    void ISBNTest3() {
        String inputISBN = "58039102645451"; //14 cifre

        Mockito.when(book.validateLenghtBookISBN(inputISBN)).thenReturn(false);
        assertFalse(book.validateLenghtBookISBN(inputISBN));
    }

    @Test
    void ISBNTest4() {
        String inputISBN = "Manzoni1827"; //ISBN contenente caratteri non numerici

        Mockito.when(book.validateSyntaxBookISBN(inputISBN)).thenReturn(false);
        assertFalse(book.validateSyntaxBookISBN(inputISBN));
    }

    @Test
    void ISBNTest5() {
        String inputISBN = "Manzoni"; //ISBN contenente caratteri non numerici

        Mockito.when(book.validateSyntaxBookISBN(inputISBN)).thenReturn(false);
        assertFalse(book.validateSyntaxBookISBN(inputISBN));
    }

    @Test
    void ISBNTest6() {
        String inputISBN = "5803910265"; // 10 cifre

        Mockito.when(book.validateISBN(inputISBN)).thenReturn(true);
        assertTrue(book.validateISBN(inputISBN));
    }

    @Test
    void ISBNTest7() {
        String inputISBN = "5803910264547"; //13 cifre

        Mockito.when(book.validateISBN(inputISBN)).thenReturn(true);
        assertTrue(book.validateISBN(inputISBN));
    }
}