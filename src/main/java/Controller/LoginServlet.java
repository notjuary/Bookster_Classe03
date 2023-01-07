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

        if(request.getParameter("email") != null && request.getParameter("password") != null) {
           Lettore lettore = LettoreDAO.doLogin(request.getParameter("email"), request.getParameter("password"));

           if(lettore != null)
               request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }
}
