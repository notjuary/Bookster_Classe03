package Model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "LettoreDAO", value = "/LettoreDAO")
public class BookDAO extends HttpServlet {

    public static void doSave(Book b){

        try(Connection connection= ConPool.getConnection()) {

            PreparedStatement ps= connection.prepareStatement("INSERT INTO libro(isbn,titolo,autore,pathcopertina,pagine)VALUES(?,?,?,?,?)");

            ps.setString(1,b.getIsbn());
            ps.setString(2,b.getTitle());
            ps.setString(3,b.getAuthor());
            ps.setString(4,b.getPath());
            ps.setInt(5,b.getPages());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Errore nel definire il libro");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
