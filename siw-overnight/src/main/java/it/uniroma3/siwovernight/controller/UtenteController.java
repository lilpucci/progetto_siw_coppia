package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siwovernight.model.Utente;
import it.uniroma3.siwovernight.service.UtenteService;

@Controller
public class UtenteController extends GlobalController{
    
    @Autowired
    private UtenteService utenteService;
    /*MI PORTA ALLA PAGINA PROFILO DELL'UTENTE*/
    @GetMapping("/utente")
    public String getUtente(Model model) {
        Utente u = getCredenziali().getUtente();
        //se non trovo un utente -> errore
        if(u == null){
            return "errorPage.html";
        }
        //prolisso
        model.addAttribute("utente", this.utenteService.findById(u.getId()));
        return "profilo.html";
    }
}
