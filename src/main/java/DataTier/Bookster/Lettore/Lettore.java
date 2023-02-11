package DataTier.Bookster.Lettore;

import java.sql.Date;

public class Lettore {

    private String username, psw,nome, cognome,email,telefono, genere;
    private Date ddn;
    private int punteggio;

    /**
     * Questo metodo restituisce il nome utente del Lettore.
     * @return Il nome utente del Lettore.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Questo metodo setta il nome utente del Lettore.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Questo metodo restituisce la password del Lettore.
     * @return La password del Lettore.
     */
    public String getPsw() {
        return psw;
    }

    /**
     * Questo metodo setta la password del Lettore.
     */
    public void setPsw(String psw) {
        this.psw = psw;
    }

    /**
     * Questo metodo restituisce il nome del Lettore.
     * @return Il nome del Lettore.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Questo metodo setta il nome del Lettore.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Questo metodo restituisce il cognome del Lettore.
     * @return Il cognome del Lettore.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Questo metodo setta il cognome del Lettore.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Questo metodo restituisce l'email del Lettore.
     * @return L'email del Lettore.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Questo metodo setta l'email del Lettore.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Questo metodo restituisce il numero di telefono del Lettore.
     * @return Il numero di telefono del Lettore.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Questo metodo setta il numero di telefono del Lettore.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Questo metodo restituisce il genere del Lettore.
     * @return Il genere del Lettore.
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Questo metodo setta il genere del Lettore.
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * Questo metodo restituisce la data di nascita del Lettore.
     * @return La data di nascita del Lettore.
     */
    public Date getDdn() {
        return ddn;
    }

    /**
     * Questo metodo setta la data di nascita del Lettore.
     */
    public void setDdn(java.sql.Date ddn) {
        this.ddn = ddn;
    }

    /**
     * Questo metodo restituisce il punteggio del Lettore.
     * @return Il punteggio del Lettore.
     */
    public int getPunteggio() {
        return punteggio;
    }

    /**
     * Questo metodo setta il punteggio del Lettore.
     */
    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public String toString() {
        return "Lettore{" +
                "username='" + username + '\'' +
                ", psw='" + psw + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", genere='" + genere + '\'' +
                ", ddn=" + ddn +
                ", punteggio=" + punteggio +
                '}';
    }
}
