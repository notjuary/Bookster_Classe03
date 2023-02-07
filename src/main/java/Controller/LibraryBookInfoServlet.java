package Controller;

import Model.Book;
import Model.Libreria;
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

    /**
     * Questo metodo gestisce la richiesta doGet .Verifica se l'azione richiesta dall'utente è "libreria" o
     * "preferiti" tramite il parametro "action" della richiesta HTTP. Se l'azione è "libreria" oppure è
     * "preferiti".
     * @param request La richiesta HTTP
     * @param response La risposta HTTP
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta
     * @throws IOException Se si verifica un errore d'ingresso o uscita durante l'elaborazione della richiesta
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("lettore")==null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }else {
            String action = request.getParameter("action");
            title = (String) request.getSession().getAttribute("title");

            if (action != null) {
                if (action.equalsIgnoreCase("libreria")) {
                    doGetAddToLib(request, response);
                } else if (action.equalsIgnoreCase("preferiti")) {
                    doGetAddFavourite(request, response);
                }
            }
        }
    }

    /**
     * Questo metodo gestisce l'aggiunta di un libro alla libreria dell'utente. Recupera la sessione e la
     * lista di libri disponibili e controlla se la libreria dell'utente è già stata creata. Se non è stata
     * creata, viene creata una nuova lista di libreria e viene aggiunto il libro corrispondente al titolo
     * specificato. Se la libreria esiste già, viene controllato se il libro esiste già nella lista utilizzando
     * il metodo "isExisting". Se non esiste, viene aggiunto alla libreria. Infine, viene impostata la libreria
     * come attributo di sessione e viene reindirizzato l'utente alla pagina "libraryPage.jsp".
     * @param request La richiesta HTTP
     * @param response La risposta HTTP
     * @throws ServletException Se c'è un errore durante l'elaborazione della richiesta
     * @throws IOException Se c'è un errore d'input/output durante la gestione della richiesta
     * @see Book
     */
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

        List<Book> libraryList = (List<Book>) session.getAttribute("libraryList");
        Libreria libreria=(Libreria) session.getAttribute("libreria");
        if(libraryList==null){
            libreria.setNumeroLibri(0);
        }else {
            libreria.setNumeroLibri(libraryList.size());
        }
            session.setAttribute("libreria",libreria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("libraryPage.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Questo metodo verifica se un libro con un determinato ISBN esiste già nella lista della libreria.
     * @param isbnList ISBN del libro da cercare
     * @param libraryList Lista della libreria
     * @return Indice della posizione del libro se esiste, -1 altrimenti
     * @see Book
     */
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

    /**
     * Questo metodo si occupa di gestire l'aggiunta di un libro ai preferiti di un utente. Viene effettuato
     * il controllo se l'elenco dei libri preferiti è già stato creato o meno. In caso negativo, viene creato
     * un nuovo elenco e viene inserito il libro desiderato. In caso contrario, viene verificato se il libro
     * è già presente nell'elenco, se non lo è viene inserito. Infine, viene reindirizzato l'utente alla pagina
     * "libraryPage.jsp".
     * @param request La richiesta HTTP
     * @param response La risposta HTTP
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta
     * @throws IOException Se si verifica un errore di I/O durante la risposta HTTP
     * @see Book
     */
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
        List<Book> libraryFavourite = (List<Book>) session.getAttribute("libraryFavourite");
        Libreria libreria= (Libreria) session.getAttribute("libreria");
        libreria.setNumeroLibri(libreria.getNumeroLibri()+libraryFavourite.size());
        session.setAttribute("libreria",libreria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("libraryPage.jsp");
        dispatcher.forward(request, response);
    }
}