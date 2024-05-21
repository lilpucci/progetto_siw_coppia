package it.uniroma3.siwovernight.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Locale;

@Repository
public interface LocaleRepository extends CrudRepository<Locale, Long>{
    
}
