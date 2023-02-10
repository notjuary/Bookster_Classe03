package Model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LettoreDAO", value = "/LettoreDAO")
public class LettoreDAO extends HttpServlet {

    /**
     * Effettua il login di un utente.
     * @param username l'username inserito dall'utente
     * @param password la password inserita dall'utente
     * @return il {@link Lettore} associato all'username e password, se esiste, altrimenti {@code null}
     * @throws RuntimeException in caso di errore di connessione al database
     */
    public static Lettore doLogin(String username,String password){

        Lettore l = new Lettore();
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Lettore WHERE email = ? AND psw = SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                l.setUsername(rs.getString(1));
                l.setPsw(rs.getString(2));
                l.setNome(rs.getString(3));
                l.setCognome(rs.getString(4));
                l.setEmail(rs.getString(5));
                l.setDdn(rs.getDate(6));
                l.setTelefono(rs.getString(7));
                l.setGenere(rs.getString(8));
                l.setPunteggio(rs.getInt(9));

                return l;
            }

            return null;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo viene utilizzato per registrare un nuovo utente nel sistema.
     * Crea una nuova connessione al database con l'aiuto della classe ConPool e quindi esegue una query SQL per inserire i dettagli del nuovo utente.
     * In caso di errore nell'inserimento dei dettagli, viene sollevata una eccezione di tipo RuntimeException.
     * @param l Lettore - L'oggetto Lettore che rappresenta il nuovo utente da registrare
     * @throws RuntimeException in caso di errore nel definire il nuovo utente
     */
    public static void doRegistrazione(Lettore l){

        try(Connection connection= ConPool.getConnection()){

            PreparedStatement ps= connection.prepareStatement("INSERT INTO Lettore(username,psw,nome,cognome,email,ddn,telefono,genere,punteggio)VALUES(?,?,?,?,?,?,?,?,?)");

            ps.setString(1,l.getUsername());
            ps.setString(2,l.getPsw());
            ps.setString(3,l.getNome());
            ps.setString(4,l.getCognome());
            ps.setString(5,l.getEmail());
            ps.setDate(6,l.getDdn());
            ps.setString(7,l.getTelefono());
            ps.setString(8,l.getGenere());
            ps.setInt(9,l.getPunteggio());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Errore nel definire il lettore");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo recupera tutti i dati degli utenti registrati nel sistema.
     * @return Una lista di oggetti di tipo Lettore, che rappresentano tutti gli utenti registrati.
     * @throws RuntimeException in caso di errore nell'esecuzione della query.
     */
    public static List<Lettore> doRetriveUtente() {

        List<Lettore> listLettore = new ArrayList<Lettore>();

        try (Connection connection = ConPool.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Lettore");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lettore l = new Lettore();
                l.setUsername(rs.getString(1));
                l.setPsw(rs.getString(2));
                l.setNome(rs.getString(3));
                l.setCognome(rs.getString(4));
                l.setEmail(rs.getString(5));
                l.setDdn(rs.getDate(6));
                l.setTelefono(rs.getString(7));
                l.setGenere(rs.getString(8));
                l.setPunteggio(rs.getInt(9));
                listLettore.add(l);
            }

            return listLettore;
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo controlla se l'email specificata esiste già nel database dei lettori.
     * @param email L'email da verificare.
     * @return True se l'email esiste già nel database, False altrimenti.
     * @throws RuntimeException Se si verifica un'eccezione SQL durante l'esecuzione della query.
     */
    public static boolean controlloEmail(String email){

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT email FROM Lettore WHERE email=?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questa funzione si occupa di aggiornare il punteggio di un utente nel sistema.
     * @param username Il nome utente del lettore
     * @param punteggio Il valore di punteggio da aggiungere al punteggio esistente
     */
    public static void upgradePunteggio(String username, int punteggio) {

        try (Connection connection = ConPool.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("UPDATE Lettore SET punteggio = punteggio + ? WHERE username = ?");
            ps.setInt(1, punteggio);
            ps.setString(2, username);

            int result = ps.executeUpdate();
            if (result == 0) {
                System.out.println("User not found");
            } else {
                System.out.println("User's score upgraded");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
