package it.uniroma3.siwovernight.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Locale {
    
    /*ATTRIBUTI LOCALE*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeLocale;
    private String indirizzo; //-> si potrebbe trasformare in una classe (nome, civico, città)
    private String descrizione;
    @OneToMany(mappedBy = "locale")
    private List<Evento> eventi;
    /*FINE ATTRIBUTI LOCALE*/

    /*GETTER & SETTER*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeLocale() {
        return nomeLocale;
    }
    public void setNomeLocale(String nomeLocale) {
        this.nomeLocale = nomeLocale;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public List<Evento> getEventi() {
        return eventi;
    }
    public void setEventi(List<Evento> eventi) {
        this.eventi = eventi;
    }
    /*FINE GETTER & SETTER*/

    /*EQUALS & HASHCODE*/
    @Override
    public int hashCode() {
        return Objects.hash(nomeLocale,indirizzo,id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Locale other = (Locale) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nomeLocale == null) {
            if (other.nomeLocale != null)
                return false;
        } else if (!nomeLocale.equals(other.nomeLocale))
            return false;
        if (indirizzo == null) {
            if (other.indirizzo != null)
                return false;
        } else if (!indirizzo.equals(other.indirizzo))
            return false;
        if (descrizione == null) {
            if (other.descrizione != null)
                return false;
        } else if (!descrizione.equals(other.descrizione))
            return false;
        return true;
    }
    /*FINE EQUALS & HASHCODE*/

}
