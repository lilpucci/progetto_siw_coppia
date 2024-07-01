
package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Prenotazione;
import it.uniroma3.siwovernight.repository.PrenotazioneRepository;

@Service 
public class PrenotazioneService {

    @Autowired 
    private PrenotazioneRepository prenotazioneRepository; 

    /*RICERCHE */
    public Prenotazione findById(Long id){
        return this.prenotazioneRepository.findById(id).get();
    }

    public Iterable<Prenotazione> findAll(){
        return this.prenotazioneRepository.findAll();
    }
    /*FINE RICERCHE */

    /*SALVATAGGIO */
    public Prenotazione save(Prenotazione Prenotazione){
        return this.prenotazioneRepository.save(Prenotazione);
    }
    /*FINE SALVATAGGIO */

    /*CANCELLAZIONE */
    public void deleteById(Long id){
        this.prenotazioneRepository.deleteById(id);
    }

    public void delete(Prenotazione Prenotazione){
        this.prenotazioneRepository.delete(Prenotazione);
    }
    /*FINE CANCELLAZIONE */
    
}    
