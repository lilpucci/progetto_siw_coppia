package it.uniroma3.siwovernight.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Credenziali {
  
    public static final String UTENTE_GENERICO = "GENERICO";
    public static final String UTENTE_REGISTRATO = "REGISTRATO";
    public static final String ADMIN = "ADMIN";

    /*ATTRIBUTI CREDENTIALI*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;
    
    @NotBlank
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Utente utente;
    /*FINE ATTRIBUTI CREDENTIALI*/


    /*EQUALS & HASHCODE*/
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        Credenziali other = (Credenziali) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }
    /*FINE EQUALS & HASHCODE*/


    /*METODO CHE VERIFICA SE L'UTENTE E' ADMIN O MENO*/ 
    public boolean isAdmin(){
        return this.role.equals(ADMIN);
    }

    /*RESTITUISCONO I RUOLI*/
    public static String getUtenteGenerico() {
        return UTENTE_GENERICO;
    }

    public static String getUtenteRegistrato() {
        return UTENTE_REGISTRATO;
    }

    public static String getAdmin() {
        return ADMIN;
    }


    /*GETTERS & SETTERS*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    /*FINE GETTERS & SETTERS*/


}
