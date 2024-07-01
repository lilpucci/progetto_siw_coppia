package it.uniroma3.siwovernight.model;

import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Evento {
    
    /*ATTRIBUTI EVENTO*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titoloEvento;
    private float prezzo;
    private String descr; 
    
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDate dataEvento;
    
    //@DateTimeFormat(pattern = "hh:mm")
    //private LocalTime orarioInizio;

    @ManyToOne
    @JoinColumn(name = "locale_id")
    private Locale locale;
    
    @ManyToMany
    @JoinTable(
        name = "evento_artista",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private Set<Artista> artisti = new HashSet<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
    /*FINE ATTRIBUTI EVENTO*/

    
    /*GETTER & SETTER*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitoloEvento() {
        return titoloEvento;
    }
    public void setTitoloEvento(String titoloEvento) {
        this.titoloEvento = titoloEvento;
    }
    public Locale getLocale() {
        return locale;
    }
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    public LocalDate getDataEvento() {
        return dataEvento;
    }
    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public Set<Artista> getArtisti() {
        return artisti;
    }
    public void setArtisti(Set<Artista> artisti) {
        this.artisti = artisti;
    }
    public float getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    //public LocalTime getOrarioInizio() {
    //    return orarioInizio;
    //}
    //public void setOrarioInizio(LocalTime orarioInizio) {
    //    this.orarioInizio = orarioInizio;
    //}
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
    /*FINE GETTER & SETTER*/

    
    /*EQUALS & HASHCODE*/
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((titoloEvento == null) ? 0 : titoloEvento.hashCode());
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
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
        Evento other = (Evento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (titoloEvento == null) {
            if (other.titoloEvento != null)
                return false;
        } else if (!titoloEvento.equals(other.titoloEvento))
            return false;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        return true;
    }
    /*FINE EQUALS & HASHCODE*/

    
    /*COSTRUTTORI*/
    public Evento() {
    }

    public Evento(String titoloEvento, String descr, float prezzo, LocalDate dataEvento, /*LocalTime orario,*/ Locale locale, Set<Artista> artisti){
        this.titoloEvento = titoloEvento;
        this.descr = descr;
        this.prezzo = prezzo;
        this.dataEvento = dataEvento;
        //this.orarioInizio = orario;
        this.locale = locale;
        this.artisti = artisti;
    }
    //TODO toString()
    /*FINE COSTRUTTORI*/
    
}
