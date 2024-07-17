package it.uniroma3.siwovernight.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Artista;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista,Long>{
    
    public boolean existsByNome(String nome);

    public Artista findByNome(String nome);

    public Iterable<Artista> findByDataNascitaAfter(LocalDate dataNascita);

}
