
package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.model.Prenotazione;
import it.uniroma3.siwovernight.model.Utente;
import it.uniroma3.siwovernight.service.EventoService;
import it.uniroma3.siwovernight.service.PrenotazioneService;



@Controller
public class PrenotazioneController extends GlobalController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private EventoService eventoService;
	

	//TODO da testare se funziona la prenotazione
	/*PORTA AL FORM PER LA NUOVA PRENOTAZIONE*/
	@GetMapping("/prenotaEvento/{id}")
	public String getNewPrenotazione(@PathVariable("id") Long id_evento, Model model) {

	//in teoria è inutile perchè se non sei loggato non
	//viene mostrato il link nel template con il th:if
	if(getCredenziali().getUtente() == null){
		return "errorPage.html";
	}

	model.addAttribute("evento", this.eventoService.findById(id_evento));
	model.addAttribute("prenotazione", new Prenotazione());

	return "formNewPrenotazione.html";
	}

	/*FINALIZZA LA PRENOTAZIONE PER L'UTENTE ATTUALMENTE LOGGATO*/
	@PostMapping("/prenotaEvento/{id}")
	public String postNewPrenotazione(@PathVariable("id") Long id_evento, @ModelAttribute("prenotazione") Prenotazione prenotazione) {
	//il numero di biglietti viene aggiunto nel form
	//utente corrente ed evento per il quale aggiungo la prenotazione
	Utente u = getCredenziali().getUtente();
	Evento e = this.eventoService.findById(id_evento);
	//setto i valori di prenotazione
	prenotazione.setEvento(e);
	prenotazione.setUtente(u);
	//salvo la prenotazioen
	this.prenotazioneService.save(prenotazione);
	//per ora ritorni alla pagina dell'utente
	return "redirect:/eventi/" + id_evento;  //potremmo fare una pagina profilo dell'utente dove mostrare le prenotazioni
	}
	 
	 
	 
	 

}
