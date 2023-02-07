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
