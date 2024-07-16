package it.uniroma3.siwovernight.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.model.Locale;
import it.uniroma3.siwovernight.service.ImmagineService;
import it.uniroma3.siwovernight.service.LocaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class LocaleController extends GlobalController{
    

    @Autowired
    private LocaleService localeService;

    @Autowired
    private ImmagineService immagineService;

    //risponde a una GET HTTP che avra' un URL del tipo /movie/1231
	@GetMapping("/locali/{id}")//senza s
	public String getLocale(@PathVariable("id") Long id, Model model) {
		model.addAttribute("locale", this.localeService.findById(id));
		return "locale.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
    @GetMapping("/locali")  //restituisce l'html con tutti i locali
    public String getLocali(Model model) {
        model.addAttribute("locali", this.localeService.findAll());
        return "locali.html";
    }
    
    //search del locale (da fare meglio)
    @PostMapping("/searchLocale")
    public String postSearchLocale(@RequestParam String nome) { 
        Locale locale = this.localeService.findByNomeLocale(nome);
        return "redirect:/locali/" + locale.getId();
    }
    
    //todo eventuale edit locale


    

    /*AGGIUNTA DEL LOCALE*/
    @GetMapping("/admin/addLocale")
    public String getFormNewLocale(Model model) {

        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }

        model.addAttribute("locale", new Locale());
        return "formNewLocale.html";
    }

    @PostMapping("/admin/addLocale")
    public String postNewLocale(@ModelAttribute Locale locale,@RequestParam("immagine") MultipartFile immagine) throws IOException {
        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            locale.getImmagini().add(img);
            immagineService.save(img);
        }

        this.localeService.save(locale);
        
        return "redirect:/locali/" + locale.getId();
    }
    /*FINE AGGIUNTA LOCALI*/
    

    /*CANCELLAZIONE LOCALE*/
    @GetMapping("/admin/deleteLocale/{id}")
    public String deleteLocale(@PathVariable("id") Long id) {
        //solo l'admin può cancellare un locale
        if(!getCredenziali().isAdmin()){
            return "errorPage.html";
        }

        this.localeService.deleteById(id);
        return "redirect:/locali";
    }
    /*FINE CANCELLAZIONE LOCALE*/
    
    
    
}