package Model;

public class Libreria {
    private String titolo;
    private int numeroLibri;
    private int numeroPagineLette;
    private int numeroPagineTotale;


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getNumeroLibri() {
        return numeroLibri;
    }

    public void setNumeroLibri(int numeroLibri) {
        this.numeroLibri = numeroLibri;
    }

    public int getNumeroPagineLette() {
        return numeroPagineLette;
    }

    public void setNumeroPagineLette(int numeroPagineLette) {
        this.numeroPagineLette = numeroPagineLette;
    }

    public int getNumeroPagineTotale() {
        return numeroPagineTotale;
    }

    public void setNumeroPagineTotale(int numeroPagineTotale) {
        this.numeroPagineTotale = numeroPagineTotale;
    }
}
