package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Utente;
import it.uniroma3.siwovernight.repository.UtenteRepository;

@Service
public class UtenteService {
    
    @Autowired
    private UtenteRepository utenteRepository;

    /*RICERCHE */
    public Utente findById(Long id){
        return utenteRepository.findById(id).get();
    }

    public Iterable<Utente> findAll(){
        return utenteRepository.findAll();
    }
    /*FINE RICERCHE */

    /*SALVATAGGIO */
    public Utente save(Utente Utente){
        return utenteRepository.save(Utente);
    }
    /*FINE SALVATAGGIO */

    /*CANCELLAZIONE */
    public void deleteById(Long id){
        this.utenteRepository.deleteById(id);
    }

    public void delete(Utente Utente){
        this.utenteRepository.delete(Utente);
    }
    /*FINE CANCELLAZIONE */
    
}
