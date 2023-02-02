package Controller;

import Model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LibraryServlet", value = "/LibraryServlet")
public class LibraryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        List<Book> list= (List<Book>) session.getAttribute("bookList");
        Book b=(Book) session.getAttribute("libroSelezionato");
        System.out.println(b.toString());


        for(int i=0;i<list.size();i++){
            if(b.getTitle().equalsIgnoreCase(list.get(i).getTitle())){
                System.out.println(list.get(i).getTitle());
            }
        }
    }
}
