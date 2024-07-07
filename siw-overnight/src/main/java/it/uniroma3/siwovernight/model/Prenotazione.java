package it.uniroma3.siwovernight.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prenotazione {
    
    /*ATTRIBUTI*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //questi due si potrebbero togliere
    private String descrizione;
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    
    @ManyToOne 
    private Utente utente;
    /*FINE ATTRIBUTI*/


    /*GETTER & SETTER*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public Utente getUtente() {
        return utente;
    }
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    /*FINE GETTER & SETTER*/


    /*EQUALS & HASHCODE*/
    @Override
    public int hashCode() {
        return Objects.hash(utente,evento);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null)
        return false;
        if (getClass() != obj.getClass())
        return false;
        Prenotazione other = (Prenotazione) obj;
        if (id == null) {
            if (other.id != null)
            return false;
        } else if (!id.equals(other.id))
        return false;
        if (descrizione == null) {
            if (other.descrizione != null)
            return false;
        } else if (!descrizione.equals(other.descrizione))
        return false;
        if (url == null) {
            if (other.url != null)
            return false;
        } else if (!url.equals(other.url))
        return false;
        if (evento == null) {
            if (other.evento != null)
            return false;
        } else if (!evento.equals(other.evento))
        return false;
        if (utente == null) {
            if (other.utente != null)
            return false;
        } else if (!utente.equals(other.utente))
        return false;
        return true;
    }
    /*FINE EQUALS & HASHCODE*/


    /*COSTRUTTORI*/
    public Prenotazione() {
    
    }
    
    public Prenotazione(String descrizione, String url, Evento evento, Utente utente){
        this.descrizione = descrizione;
        this.url = url;
        this.evento = evento;
        this.utente = utente;
    }
    /*FINE COSTRUTTORI*/
    
}