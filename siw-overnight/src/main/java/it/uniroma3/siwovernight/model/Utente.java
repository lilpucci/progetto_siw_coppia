package it.uniroma3.siwovernight.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utente {

    /*ATTRIBUTI UTENTE*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDate dataNascita;

    private String nome;
    
    private String cognome;
    
    /*FINE ATTRIBUTI UTENTE*/


    /*GETTER & SETTER*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /*FINE GETTER & SETTER*/
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    /*EQUALS & HASHCODE*/
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utente other = (Utente) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        return true;
    }
    
    /*FINE EQUALS & HASHCODE*/




}
