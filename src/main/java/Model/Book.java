package Model;

import java.sql.Date;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String category;
    private Date year;
    private String publisher;
    private String path;
    private int pages;
    private String description;

    public Book(){};

    /**
     * Costruttore della classe {@link Book}.
     * @param isbn Il codice ISBN del libro.
     * @param title Il titolo del libro.
     * @param author L'autore del libro.
     * @param path Il percorso del file del libro.
     * @param pages Il numero di pagine del libro.
     */
    public Book(String isbn, String title, String author, String path, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = "N/A";
        this.year = Date.valueOf("2000-01-01");
        this.publisher = "N/A";
        this.path = path;
        this.pages = pages;
        this.description = "N/A";
    }

    /**
     * Questa classe rappresenta un oggetto libro.
     * @param isbn Codice ISBN del libro
     * @param title Titolo del libro
     * @param author Autore del libro
     * @param category Categoria del libro
     * @param year Anno di pubblicazione del libro
     * @param publisher Casa editrice del libro
     * @param path Percorso del file del libro
     * @param pages Numero di pagine del libro
     */
     public Book(String isbn, String title, String author, String category, Date year, String publisher, String path, int pages, String description) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.category = category;
            this.year = year;
            this.publisher = publisher;
            this.path = path;
            this.pages = pages;
            this.description = description;
     }

    /**
     * Questo metodo restituisce il valore dell'ISBN.
     * @return Il valore dell'ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Questo metodo setta il valore dell'ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Questo metodo restituisce il titolo del libro
     * @return Il titolo del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Questo metodo setta il titolo del libro
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Questo metodo restituisce l'autore del libro
     * @return L'autore del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Questo metodo setta l'autore del libro
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Questo metodo restituisce la categoria del libro
     * @return La categoria del libro.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Questo metodo setta la categoria del libro
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Questo metodo restituisce la data di pubblicazione del libro
     * @return La data di pubblicazione del libro
     */
    public Date getYear() {
        return year;
    }

    /**
     * Questo metodo setta la data di pubblicazione del libro
     */
    public void setYear(Date year) {
        this.year = year;
    }

    /**
     * Questo metodo restituisce la casa editrice del libro
     * @return La casa editrice del libro
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Questo metodo setta la casa editrice del libro
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Questo metodo restituisce la copertina del libro
     * @return La copertina del libro
     */
    public String getPath() {
        return path;
    }

    /**
     * Questo metodo setta la copertina del libro
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Questo metodo restituisce il numero di pagine del libro
     * @return Il numero di pagine del libro
     */
    public int getPages() {
        return pages;
    }

    /**
     * Questo metodo setta il numero di pagine del libro
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Questo metodo restituisce la descrizione del libro
     * @return La descrizione del libro
     */
    public String getDescription() {
        return description;
    }

    /**
     * Questo metodo setta la descrizione del libro
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                ", path='" + path + '\'' +
                ", pages=" + pages +
                ", description='" + description + '\'' +
                '}';
    }
}
