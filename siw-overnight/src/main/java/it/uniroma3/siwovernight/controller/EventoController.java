package it.uniroma3.siwovernight.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
public class EventoController extends GlobalController {
    

    @Autowired
    private EventoService eventoService;

    @Autowired
    private LocaleService localeService;

    @Autowired
    private ImmagineService immagineService;

    @Autowired
    private ArtistaService artistaService;


    /*PAGINA SINGOLO EVENTO*/
    @GetMapping("/eventi/{id}")
    public String getEvento(@PathVariable("id") Long id, Model model) {
        model.addAttribute("evento", this.eventoService.findById(id));
        return "evento.html";
    }

    /*PAGINA TUTTI GLI EVENTI*/
    @GetMapping("/eventi")
    public String getEventi(Model model) {
        model.addAttribute("eventi", this.eventoService.findAll());
        return "eventi.html";
    }

    /*EVENTI CON QUELL'ARTISTA*/  //da testare
    @GetMapping("/eventi/artista/{id}")
    public String getEventiArtista(Model model, @PathVariable("id") Long id_art){
        //trovo il nome dell'artista e poi aggiungo al modello gli eventi a cui partecipa
        String nomeArtista = this.artistaService.findById(id_art).getNome();
        model.addAttribute("eventi", this.eventoService.findByArtista(nomeArtista));
        return "eventi.html";
    }

    
    /*MI DEVE MANDARE ALLA FORM PER L'AGGIUNTA DI UN NUOVO EVENTO AD UN LOCALE*/
	@GetMapping("/admin/formNewEvento/{id}")
	public String formNewEvento(Model model, @PathVariable("id") Long id_loc) {
        //controllo dei permessi -> operazione riservata all'admin
        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }
        //aggiungo al modello il nuovo evento ed il locale
        model.addAttribute("locale", this.localeService.findById(id_loc));
		model.addAttribute("evento", new Evento());
		return "formNewEvento.html";
	}
	
	/*SALVATAGGIO DEL NUOVO EVENTO*/  //cambierei il nome per farlo uguale alla richiesta get
	@PostMapping("/admin/newEvento")
	public String newEvento(@ModelAttribute("evento") Evento evento, @ModelAttribute("locale") Locale locale ,@RequestParam("immagine") MultipartFile immagine) throws IOException {
		
        locale.getEventi().add(evento);
        evento.setLocale(locale);

        //se l'immagine non è vuota 
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

        //salvo il nuovo evento
		this.eventoService.save(evento);

		return "redirect:/artisti/" + evento.getId();
	}
	
	@PostMapping("/admin/deleteEvento/{id}")
    public String deleteEvento(@PathVariable Long id) {
        eventoService.deleteById(id);
        return "redirect:/artisti"; // Redirect alla lista degli artisti dopo la cancellazione
    }


    //se abbiamo capito bene con no cap non funzionano bene
	@GetMapping("/admin/editEvento/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
    Evento evento = eventoService.findById(id);
    model.addAttribute("evento", evento); // Aggiunge l'oggetto 'evento' al modello
    return "formUpdateEvento.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/admin/updateEvento/{id}")
	public String updateEvento(@PathVariable("id") Long id, @ModelAttribute Evento evento) {
    	evento.setId(id); // Imposta l'ID sulla evento per l'aggiornamento
    	this.eventoService.save(evento); // Salva l' evento aggiornato
    	return "redirect:/artisti/" + evento.getId(); // Redirect alla pagina del evento aggiornato
	}

	@PostMapping("/searchEvento")
	public String searchEvento(Model model, @RequestParam String nome) {
		model.addAttribute("eventi", this.eventoService.findByNomeEvento(nome)); 
        return "eventi.html"; 
	}

}

    

