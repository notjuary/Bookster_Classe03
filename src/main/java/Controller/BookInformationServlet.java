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
