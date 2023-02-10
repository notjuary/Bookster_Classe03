package Model;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConPool {

    private static DataSource datasource;

    /**
     * La classe ConPool contiene il metodo getConnection che restituisce una connessione al database
     * Bookster sul server locale. La connessione viene ottenuta tramite un oggetto DataSource
     * configurato con un oggetto di tipo PoolProperties.
     * @return una connessione al database Bookster.
     * @throws SQLException in caso di errore durante la connessione al database.
     */
    public static Connection getConnection() throws SQLException {

        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/Bookster?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("qwerty123");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }

        return datasource.getConnection();
    }
}