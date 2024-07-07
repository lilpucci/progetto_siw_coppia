package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.repository.ImmagineRepository;
import jakarta.transaction.Transactional;

@Service
public class ImmagineService {
    
    @Autowired
    private ImmagineRepository immagineRepository;


    /*RICERCHE*/
    public Immagine findById(Long id){
        return this.immagineRepository.findById(id).orElse(null);
    }
    /*FINE RICERCHE*/


    /*SALVATAGGIO*/
    @Transactional
    public Immagine save(Immagine immagine){
        return this.immagineRepository.save(immagine);
    }
    /*FINE SALVATAGGIO*/


    /*CANCELLAZIONE*/
    @Transactional
    public void delete(Immagine immagine){
        this.immagineRepository.delete(immagine);
    }
    /*FINE CANCELLAZIONE*/

}
