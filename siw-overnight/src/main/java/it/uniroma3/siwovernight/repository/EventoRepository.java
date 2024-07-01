package it.uniroma3.siwovernight.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siwovernight.model.Evento;


public interface EventoRepository extends CrudRepository<Evento,Long>{
    
    public boolean existsByTitoloEvento(String titoloEvento);

    public Evento findByTitoloEvento(String titoloEvento);

    //tutti gli eventi di un locale -> getEventi da locale

    //TODO tutti gli eventi dopo una certa data

    @Query("SELECT e FROM Evento e JOIN e.artisti a WHERE a.nome = :nomeArtista")
    public Iterable<Evento> findByArtista (@Param("nomeArtista") String nomeArtista);

}
