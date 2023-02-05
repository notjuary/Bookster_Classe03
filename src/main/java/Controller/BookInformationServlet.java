package Controller;

import Model.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet( value = "/BookInformationServlet")
public class BookInformationServlet extends HttpServlet {

    /**
     * Il metodo doGet gestisce la richiesta HTTP GET. Il metodo recupera l'indice del libro selezionato dai
     * parametri della richiesta; ottiene il libro dall'elenco dei libri memorizzato nella sessione; e imposta
     * il titolo del libro nella sessione e il libro stesso come attributo della richiesta e la inoltra alla
     * pagina di visualizzazione "bookPage.jsp".
     * @param request l'oggetto HttpServletRequest che contiene la richiesta che il client ha fatto alla servlet
     * @param response l'oggetto HttpServletResponse che contiene la risposta che la servlet invia al client
     * @throws IOException se viene rilevato un errore d'I/O nella gestione della richiesta
     * @see Book
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("libro"));
        ArrayList<Book> bookList = (ArrayList<Book>) request.getSession().getAttribute("bookList");
        Book book = bookList.get(index);
        request.getSession().setAttribute("title",book.getTitle());
        System.out.println(book.toString());
        request.setAttribute("book", book);
        String indirizzo = "bookPage.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(indirizzo);
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
