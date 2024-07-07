package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Locale;
import it.uniroma3.siwovernight.repository.LocaleRepository;

@Service
public class LocaleService {
    
    @Autowired
    private LocaleRepository localeRepository;

    /*RICERCHE */
    public Locale findById(Long id){
        return this.localeRepository.findById(id).get();
    }

    public Iterable<Locale> findAll(){
        return this.localeRepository.findAll();
    }

    public Locale findByNomeLocale(String nome){
        return this.localeRepository.findByNomeLocale(nome);
    }

    public Locale findByIndirizzo(String indirizzo){
        return this.localeRepository.findByIndirizzo(indirizzo);
    }
    /*FINE RICERCHE */

    /*SALVATAGGIO */
    public Locale save(Locale locale){
        return this.localeRepository.save(locale);
    }
    /*FINE SALVATAGGIO */

    /*CANCELLAZIONE */
    public void deleteById(Long id){
        this.localeRepository.deleteById(id);
    }

    public void delete(Locale locale){
        this.localeRepository.delete(locale);
    }
    /*FINE CANCELLAZIONE */

}
