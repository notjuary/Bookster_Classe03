package LogicTier.Bookster.Login;

import DataTier.Bookster.Lettore.Lettore;
import DataTier.Bookster.Lettore.LettoreDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet( value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Questo metodo viene utilizzato per eseguire l'accesso del lettore tramite email e password.
     * Viene verificato se l'email corrisponde al pattern.
     * @param request La richiesta HTTP che contiene i dati inseriti dall'utente, tra cui l'email e la password.
     * @param response La risposta HTTP che conterrà i dati restituiti dal server.
     * @throws ServletException In caso di errori nell'esecuzione del servlet.
     * @throws IOException In caso di errori d'input o output.
     * @see Lettore
     * @see LettoreDAO
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

        if (!EMAIL_REGEX.matcher(email).matches()) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            Lettore l = LettoreDAO.doLogin(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("lettore", l);
            request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }
}
