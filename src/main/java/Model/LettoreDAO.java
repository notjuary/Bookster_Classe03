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
}
