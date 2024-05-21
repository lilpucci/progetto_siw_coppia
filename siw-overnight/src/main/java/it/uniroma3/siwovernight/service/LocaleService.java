package it.uniroma3.siwovernight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Locale;
import it.uniroma3.siwovernight.repository.LocaleRepository;

@Service
public class LocaleService {
    
    @Autowired
    private LocaleRepository localeRepository;

    public Locale findById(Long id){
        return localeRepository.findById(id).get();
    }

    public Iterable<Locale> findAll(){
        return localeRepository.findAll();
    }
}
