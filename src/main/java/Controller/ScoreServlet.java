package Controller;

import Model.Lettore;
import Model.LettoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet( value = "/ScoreServlet")
public class ScoreServlet extends HttpServlet {

    /**
     * Il metodo gestisce una richiesta doGet. Il metodo recupera i valori "pages" e "seconds" dalla richiesta HTTP.
     * Se il tempo supera il numero di pagine moltiplicato per 10, viene effettuato un upgrade del punteggio del
     * lettore presente nella sessione.
     * @param request l'oggetto HttpServletRequest che contiene la richiesta che il client ha fatto alla servlet
     * @param response l'oggetto HttpServletResponse che contiene la risposta che la servlet invia al client
     * @throws IOException se viene rilevato un errore d'I/O nella gestione della richiesta
     * @see Lettore
     * @see LettoreDAO
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pages = Integer.parseInt(request.getParameter("pages"));
        int time = Integer.parseInt(request.getParameter("seconds"));

        if (time > pages * 10) {
            Lettore lettore = (Lettore) request.getSession().getAttribute("lettore");
            LettoreDAO.upgradePunteggio(lettore.getUsername(), lettore.getPunteggio() + pages);
        }
    }
}
