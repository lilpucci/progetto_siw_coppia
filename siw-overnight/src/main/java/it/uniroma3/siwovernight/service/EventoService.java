package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.repository.EventoRepository;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    public Evento findById(Long id){
        return eventoRepository.findById(id).get();
    }

    public Iterable<Evento> findAll(){
        return eventoRepository.findAll();
    }

    
}
