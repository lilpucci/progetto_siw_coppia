package it.uniroma3.siwovernight.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
public class Artista {

    /*ATTRIBUTI ARTISTA*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    
    @ElementCollection
    private List<Immagine> immagini;
    
    
    @Past
    @DateTimeFormat(pattern="dd-MM_yyyy")
    private LocalDate dataNascita;
    
    @ManyToMany(mappedBy = "artisti")
    private Set<Evento> eventi = new HashSet<>();
    /*FINE ATTRIBUTI ARTISTA*/


    /*GETTERS & SETTERS*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public List<Immagine> getImmagini() {
        return immagini;
    }
    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }
    public LocalDate getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
    public Set<Evento> getEventi() {
        return eventi;
    }
    public void setEventi(Set<Evento> eventi) {
        this.eventi = eventi;
    }
    /*FINE GETTERS & SETTERS*/


    /*EQUALS & HASHCODE*/
    @Override
    public int hashCode() {
        return Objects.hash(nome,cognome,dataNascita);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Artista other = (Artista) obj;
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
        if (dataNascita == null) {
            if (other.dataNascita != null)
                return false;
        } else if (!dataNascita.equals(other.dataNascita))
            return false;
        return true;
    }
    /*FINE EQUALS & HASHCODE*/


    /*COSTRUTTORI*/
    public Artista() {
    
    }

    public Artista(String nome, String cognome, LocalDate dataNascita, List<Immagine> immagini){
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.immagini = immagini;
    }
    //TODO toString()
    /*FINE COSTRUTTORI*/
    
}
    