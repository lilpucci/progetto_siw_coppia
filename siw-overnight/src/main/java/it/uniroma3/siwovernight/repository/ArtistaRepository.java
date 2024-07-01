package it.uniroma3.siwovernight.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Artista;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista,Long>{
    
    public boolean existsByNome(String nome);

    public Iterable<Artista> findByNome(String nome);

    public Artista findByNomeAndCognome(String nome, String cognome);

    public Iterable<Artista> findByDataNascitaAfter(LocalDate dataNascita);

}
