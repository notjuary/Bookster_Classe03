package Controller;

import Model.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LibraryBookInfoServlet", value = "/LibraryBookInfoServlet")
public class LibraryBookInfoServlet  extends HttpServlet {
    private String title;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        title=(String) request.getSession().getAttribute("title");


        if (action != null) {
            if (action.equalsIgnoreCase("libreria")) {
                doGetAddToLib(request, response);
            } else if (action.equalsIgnoreCase("preferiti")) {
                doGetAddFavourite(request, response);
            }
        }
    }

    protected void doGetAddToLib(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> list = (List<Book>) session.getAttribute("bookList");

        if (session.getAttribute("libraryList") == null) {
            List<Book> librayList = new ArrayList<Book>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIsbn().compareTo(title)==0) {
                    librayList.add(list.get(i));
                    System.out.println(list.get(i).getTitle());
                    session.setAttribute("libraryList", librayList);
                }
            }
        } else {
            List<Book> libraryList = (List<Book>) session.getAttribute("libraryList");
            String isbnList = null;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getTitle().compareTo(title)==0) {
                    isbnList = list.get(j).getIsbn();
                }
            }
            int indexList = isExisting(isbnList, libraryList);

            if (indexList == -1) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getTitle().compareTo(title)==0) {
                        libraryList.add(list.get(j));
                        System.out.println(list.get(j).getTitle());

                    }
                }
            }
            session.setAttribute("libraryList", libraryList);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("libraryPage.jsp");
        dispatcher.forward(request, response);

    }

    private int isExisting(String isbnList, List<Book> libraryList) {
        if (libraryList != null) {
            for (int i = 0; i < libraryList.size(); i++) {
                if (libraryList.get(i).getIsbn().equalsIgnoreCase(isbnList)) {
                    return i;
                }
            }
        }
        return -1;
    }

    protected void doGetAddFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> list = (List<Book>) session.getAttribute("bookList");


        if (session.getAttribute("libraryFavourite") == null) {
            List<Book> libraryFavourite = new ArrayList<Book>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTitle().compareTo(title)==0) {
                    libraryFavourite.add(list.get(i));

                    session.setAttribute("libraryFavourite", libraryFavourite);
                }
            }
        }else {
            List<Book> libraryFavourite = (List<Book>) session.getAttribute("libraryFavourite");
            String isbn=null;
            for(int j=0;j< list.size();j++){
                if(list.get(j).getTitle().compareTo(title)==0){
                    isbn=list.get(j).getIsbn();
                }
            }
            int indexList = isExisting(isbn, libraryFavourite);

            if(indexList==-1) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getTitle().compareTo(title)==0) {
                        libraryFavourite.add(list.get(j));

                    }
                }
            }

            session.setAttribute("libraryFavourite", libraryFavourite);
        }



        RequestDispatcher dispatcher = request.getRequestDispatcher("libraryPage.jsp");
        dispatcher.forward(request, response);
    }

}