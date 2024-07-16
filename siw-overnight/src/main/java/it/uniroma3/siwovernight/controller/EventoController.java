package it.uniroma3.siwovernight.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.model.Locale;
import it.uniroma3.siwovernight.service.ArtistaService;
import it.uniroma3.siwovernight.service.EventoService;
import it.uniroma3.siwovernight.service.ImmagineService;
import it.uniroma3.siwovernight.service.LocaleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class EventoController extends GlobalController{
    

    @Autowired
    private EventoService eventoService;
    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private LocaleService localeService;

    @Autowired
    private ImmagineService immagineService;

    @GetMapping("/eventi/{id}")
    public String getEvento(@PathVariable("id") Long id, Model model) {
        model.addAttribute("evento", this.eventoService.findById(id));
        return "evento.html";
    }

    @GetMapping("/eventi")
    public String getEventi(Model model) {
        model.addAttribute("eventi", this.eventoService.findAll());
        return "eventi.html";
    }
    
	@GetMapping("/admin/formNewEvento/{id}")
	public String formNewEvento(Model model, @PathVariable("id") Long id_loc) {
        //controllo dei permessi -> operazione riservata all'admin
        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }

        //aggiungo al modello il nuovo evento ed il locale
        model.addAttribute("locale", this.localeService.findById(id_loc));
		model.addAttribute("evento", new Evento());
        model.addAttribute("artisti", artistaService.findAll());
		return "formNewEvento.html";
	}
	
	
	@PostMapping("/admin/newEvento/{localeId}")
    public String newEvento(@PathVariable("localeId") Long localeId, @ModelAttribute Evento evento, @RequestParam("immagine") MultipartFile immagine) throws IOException {
        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (evento.getImmagini() == null) {
                evento.setImmagini(new ArrayList<>());
            }
            evento.getImmagini().add(img);
            immagineService.save(img);
        }

        Locale locale = this.localeService.findById(localeId);
        evento.setLocale(locale); // Associa il locale all'evento
        locale.getEventi().add(evento); // Associa l'evento al locale
        this.eventoService.save(evento); // Salva l'evento
        this.localeService.save(locale); // Salva il locale

        return "redirect:/locali/" + locale.getId(); // Reindirizza alla pagina del locale
    }

	@PostMapping("/admin/deleteEvento/{id}")
    public String deleteEvento(@PathVariable Long id) {
        eventoService.deleteById(id);
        return "redirect:/artisti"; // Redirect alla lista degli artisti dopo la cancellazione
    }

	@GetMapping("/admin/editEvento/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
    Evento evento = eventoService.findById(id);
    model.addAttribute("evento", evento); // Aggiunge l'oggetto 'evento' al modello
    model.addAttribute("artista", new Artista()); // Aggiungi un nuovo oggetto Artista al modello
    model.addAttribute("artisti", this.artistaService.findAll()); // Aggiungi la lista degli artisti al modello se necessario
    return "formUpdateEvento.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/admin/updateEvento/{id}")
	public String updateEvento(@PathVariable("id") Long id, @ModelAttribute Evento evento, @RequestParam("immagini") MultipartFile[] immagini) throws IOException {
    	Evento existingEvento = eventoService.findById(id);
        if (existingEvento == null) {
            return "errorPage.html";
        }

        existingEvento.setTitoloEvento(evento.getTitoloEvento());
        existingEvento.setDescr(evento.getDescr());
        existingEvento.setPrezzo(evento.getPrezzo());
        existingEvento.setDataEvento(evento.getDataEvento());
        if (immagini != null && immagini.length > 0) {
            for (MultipartFile immagine : immagini) {
                if (!immagine.isEmpty()) {
                    Immagine img = new Immagine();
                    img.setFileName(immagine.getOriginalFilename());
                    img.setImageData(immagine.getBytes());
                    existingEvento.getImmagini().add(img);
                    immagineService.save(img);
                }
            }
        }
    	eventoService.save(existingEvento); // Salva l' evento aggiornato
    	return "redirect:/artisti/" + evento.getId(); // Redirect alla pagina del evento aggiornato
	}

	@PostMapping("/searchEvento")
	public String searchEvento(Model model, @RequestParam String nome) {
		model.addAttribute("eventi", this.eventoService.findByNomeEvento(nome)); 
        return "eventi.html"; 
	}

    @PostMapping("/admin/addArtistiToEvento/{evento_id}")
	public String addArtisti(/*@Valid*/ @PathVariable("evento_id") Long id,  @ModelAttribute("artista") Artista artista){/* ,BindingResult bindingResult*/
		//this.ingredienteValidator.validate(ingrediente, bindingResult);
		//if (!bindingResult.hasErrors()) {
            Artista foundArtista = this.artistaService.findByNome(artista.getNome());	
                Evento evento = this.eventoService.findById(id);
                // Aggiungi l'evento al set degli eventi dell'artista
                // Aggiungi l'evento al set degli eventi dell'artista
                foundArtista.getEventi().add(evento);

                // Aggiungi l'artista al set degli artisti dell'evento
                evento.getArtisti().add(foundArtista);

                // Salva le modifiche
                this.artistaService.save(foundArtista);
                this.eventoService.save(evento);
                return "redirect:/admin/editEvento/"+ evento.getId();
        }
}

    

