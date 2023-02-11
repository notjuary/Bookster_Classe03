package LogicTier.Bookster.Book;

import DataTier.Bookster.Book.Book;
import DataTier.Bookster.Book.BookDAO;
import DataTier.Bookster.Lettore.Lettore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet( value = "/InsertBookServlet")
public class InsertBookServlet extends HttpServlet {

    /**
     * Il metodo doGet gestisce la richiesta HTTP GET. Questo metodo recupera il ISBN, il titolo, l'autore e
     * il numero di pagine di un libro dai parametri della richiesta. Quindi, verifica che le lunghezze dello ISBN,
     * del titolo, dell'autore e del numero di pagine siano valide e che soddisfino i criteri di formato stabiliti.
     * Infine, crea un nuovo libro con questi dettagli e lo salva nel database tramite il BookDAO.
     * @param request la richiesta HTTP GET effettuata dal client
     * @param response la risposta inviata dalla servlet al client
     * @throws ServletException se la richiesta non può essere gestita
     * @throws IOException se viene rilevato un errore d'input o output durante la gestione della richiesta
     * @see Book
     * @see BookDAO
     * @see Lettore
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int numberPage = Integer.parseInt(request.getParameter("numberPage"));

        if (isbn.length() != 13 && isbn.length() != 10)
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        else if (!isbn.matches("[0-9]+"))
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        if (title.length() > 100 || title.length() < 1)
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        else if (!title.matches("^[a-zA-Z\\s]+$"))
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        if (author.length() > 30 || author.length() < 1)
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        else if (!author.matches("^[a-zA-Z\\s0-9]+$"))
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        if (numberPage <= 0)
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        else if (!String.valueOf(numberPage).matches("[0-9]+"))
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        Book book = new Book(isbn, title, author, "resources/noimage.png", numberPage);
        Lettore lettore = (Lettore) request.getSession().getAttribute("lettore");

        BookDAO.doSave(book, lettore);
    }
}
