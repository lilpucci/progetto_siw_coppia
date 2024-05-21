package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siwovernight.service.LocaleService;


@Controller
public class LocaleController {
    

    @Autowired
    private LocaleService localeService;

    //risponde a una GET HTTP che avra' un URL del tipo /movie/1231
	@GetMapping("/locale/{id}")//senza s
	public String getLocale(@PathVariable("id") Long id, Model model) {
		model.addAttribute("locale", this.localeService.findById(id));
		return "locale.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
    @GetMapping("/locali")  //restituisce l'html con tutti i locali
    public String getLocali(Model model) {
        model.addAttribute("locali", this.localeService.findAll());
        return "locali.html";
    }
    
}
