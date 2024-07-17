package it.uniroma3.siwovernight.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.model.Locale;
import it.uniroma3.siwovernight.model.Prenotazione;
import it.uniroma3.siwovernight.service.ArtistaService;
import it.uniroma3.siwovernight.service.EventoService;
import it.uniroma3.siwovernight.service.ImmagineService;
import it.uniroma3.siwovernight.service.LocaleService;
import it.uniroma3.siwovernight.service.PrenotazioneService;

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
    private ArtistaService artistaService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private ImmagineService immagineService;

    


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

    /*EVENTI CON QUELL'ARTISTA*/  //TODO da rendere coerente con le altre ricerche
    @GetMapping("/eventi/artista/{id}")
    public String getEventiArtista(Model model, @PathVariable("id") Long id_art){
        //trovo il nome dell'artista e poi aggiungo al modello gli eventi a cui partecipa
        String nomeArtista = this.artistaService.findById(id_art).getNome();
        model.addAttribute("eventi", this.eventoService.findByArtista(nomeArtista));
        return "eventi.html";
    }

    /*EVENTO IN BASE AL NOME*/
    @PostMapping("/searchEvento")
	public String searchEvento(Model model, @RequestParam String nome) {
		model.addAttribute("eventi", this.eventoService.findByNomeEvento(nome)); 
        return "eventi.html"; 
	}

    
    /*MI DEVE MANDARE ALLA FORM PER L'AGGIUNTA DI UN NUOVO EVENTO AD UN LOCALE*/
	@GetMapping("/admin/newEvento/{id}")
	public String getFormNewEvento(Model model, @PathVariable("id") Long id_loc) {
        //controllo dei permessi -> operazione riservata all'admin
        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }
        //aggiungo al modello il nuovo evento ed il locale
        model.addAttribute("locale", this.localeService.findById(id_loc));
		model.addAttribute("evento", new Evento());
		return "formNewEvento.html";
	}


    @PostMapping("/admin/newEvento/{localeId}")
    public String newEvento(@PathVariable("localeId") Long localeId, @ModelAttribute Evento evento, @RequestParam("immagine") MultipartFile immagine) throws IOException {
        this.immagineService.addFotoToEvento(evento, immagine);
        Locale locale = this.localeService.findById(localeId);
        evento.setLocale(locale); // Associa il locale all'evento
        locale.getEventi().add(evento); // Associa l'evento al locale
        this.eventoService.save(evento); // Salva l'evento
        this.localeService.save(locale); // Salva il locale
        return "redirect:/locali/" + locale.getId(); // Reindirizza alla pagina del locale
    }
	
	/*SALVATAGGIO DEL NUOVO EVENTO  //cambierei il nome per farlo uguale alla richiesta get
	@PostMapping("/admin/newEvento/{id}")
	public String postNewEvento(@ModelAttribute("evento") Evento evento, @PathVariable("id") Long id_loc, @RequestParam("immagine") MultipartFile immagine) throws IOException {	
        Locale locale = this.localeService.findById(id_loc);
        //aggiungo l'evento alla lista del locale
        evento.setLocale(locale);
        locale.getEventi().add(evento);
        //imposto il locale dell'evento
        //aggiungo la foto al locale
        this.immagineService.addFotoToEvento(evento, immagine);
        //salvo il nuovo evento
		this.eventoService.save(evento);
        this.localeService.save(locale);
		return "redirect:/eventi/" + evento.getId();
	}
	

    /*CANCELLAZIONE DI UN EVENTO */
	@PostMapping("/admin/deleteEvento/{id}")
    public String deleteEvento(@PathVariable Long id) {
        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }
        //prendo l'evento che voglio cancellare
        Evento e = this.eventoService.findById(id);
        //prima di cancellare l'evento devo rimuovere tutte le prenotazioni relative all'evento
        List<Prenotazione> prenEv = this.prenotazioneService.findByEvento(e);
        for(Prenotazione p : prenEv){
            this.prenotazioneService.delete(p);
        }
        //cancello l'evento
        this.eventoService.deleteById(id);
        return "redirect:/eventi";
    }
    /*FINE CANCELLAZIONE DI UN EVENTO */


    /*MODIFICA DEI DATI DI UN EVENTO */
    //se abbiamo capito bene con no cap non funzionano bene
	@GetMapping("/admin/updateEvento/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
    
        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }
        model.addAttribute("evento", this.eventoService.findById(id));
        model.addAttribute("artista", new Artista()); // Aggiungi un nuovo oggetto Artista al modello
        model.addAttribute("artisti", this.artistaService.findAll());

        return "formUpdateEvento.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/admin/updateEvento/{id}")
	public String updateEvento(@PathVariable("id") Long id_e, @ModelAttribute Evento evento, @RequestParam("immagine") MultipartFile immagine) throws IOException {
    	//setto il locale al nuovo evento
        evento.setLocale(this.eventoService.findById(id_e).getLocale());
        //aggiungo al nuovo evento le vecchie foto
        evento.setImmagini(this.eventoService.findById(id_e).getImmagini());
        evento.setArtisti(this.eventoService.findById(id_e).getArtisti());
        evento.setId(id_e);
        //aggiungo la nuova foto
        this.immagineService.addFotoToEvento(evento, immagine);
        this.eventoService.save(evento);
    	return "redirect:/eventi/" + id_e; // Redirect alla pagina del evento aggiornato
	}
    /*FINE MODIFICA DEI DATI DI UN EVENTO */


    /*AGGIUNTA DI UN ARTISTA ALL'EVENTO */
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
        return "redirect:/admin/updateEvento/"+ evento.getId();
    }
    

}

    

