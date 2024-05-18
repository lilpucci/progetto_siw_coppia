package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.repository.ArtistaRepository;

@Service 
public class ArtistaService {

    @Autowired 
    private ArtistaRepository artistaRepository; 

    public Artista findById(Long id) {
        return artistaRepository.findById(id).get();
    }

    public Iterable<Artista> findAll() {
        return artistaRepository.findAll();
    }

    public void save(Artista artista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Object findByYear(Integer year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByYear'");
    }
}    
