
package it.uniroma3.siwovernight.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwovernight.model.Artista;
import it.uniroma3.siwovernight.service.ArtistaService;
import it.uniroma3.siwovernight.service.ImmagineService;


@Controller
public class ArtistaController extends GlobalController{
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private ImmagineService immagineService;

	
	//pagina del singolo artista
	@GetMapping("/artisti/{id}")
	public String getArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.findById(id));
		return "artista.html";
	}
	
	//tutti gli artisti
	@GetMapping("/artisti")
	public String showArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.findAll());
		return "artisti.html";
	}

	//artista cercato per il nome
	@PostMapping("/searchArtista")
	public String searchArtista(Model model, @RequestParam String nome) {
		model.addAttribute("artiti", this.artistaService.findByNome(nome)); 
        return "artisti.html"; 
	}


	/*AGGIUNTA DI UN NUOVO ARTISTA */
	@GetMapping("/admin/newArtista")
	public String formNewArtista(Model model) {
		//controllo dei permessi
		if(!getCredenziali().isAdmin()){
			return "errorPage.html";
		}
		model.addAttribute("artista", new Artista());
		return "formNewArtista.html";
	}
	
	@PostMapping("/admin/newArtista")
	public String newArtista(@ModelAttribute("artista") Artista artista, @RequestParam("immagine") MultipartFile immagine) throws IOException {
		this.immagineService.addFotoToArtista(artista, immagine);
		this.artistaService.save(artista);
		return "redirect:/artisti/" + artista.getId();
	}
	/*FINE AGGIUNTA DI UN NUOVO ARTISTA */
	

	/*CANCELLAZIONE DI UN ARTISTA */
	@PostMapping("/admin/deleteArtista/{id}")
    public String deleteArtista(@PathVariable Long id) {
		//controllo dei permessi
		if(!getCredenziali().isAdmin()){
			return "errorPage.html";
		}
		//cancellazione dell'artista
        this.artistaService.deleteById(id);
        return "redirect:/artisti"; // Redirect alla lista degli artisti dopo la cancellazione
    }

	@GetMapping("/admin/editArtista/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
		//controllo dei permessi
		if(!getCredenziali().isAdmin()){
			return "errorPage.html";
		}
		model.addAttribute("artista", this.artistaService.findById(id)); // Aggiunge l'oggetto 'artista' al modello
		return "formUpdateArtista.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/admin/updateArtista/{id}")
	public String updateArtista(@PathVariable("id") Long id, @ModelAttribute Artista artista) {
    	artista.setId(id); // Imposta l'ID sulla artista per l'aggiornamento
    	this.artistaService.save(artista); // Salva l' artista aggiornato
    	return "redirect:/artisti/" + artista.getId(); // Redirect alla pagina del artista aggiornato
	}

	

}
