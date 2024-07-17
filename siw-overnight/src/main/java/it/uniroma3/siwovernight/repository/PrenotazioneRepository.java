package it.uniroma3.siwovernight.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.model.Prenotazione;
import it.uniroma3.siwovernight.model.Utente;

@Repository
public interface PrenotazioneRepository  extends CrudRepository<Prenotazione,Long> {

    public List<Prenotazione> findByEvento(Evento e);

    public List<Prenotazione> findByUtente(Utente utente);
    
}