package it.uniroma3.siwovernight.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Artista;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista,Long>{
    
    public boolean existsByNome(String nome);

    public Artista findByNome(String nome);

    public Iterable<Artista> findByDataNascitaAfter(LocalDate dataNascita);

    @Query(value="select * from artista order by dataNascita desc",nativeQuery = true)
    public List<Artista> findLatestArtisti();

    @Query(value="select count(*) artista",nativeQuery = true)
    public int countArtista();

    @Query(value="select * from artista order by nome asc",nativeQuery = true)
    public List<Artista> findArtistaByNome();

    @Query(value="select count(a) from artista a where a.nome=Vasco",nativeQuery = true)
    public int countArtistaByNome(String nome);

}
