package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.repository.EventoRepository;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    /*RICERCHE */
    public Evento findById(Long id){
        return eventoRepository.findById(id).get();
    }

    public Iterable<Evento> findAll(){
        return eventoRepository.findAll();
    }

    public Evento findByNomeEvento(String nome){
        return this.eventoRepository.findByTitoloEvento(nome);
    }

    public Iterable<Evento> findByArtista(String nomeArtista){
        return this.eventoRepository.findByArtista(nomeArtista);
    }
    /*FINE RICERCHE */

    /*SALVATAGGIO */
    public Evento save(Evento Evento){
        return eventoRepository.save(Evento);
    }
    /*FINE SALVATAGGIO */

    /*CANCELLAZIONE */
    public void deleteById(Long id){
        this.eventoRepository.deleteById(id);
    }

    public void delete(Evento Evento){
        this.eventoRepository.delete(Evento);
    }
    /*FINE CANCELLAZIONE */
    
}
