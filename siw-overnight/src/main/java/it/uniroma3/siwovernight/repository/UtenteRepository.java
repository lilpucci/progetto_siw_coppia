package it.uniroma3.siwovernight.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {
    
}
