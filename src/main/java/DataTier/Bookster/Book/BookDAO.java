package DataTier.Bookster.Book;

import DataTier.Bookster.Lettore.Lettore;
import DataTier.Bookster.ConPool;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "LettoreDAO", value = "/LettoreDAO")
public class BookDAO extends HttpServlet {

    /**
     * Questo metodo salva un oggetto libro e aggiorna il riferimento al lettore nel database.
     * @param b L'oggetto libro da salvare nel database.
     * @param lettore L'oggetto lettore da salvare nel database.
     */
    public static void doSave(Book b, Lettore lettore){

        try(Connection connection= ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO libro(isbn,titolo,autore,pathcopertina,pagine)VALUES(?,?,?,?,?)");

            ps.setString(1,b.getIsbn());
            ps.setString(2,b.getTitle());
            ps.setString(3,b.getAuthor());
            ps.setString(4,b.getPath());
            ps.setInt(5,b.getPages());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Errore nel definire il libro");

            PreparedStatement psUser = connection.prepareStatement("INSERT INTO Libreria(username,,libro)VALUES(?,?)");
            ps.setString(1,lettore.getUsername());
            ps.setString(2,b.getIsbn());

            if (psUser.executeUpdate() != 1)
                throw new RuntimeException("Errore nel definire il libro");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}