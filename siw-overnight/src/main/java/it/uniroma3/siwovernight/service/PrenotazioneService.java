
package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.uniroma3.siwovernight.model.Prenotazione;
import it.uniroma3.siwovernight.repository.PrenotazioneRepository;

@Service 
public class PrenotazioneService {

    @Autowired 
    private PrenotazioneRepository prenotazioneRepository; 

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).get();
    }

    public Iterable<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public void save(Prenotazione prenotazione) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Object findByYear(Integer year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByYear'");
    }
}    
