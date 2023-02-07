package Controller;

import Model.Book;
import Model.Lettore;
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

@WebServlet(name = "LibraryServlet", value = "/LibraryServlet")
public class LibraryServlet extends HttpServlet {
    private int index;
    private Libreria libreria;
    /**
     * Questo metodo viene eseguito quando una richiesta GET viene inviata al servlet.
     * Il metodo esegue un'azione basata sul parametro "action" presente nella richiesta.
     * Se il parametro "action" è "libreria", viene chiamato il metodo doGetAddToLib.
     * Se il parametro "action" è "preferiti", viene chiamato il metodo doGetAddFavourite.
     * @param request Oggetto HttpServletRequest che rappresenta la richiesta al servlet
     * @param response Oggetto HttpServletResponse che rappresenta la risposta del servlet
     * @throws ServletException Eccezione che può essere lanciata durante l'elaborazione della richiesta
     * @throws IOException Eccezione che può essere lanciata durante la comunicazione tra client e server
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if(request.getSession().getAttribute("lettore")==null){
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }else {
        libreria = new Libreria();
        Lettore lettore = (Lettore) request.getSession().getAttribute("lettore");
        libreria.setTitolo("Libreria di: " + lettore.getUsername());

        String action = request.getParameter("action");
        String[] actionSplit = action.split(",");
        action = actionSplit[0];
        String temp = actionSplit[1];
        String[] indexSplit = temp.split("=");
        index = Integer.parseInt(indexSplit[1]);

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
     * Questo metodo viene eseguito quando l'utente fa una richiesta HTTP GET per aggiungere un libro alla libreria.
     * Il libro viene selezionato dalla lista di libri presenti e viene aggiunto alla lista della libreria nella
     * sessione HTTP.
     * Se la lista della libreria non esiste ancora, viene creata una nuova lista di libri.
     * Se il libro selezionato è già presente nella lista della libreria, non viene aggiunto nuovamente.
     * Alla fine, l'utente viene reindirizzato alla pagina della libreria.
     * @param request La richiesta HTTP
     * @param response La risposta HTTP
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta
     * @throws IOException Se si verifica un errore d'ingresso e d'uscita durante l'elaborazione della richiesta
     * @see Book
     */
    protected void doGetAddToLib(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> list = (List<Book>) session.getAttribute("bookList");



        if (session.getAttribute("libraryList") == null) {
            List<Book> librayList = new ArrayList<Book>();
            for (int i = 0; i < list.size(); i++) {
                if (i == index) {
                    librayList.add(list.get(i));

                    session.setAttribute("libraryList", librayList);
                }
            }
        }else {
            List<Book> libraryList = (List<Book>) session.getAttribute("libraryList");
            String isbn=null;
                for(int j=0;j< list.size();j++){
                    if(j==index){
                        isbn=list.get(j).getIsbn();
                    }
             }
                int indexList = isExisting(isbn, libraryList);

                    if(indexList==-1) {
                        for (int j = 0; j < list.size(); j++) {
                            if (j == index) {
                                libraryList.add(list.get(j));

                            }
                        }
                    }

            session.setAttribute("libraryList", libraryList);
        }


        List<Book> libraryList = (List<Book>) session.getAttribute("libraryList");
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
     * Questo metodo verifica se un libro esiste già nella lista di libri presenti nella libreria dell'utente.
     * @param isbn L'ISBN del libro da cercare.
     * @param libraryList La lista dei libri presenti nella libreria dell'utente.
     * @return L'indice del libro nella lista se esiste, altrimenti -1.
     * @see Book
     */
    private int isExisting(String isbn,List<Book> libraryList){
        if(libraryList!=null) {
            for (int i = 0; i < libraryList.size(); i++) {
                if (libraryList.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Questo metodo viene utilizzato per aggiungere un libro ai preferiti. Viene recuperata la sessione dalla
     * richiesta HTTP e viene ottenuta la lista di libri. Se la lista dei libri preferiti non esiste ancora,
     * viene creata una nuova lista e il libro viene aggiunto a essa. Altrimenti, se la lista esiste già, si
     * verifica se il libro è già presente nella lista. Se non è presente, viene aggiunto, altrimenti non viene
     * fatto nulla. Infine, viene impostata la lista dei libri preferiti come attributo della sessione e
     * viene fatto il forward alla pagina "libraryPage.jsp".
     * @param request La richiesta HTTP
     * @param response La risposta HTTP
     * @throws ServletException Eccezione lanciata nel caso in cui si verifichino errori durante l'elaborazione
     * della richiesta
     * @throws IOException Eccezione lanciata nel caso in cui si verifichino errori durante la scrittura della
     * risposta
     */
    protected void doGetAddFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> list = (List<Book>) session.getAttribute("bookList");


        if (session.getAttribute("libraryFavourite") == null) {
            List<Book> libraryFavourite = new ArrayList<Book>();
            for (int i = 0; i < list.size(); i++) {
                if (i == index) {
                    libraryFavourite.add(list.get(i));

                    session.setAttribute("libraryFavourite", libraryFavourite);
                }
            }
        }else {
            List<Book> libraryFavourite = (List<Book>) session.getAttribute("libraryFavourite");
            String isbn=null;
            for(int j=0;j< list.size();j++){
                if(j==index){
                    isbn=list.get(j).getIsbn();
                }
            }
            int indexList = isExisting(isbn, libraryFavourite);

            if(indexList==-1) {
                for (int j = 0; j < list.size(); j++) {
                    if (j == index) {
                        libraryFavourite.add(list.get(j));

                    }
                }
            }

            session.setAttribute("libraryFavourite", libraryFavourite);
        }


        List<Book> libraryFavourite = (List<Book>) session.getAttribute("libraryFavourite");
        Libreria lib= (Libreria) session.getAttribute("libreria");
        libreria.setNumeroLibri(lib.getNumeroLibri()+libraryFavourite.size());
        session.setAttribute("libreria",libreria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("libraryPage.jsp");
        dispatcher.forward(request, response);
    }
}