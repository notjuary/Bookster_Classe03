package LogicTier.Bookster.Registrazione;

import DataTier.Bookster.Lettore.Lettore;
import DataTier.Bookster.Lettore.LettoreDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

    /**
     * Questo metodo gestisce la richiesta HTTP POST per la registrazione di un nuovo utente nel sistema.
     * Il metodo recupera i parametri di richiesta necessari per la registrazione di un utente, ovvero username,
     * nome, cognome, data di nascita, email, password, conferma password, numero di telefono. Viene quindi
     * effettuata una serie di controlli di validità sui dati inseriti (ad esempio, lunghezza di nome e cognome,
     * validità dell'email, formato del numero di telefono, formato della password). Se i controlli di validità
     * falliscono, viene reindirizzato l'utente alla pagina di registrazione. Altrimenti, viene creato un nuovo
     * oggetto Lettore con i dati inseriti dall'utente e viene eseguito il metodo doRegistrazione del relativo
     * DAO per registrare l'utente nel sistema.
     * @param request Oggetto HttpServletRequest per la richiesta HTTP.
     * @param response Oggetto HttpServletResponse per la risposta HTTP.
     * @throws IOException Se si verifica un errore d'ingresso e di uscita durante l'elaborazione della richiesta.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @see Lettore
     * @see LettoreDAO
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Date dateOfBirth = Date.valueOf(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phoneNumber = request.getParameter("phone");

        if (name.length() < 2 || name.length() > 30 || lastName.length() < 2 || lastName.length() > 30) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }

        if (dateOfBirth.toString().equals("")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }

        LocalDate today = LocalDate.now();
        Period age = Period.between(LocalDate.parse(dateOfBirth.toString()), today);
        if (age.getYears() < 12) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }

        final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        if (!EMAIL_REGEX.matcher(email).matches()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }

        if (phoneNumber != null && phoneNumber.length() <= 20)
            if (!phoneNumber.matches("\\d+")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
                dispatcher.forward(request, response);
            }

        if (username != null && username.length() <= 20)
            if (!username.matches("^[a-zA-Z0-9]+$")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
                dispatcher.forward(request, response);
            }

        boolean hasNumber = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasNumber = true;
            }
        }

        boolean hasLength = password.length() >= 8 && password.length() <= 20;

        boolean hasUpperCase = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                hasUpperCase = true;
            }
        }

        boolean hasSpecialChatacters = false;
        String specialCharacters = "$!-_#*";
        for (int i = 0; i < password.length(); i++) {
            if (specialCharacters.indexOf(password.charAt(i)) != -1) {
                hasSpecialChatacters = true;
            }
        }

        boolean isEqual = password.equals(confirmPassword);

        if (!hasNumber || !hasLength || !hasSpecialChatacters || !hasUpperCase || !isEqual) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }

        Lettore l = new Lettore();
        l.setUsername(username);
        l.setPsw(password);
        l.setNome(name);
        l.setCognome(lastName);
        l.setEmail(email);
        l.setDdn(dateOfBirth);
        l.setTelefono(phoneNumber);
        l.setPunteggio(0);
        l.setGenere("none");

        if (LettoreDAO.controlloEmail(request.getParameter("email"))){
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }

        else {
            LettoreDAO.doRegistrazione(l);
            HttpSession session = request.getSession();
            session.setAttribute("lettore", l);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
