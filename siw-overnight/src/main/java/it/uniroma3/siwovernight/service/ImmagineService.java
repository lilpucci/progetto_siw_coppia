package it.uniroma3.siwovernight.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.model.Locale;
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


    //se non vuoto il multipart file lo aggiunge alla collezione di foto del locale
    public void addFotoToLocale(Locale locale, MultipartFile immagine) throws IOException{
        if(!immagine.isEmpty()){
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            //lo aggiungo al cuoco
            locale.getImmagini().add(img);
            this.save(img);
        }
    }

    //se non vuoto il multipart file lo aggiunge alla collezione di foto del evento
    public void addFotoToEvento(Evento evento, MultipartFile immagine) throws IOException{
        if(!immagine.isEmpty()){
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            //lo aggiungo al cuoco
            evento.getImmagini().add(img);
            this.save(img);
        }
    }

    //se non vuoto il multipart file lo aggiunge alla collezione di foto del artista
    public void addFotoToArtista(Artista artista, MultipartFile immagine) throws IOException{
        if(!immagine.isEmpty()){
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            //lo aggiungo al cuoco
            artista.getImmagini().add(img);
            this.save(img);
        }
    }



}
