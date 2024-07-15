package it.uniroma3.siwovernight.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwovernight.model.Evento;
import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.service.EventoService;
import it.uniroma3.siwovernight.service.ImmagineService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class EventoController {
    

    @Autowired
    private EventoService eventoService;

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
    
	@GetMapping("/admin/formNewEvento")
	public String formNewEvento(Model model) {
		model.addAttribute("evento", new Evento());
		return "formNewEvento.html";
	}
	
	
	@PostMapping("/admin/newEvento")
	public String newEvento(@ModelAttribute("evento") Evento evento, @RequestParam("immagine") MultipartFile immagine) throws IOException {
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
		this.eventoService.save(evento);
		return "redirect:/artisti/"+evento.getId();
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

    

