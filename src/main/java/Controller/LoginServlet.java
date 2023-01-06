package Controller;

import Model.Lettore;
import Model.LettoreDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet( value = "/LoginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //Si verifica se il button di registrazione sia attivo nel caso si effettua il reindirizzamento alla pagina di registrazione
        String parameter=request.getParameter("buttonRegister");
        if(parameter!=null){
            if(parameter.equalsIgnoreCase("register")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
                dispatcher.forward(request, response);
            }
        }

        /*Si procede a recuperare i dati del form di login e si procede a verificare che ci sia corrispondenza nel db e nel caso reindirizza alla "homepage" come prova di test*/
        if(request.getParameter("email")!=null && request.getParameter("password")!=null) {
           Lettore lettore = LettoreDAO.doLogin(request.getParameter("email"), request.getParameter("password"));
           if(lettore!=null){
               RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
               dispatcher.forward(request, response);
           }
       }
    }
}
