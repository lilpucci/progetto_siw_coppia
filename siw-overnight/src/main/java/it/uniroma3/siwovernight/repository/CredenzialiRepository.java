package it.uniroma3.siwovernight.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Credenziali;


@Repository
public interface CredenzialiRepository extends CrudRepository<Credenziali, Long>{
    
    //restituisce le credenziali in base all'username
    public Optional<Credenziali> findByUsername(String username);
    //restituisce le credenziali in base all'id
    public Optional<Credenziali> findById(Long id);

    //TODO capire perchè ho questi warning
}
