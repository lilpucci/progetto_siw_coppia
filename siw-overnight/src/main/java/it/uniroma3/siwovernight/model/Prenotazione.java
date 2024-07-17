package it.uniroma3.siwovernight.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Prenotazione {
    
    /*ATTRIBUTI*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private int num_biglietti;
        
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    
    @ManyToOne 
    @JoinColumn(name = "utente_id")
    private Utente utente;
    /*FINE ATTRIBUTI*/


    /*GETTER & SETTER*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public int getNum_biglietti() {
        return num_biglietti;
    }
    public void setNum_biglietti(int num_biglietti) {
        this.num_biglietti = num_biglietti;
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
    
    public Prenotazione(Evento evento, Utente utente, int num_biglietti){
        this.num_biglietti = num_biglietti;
        this.evento = evento;
        this.utente = utente;
    }
    /*FINE COSTRUTTORI*/
    
}