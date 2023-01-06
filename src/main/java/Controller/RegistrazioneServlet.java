package Controller;

import Model.Lettore;
import Model.LettoreDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date dateOfBirth = Date.valueOf(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = document.getElementById('confirmPassword').value;

        Lettore l=new Lettore();
        l.setUsername();
        l.setPsw(password);
        l.setNome();
        l.setCognome();
        l.setEmail(email);
        l.setDdn(dateOfBirth);
        l.setTelefono();
        l.setGenere();
        l.setPunteggio(0);

        if (LettoreDAO.controlloEmail(request.getParameter("email"))){
            System.out.println("Email già presente");
        }

        else {
            LettoreDAO.doRegistrazione(l);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }
    }
}
