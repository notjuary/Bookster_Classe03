package Controller;

import Model.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Book> books = new ArrayList<>();

        String typeSearch = request.getParameter("selectionInput");
        String parameter = request.getParameter("searchBar");

        URL url;

        int i = 0;

        switch (typeSearch) {
            case "isbn": url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + parameter + "&key=AIzaSyCf_5gM-QPraMtstDESRv_rxVJhUiJ_YP8"); break;
            case "author": url = new URL("https://www.googleapis.com/books/v1/volumes?q=inauthor:" + parameter + "&key=AIzaSyCf_5gM-QPraMtstDESRv_rxVJhUiJ_YP8"); break;
            case "title": url = new URL("https://www.googleapis.com/books/v1/volumes?q=intitle:" + parameter + "&key=AIzaSyCf_5gM-QPraMtstDESRv_rxVJhUiJ_YP8"); break;
            default: url = new URL("");
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) json.append(line);
        reader.close();

        JSONArray items = new JSONObject(json.toString()).getJSONArray("items");
        for (i = 0; i < items.length(); i++) {

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

            String publisher = volumeInfo.has("publisher") ?
                    volumeInfo.getString("publisher") : "N/A";

            String path = "N/A";
            if(volumeInfo.has("imageLinks"))
                path = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");

            int pages = volumeInfo.has("pageCount") ?
                    volumeInfo.getInt("pageCount") : 0;

            Book book = new Book(isbn, title, author, category, year, publisher, path, pages);
            //System.out.println(book.toString());
            books.add(book);
        }

        request.setAttribute("books", books);
        getServletConfig().getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request,response);
    }
}
