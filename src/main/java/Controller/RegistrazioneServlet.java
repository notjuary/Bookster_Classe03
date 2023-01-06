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
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Lettore l=new Lettore();
        l.setUsername(request.getParameter("username"));
        l.setPsw(request.getParameter("password"));
        l.setNome(request.getParameter("firstName"));
        l.setCognome(request.getParameter("lastName"));
        l.setEmail(request.getParameter("email"));
        String date=request.getParameter("birthday");
        System.out.println(date);

        java.sql.Date dateUntil=Date.valueOf(date);
         l.setDdn(dateUntil);
        l.setTelefono(request.getParameter("phone"));
        l.setGenere(request.getParameter("gender"));
        l.setPunteggio(0);

        /*Il seguente blocco procede a verificare se l'email risulti essere già presente nel db e nel caso dovrebbe segnalare l'errore, mentre qui viene segnalato l'errore tramite una stampa per prova.
        Nel caso, procede a fare l'inerimento nel nuovo account e fa il reindirizzamento alla pagina di login */
        if(LettoreDAO.controlloEmail(request.getParameter("email"))){

            System.out.println("Email già presente");
        }else{
            LettoreDAO.doRegistrazione(l);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }

    }

    public void destroy() {
    }
}
