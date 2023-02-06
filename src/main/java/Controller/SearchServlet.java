package Controller;

import Model.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {

    /**
     * Questo metodo viene utilizzato per gestire le richieste HTTP GET. Utilizza un parametro di ricerca
     * "selectionInput" per identificare il tipo di ricerca (ISBN, autore o titolo) e un parametro "searchBar"
     * per specificare il valore di ricerca. Se i parametri non soddisfano i criteri specificati
     * (lunghezza, formato), viene reindirizzato alla pagina index.jsp. Altrimenti, viene costruito un URL per
     * effettuare una richiesta a Google Books API per ottenere i risultati di ricerca. I risultati vengono quindi
     * elaborati per estrarre informazioni sul libro come ISBN, autore, titolo, categoria e data di pubblicazione.
     * Queste informazioni vengono quindi archiviate in una lista di oggetti libro e passate alla pagina jsp per
     * visualizzazione.
     * @param request La richiesta HTTP che contiene i parametri di ricerca
     * @param response La risposta HTTP che verrà inviata al client
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta
     * @throws IOException Se si verifica un errore d'ingresso/uscita durante l'elaborazione della richiesta
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Book> books = new ArrayList<>();

        String typeSearch = request.getParameter("selectionInput");
        String parameter = request.getParameter("searchBar");

        URL url;

        switch (typeSearch) {
            case "isbn": {
                if (parameter.length() != 10 && parameter.length() != 13)
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                else if (!parameter.matches("[0-9]+"))
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

                url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + parameter.replace(" ", "%20") + "&key=AIzaSyCf_5gM-QPraMtstDESRv_rxVJhUiJ_YP8");
                break;
            }
            case "author": {
                if (parameter.length() > 100 || parameter.length() < 1)
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                else if (!parameter.matches("^[a-zA-Z\\s]+$"))
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

                url = new URL("https://www.googleapis.com/books/v1/volumes?q=inauthor:" + parameter.replace(" ", "%20") + "&key=AIzaSyCf_5gM-QPraMtstDESRv_rxVJhUiJ_YP8");
                break;
            }
            case "title": {
                if (parameter.length() > 30 || parameter.length() < 1)
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                else if (!parameter.matches("^[a-zA-Z\\s0-9]+$"))
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

                url = new URL("https://www.googleapis.com/books/v1/volumes?q=intitle:" + parameter.replace(" ", "%20") + "&key=AIzaSyCf_5gM-QPraMtstDESRv_rxVJhUiJ_YP8");
                break;
            }
            default:
                url = new URL("");
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) json.append(line);
        reader.close();

        try {
            JSONArray items = new JSONObject(json.toString()).getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {

                JSONObject volumeInfo = items.getJSONObject(i).getJSONObject("volumeInfo");

                String isbn = "N/A";
                if (volumeInfo.has("industryIdentifiers")) {
                    JSONArray ids = volumeInfo.getJSONArray("industryIdentifiers");
                    for (int k = 0; k < ids.length(); k++) {
                        JSONObject id = ids.getJSONObject(k);
                        if (id.getString("type").equalsIgnoreCase("ISBN_13")) {
                            isbn = id.getString("identifier");
                            break;
                        }
                    }
                }

                String title = volumeInfo.has("title") ? volumeInfo.getString("title") : "N/A";

                String author = volumeInfo.has("authors") ?
                        volumeInfo.getJSONArray("authors").getString(0) : "N/A";

                String category = volumeInfo.has("categories") ?
                        volumeInfo.getJSONArray("categories").getString(0) : "N/A";

                Date year = Date.valueOf("2000-01-01");
                if (volumeInfo.has("publishedDate")) {
                    String publishedDate = volumeInfo.getString("publishedDate");
                    try {
                        year = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(publishedDate).getTime());
                    } catch (ParseException e) {
                        System.out.println("Error parsing date, using default");
                    }
                }

                String publisher = volumeInfo.has("publisher") ?
                        volumeInfo.getString("publisher") : "N/A";

                String path = "N/A";
                if (volumeInfo.has("imageLinks")) {
                    path = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
                }

                int pages = volumeInfo.has("pageCount") ?
                        volumeInfo.getInt("pageCount") : 0;

                String description = volumeInfo.has("description") ?
                        volumeInfo.getString("description") : "N/A";

                Book book = new Book(isbn, title, author, category, year, publisher, path, pages, description);
                books.add(book);
            }

            request.getSession().setAttribute("bookList", books);
            request.setAttribute("books", books);
            getServletConfig().getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
        } catch (JSONException e) {
            getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}