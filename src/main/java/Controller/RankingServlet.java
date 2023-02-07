package Controller;

import Model.Lettore;
import Model.LettoreDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "RankingServlet", value = "/RankingServlet")
public class RankingServlet extends HttpServlet {

 /**
  * Questo metodo viene eseguito per gestire le richieste HTTP GET.
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

        //getServletConfig().getServletContext().getRequestDispatcher("/ranking.jsp").forward(request, response);
    }
}
