package it.uniroma3.siwovernight.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Prenotazione;

@Repository
public interface PrenotazioneRepository  extends CrudRepository<Prenotazione,Long> {

    
}