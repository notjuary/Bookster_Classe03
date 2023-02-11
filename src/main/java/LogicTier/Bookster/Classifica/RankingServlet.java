package LogicTier.Bookster.Classifica;

import DataTier.Bookster.Lettore.Lettore;
import DataTier.Bookster.Lettore.LettoreDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "RankingServlet", value = "/RankingServlet")
public class RankingServlet extends HttpServlet {

    /**
     * Recupera l'elenco dei lettori dal database e ordina la classifica in base al punteggio.
     * Imposta la classifica come attributo della richiesta e la include nella pagina di classifica
    * @param request La richiesta HTTP ricevuta dal client.
    * @param response La risposta HTTP che verrà inviata al client.
    * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
    * @see Lettore
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Lettore> ranking = LettoreDAO.doRetriveUtente();

        ranking.sort(new Comparator<Lettore>() {
            @Override
            public int compare(Lettore o1, Lettore o2) {
                return Integer.compare(o2.getPunteggio(), o1.getPunteggio());
            }
        });

        request.setAttribute("ranking", ranking);

        String indirizzo = "classifica.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(indirizzo);
        requestDispatcher.include(request, response);
    }
}
