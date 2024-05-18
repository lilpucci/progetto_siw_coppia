package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.service.ArtistaService;
@Controller
public class ArtistaController {
	
	@Autowired
	ArtistaService artistaService;
	
	
	
	//risponde a una GET HTTP che avrÃ  un URL del tipo /movie/1231
	@GetMapping("/artista/{id}")//senza s
	public String getArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.findById(id));
		return "artista.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
	
	@GetMapping("/artista")//senza s
	public String showArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.findAll());
		return "artisti.html"; //verificato: qua la s ce va
		
	}
	//ci da la lista di tutti i film
	//e la inserisce nel modello passato per parametro

	
	@GetMapping("/formNewArtista")
	public String formNewArtista(Model model) {
		model.addAttribute("artista", new Artista());
		return "formNewArtista.html";
	}
	
	
	@PostMapping("/artista")
	public String newArtista(@ModelAttribute("artista") Artista artista) {
		this.artistaService.save(artista);
		return "redirect:movie/"+artista.getId();
	}
	
	 @GetMapping("/formSearchArtisti")
	 public String formSearchArtisti() {
	    return "formSearchArtisti.html";
	 }
	 
	 @PostMapping("/searchArtisti")
	 public String searchArtisti(Model model, @RequestParam Integer year) {
		 model.addAttribute("artisti", this.artistaService.findByYear(year));
		 return "foundArtisti.html";
	 }

}
