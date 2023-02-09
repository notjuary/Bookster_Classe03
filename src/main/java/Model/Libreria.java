package Model;

public class Libreria {
    private String titolo;
    private int numeroLibri;
    private int numeroPagineLette;
    private int numeroPagineTotale;


    /**
     * Il metodo restituisce il titolo
     * @return titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Il metodo setta il titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Il metodo restituisce il numero di libri
     * @return numero libri
     */
    public int getNumeroLibri() {
        return numeroLibri;
    }

    /**
     * Il metodo setta il numero di libri
     */
    public void setNumeroLibri(int numeroLibri) {
        this.numeroLibri = numeroLibri;
    }

    /**
     * Il metodo restituisce il numero di pagine lette
     * @return numero pagine lette
     */
    public int getNumeroPagineLette() {
        return numeroPagineLette;
    }

    /**
     * Il metodo setta il numero di pagine lette
     */
    public void setNumeroPagineLette(int numeroPagineLette) {
        this.numeroPagineLette = numeroPagineLette;
    }

    /**
     * Il metodo restituisce il numero di pagine totale
     * @return numero pagine totale
     */
    public int getNumeroPagineTotale() {
        return numeroPagineTotale;
    }

    /**
     * Il metodo setta il numero delle pagine
     */
    public void setNumeroPagineTotale(int numeroPagineTotale) {
        this.numeroPagineTotale = numeroPagineTotale;
    }
}