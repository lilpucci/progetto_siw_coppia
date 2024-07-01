package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Credenziali;
import it.uniroma3.siwovernight.repository.CredenzialiRepository;

@Service
public class CredenzialiService {
    

    @Autowired
    private CredenzialiRepository credenzialiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*RICERCHE*/
    //in base all'id
    public Credenziali getCredenziali(Long id){
        return this.credenzialiRepository.findById(id).get();
    }
    //in base all'username
    public Credenziali getCredenzialiByUsername(String username){
        return this.credenzialiRepository.findByUsername(username).get();
    }
    /*FINE RICERCHE*/

    /*SALVATAGGIO*/
    public Credenziali saveCredenziali(Credenziali Credenziali){

        Credenziali.setPassword(this.passwordEncoder.encode(Credenziali.getPassword()));
        return this.credenzialiRepository.save(Credenziali);
    
    }
    /*FINE SALVATAGGIO*/

    /*CANCELLAZIONE*/
    public void deleteCredenziali(Credenziali Credenziali){
        this.credenzialiRepository.delete(Credenziali);
    }
    /*FINE CANCELLAZIONE*/
}
