package Controller;

import Model.Book;
import Model.BookDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet( value = "/InsertBookServlet")
public class InsertBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int numberPage = Integer.parseInt(request.getParameter("numberPage"));*/

        String isbn = "1234567890123";
        String title = "titolo a cas";
        String author = "Autore a caso";
        int numberPage = 7;

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

        BookDAO.doSave(book);
    }
}
