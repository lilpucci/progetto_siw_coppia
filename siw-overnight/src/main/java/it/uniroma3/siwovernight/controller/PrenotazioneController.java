
package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwovernight.model.Prenotazione;
import it.uniroma3.siwovernight.service.PrenotazioneService;

@Controller
public class PrenotazioneController {
	
	@Autowired
	PrenotazioneService prenotazioneService;
	
	
	//risponde a una GET HTTP che avrÃ  un URL del tipo /movie/1231
	@GetMapping("Prenotazione/{id}")//senza s
	public String getPrenotazione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("prenotazione", this.prenotazioneService.findById(id));
		return "prenotazione.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
	
	@GetMapping("/prenotazione")//senza s
	public String showPrenotazioni(Model model) {
		model.addAttribute("prenotazioni", this.prenotazioneService.findAll());
		return "artisti.html"; //verificato: qua la s ce va
		
	}
	//ci da la lista di tutti i film
	//e la inserisce nel modello passato per parametro

	
	@GetMapping("/formNewPrenotazione")
	public String formNewPrenotazione(Model model) {
		model.addAttribute("Prenotazione", new Prenotazione());
		return "formNewPrenotazione.html";
	}
	
	
	@PostMapping("/prenotazione")
	public String newPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione) {
		this.prenotazioneService.save(prenotazione);
		return "redirect:prenotazione/"+prenotazione.getId();
	}
	
	 @GetMapping("/formSearchPrenotazioni")
	 public String formSearchPrenotazioni() {
	    return "formSearchPrenotazioni.html";
	 }
	 
	 @PostMapping("/searchPrenotazioni")
	 public String searchPrenotazioni(Model model, @RequestParam Integer year) {
		 model.addAttribute("prenotazioni", this.prenotazioneService.findByYear(year));
		 return "foundPrenotazioni.html";
	 }

}
