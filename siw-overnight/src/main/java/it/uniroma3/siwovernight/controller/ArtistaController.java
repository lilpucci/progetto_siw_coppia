
package it.uniroma3.siwovernight.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.service.ArtistaService;
import it.uniroma3.siwovernight.service.ImmagineService;
@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private ImmagineService immagineService;
	
	//risponde a una GET HTTP che avrÃƒ  un URL del tipo /movie/1231
	@GetMapping("/artisti/{id}")//senza s
	public String getArtista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.artistaService.findById(id));
		return "artista.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
	
	@GetMapping("/artisti")
	public String showArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.findAll());
		return "artisti.html"; //verificato: qua la s ce va
		
	}


	@GetMapping("/admin/formNewArtista")
	public String formNewArtista(Model model) {
		model.addAttribute("artista", new Artista());
		return "formNewArtista.html";
	}
	
	
	@PostMapping("/admin/newArtista")
	public String newArtista(@ModelAttribute("artista") Artista artista, @RequestParam("immagine") MultipartFile immagine) throws IOException {
		if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (artista.getImmagini() == null) {
                artista.setImmagini(new ArrayList<>());
            }
            artista.getImmagini().add(img);
            immagineService.save(img);
        }
		this.artistaService.save(artista);
		return "redirect:/artisti/"+artista.getId();
	}
	
	@PostMapping("/admin/deleteArtista/{id}")
    public String deleteArtista(@PathVariable Long id) {
        artistaService.deleteById(id);
        return "redirect:/artisti"; // Redirect alla lista degli artisti dopo la cancellazione
    }

	@GetMapping("/admin/editArtista/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
    Artista artista = artistaService.findById(id);
    model.addAttribute("artista", artista); // Aggiunge l'oggetto 'artista' al modello
    return "formUpdateArtista.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/admin/updateArtista/{id}")
	public String updateArtista(@PathVariable("id") Long id, @ModelAttribute Artista artista) {
    	artista.setId(id); // Imposta l'ID sulla artista per l'aggiornamento
    	this.artistaService.save(artista); // Salva l' artista aggiornato
    	return "redirect:/artisti/" + artista.getId(); // Redirect alla pagina del artista aggiornato
	}

	@PostMapping("/searchArtista")
	public String searchArtista(Model model, @RequestParam String nome) {
		model.addAttribute("cuochi", this.artistaService.findByNome(nome)); 
        return "cuochi.html"; 
	}

}
