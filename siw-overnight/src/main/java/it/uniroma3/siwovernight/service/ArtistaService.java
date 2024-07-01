
package it.uniroma3.siwovernight.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.repository.ArtistaRepository;

@Service 
public class ArtistaService {

    @Autowired 
    private ArtistaRepository artistaRepository; 

    /*RICERCHE */
    public Artista findById(Long id){
        return this.artistaRepository.findById(id).get();
    }

    public Iterable<Artista> findAll(){
        return this.artistaRepository.findAll();
    }

    public Iterable<Artista> findByNome(String nome){
        return this.artistaRepository.findByNome(nome);
    }

    public Artista findByNomeAndCognome(String nome, String cognome){
        return this.artistaRepository.findByNomeAndCognome(nome,cognome);
    }

    public Iterable<Artista> findByDataNascita(int year){
        LocalDate dataNascita = LocalDate.of(year,1,1);
        return this.artistaRepository.findByDataNascitaAfter(dataNascita);
    }
    /*FINE RICERCHE */

    /*SALVATAGGIO */
    public Artista save(Artista Artista){
        return this.artistaRepository.save(Artista);
    }
    /*FINE SALVATAGGIO */

    /*CANCELLAZIONE */
    public void deleteById(Long id){
        this.artistaRepository.deleteById(id);
    }

    public void delete(Artista Artista){
        this.artistaRepository.delete(Artista);
    }
    /*FINE CANCELLAZIONE */
}    
