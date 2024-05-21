package it.uniroma3.siwovernight.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Artista;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista,Long>{
    
}
