package LogicTier.Bookster.Book;

import DataTier.Bookster.Book.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTitleTest {

    private static Book book;

    @BeforeAll
    public static void init() {
        book = Mockito.mock(Book.class);
    }

    @Test
    void TitleTest1() {
        String inputTitle = ""; //Lunghezza pari a 0

        Mockito.when(book.validateLenghtBookTitle(inputTitle)).thenReturn(false);
        assertFalse(book.validateLenghtBookTitle(inputTitle));
    }

    @Test
    void TitleTest2() { //Lunghezza maggiore di 100
        String inputTitle = "Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione. È sopravvissuto non solo a più di cinque secoli, ma anche al passaggio alla videoimpaginazione, pervenendoci sostanzialmente inalterato. Fu reso popolare, negli anni ’60, con la diffusione dei fogli di caratteri trasferibili “Letraset”, che contenevano passaggi del Lorem Ipsum, e più recentemente da software di impaginazione come Aldus PageMaker, che includeva versioni del Lorem Ipsum.";

        Mockito.when(book.validateLenghtBookTitle(inputTitle)).thenReturn(false);
        assertFalse(book.validateLenghtBookTitle(inputTitle));
    }

    @Test
    void TitleTest3() {
        String inputTitle = "33451588654"; //Lunghezza maggiore di 0, minore di 100 e caratteri non validi

        Mockito.when(book.validateTitle(inputTitle)).thenReturn(false);
        assertFalse(book.validateTitle(inputTitle));
    }

    @Test
    void TitleTest4() {

        String inputTitle = "Il grande libro dei gialli di Natale"; //Lunghezza maggiore di 0, minore di 100 e caratteri validi

        Mockito.when(book.validateTitle(inputTitle)).thenReturn(true);
        assertTrue(book.validateTitle(inputTitle));
    }
}
