package it.uniroma3.siwovernight.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwovernight.model.Locale;



@Repository
public interface LocaleRepository extends CrudRepository<Locale, Long>{
    
    public boolean existsByNomeLocale(String nomeLocale);

    public Locale findByNomeLocale(String nomeLocale);

    public Locale findByIndirizzo(String indirizzo);
    
}
