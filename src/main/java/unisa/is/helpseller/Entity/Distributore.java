package unisa.is.helpseller.Entity;


import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "distributore")
public class Distributore implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String username;    
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String vat;
    private String telefono;
    private String indirizzo_sede;
    private int id_ordine_prova;
    
    public Distributore() {}

    public Distributore(String username, String email, String password, 
            String nome, String cognome, String vat, String telefono, 
            String indirizzo_sede, int id_ordine_prova) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.vat = vat;
        this.telefono = telefono;
        this.indirizzo_sede = indirizzo_sede;
        this.id_ordine_prova = id_ordine_prova;
    }

    public int getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getvat() {
        return vat;
    }

    public void setvat(String vat) {
        this.vat = vat;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzoSede() {
        return indirizzo_sede;
    }

    public void setIndirizzoSede(String indirizzo_sede) {
        this.indirizzo_sede = indirizzo_sede;
    }

    public int getIdOrdineProva() {
        return id_ordine_prova;
    }

    public void setIdOrdineProva(int id_ordine_prova) {
        this.id_ordine_prova = id_ordine_prova;
    }
    
    
}
