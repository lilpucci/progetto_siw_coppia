package it.uniroma3.siwovernight.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwovernight.model.Credenziali;
import it.uniroma3.siwovernight.model.Immagine;
import it.uniroma3.siwovernight.model.Utente;
import it.uniroma3.siwovernight.service.CredenzialiService;
import it.uniroma3.siwovernight.service.ImmagineService;
import it.uniroma3.siwovernight.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class AuthController {
    
    @Autowired
    private CredenzialiService credenzialiService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ImmagineService immagineService;



    //RESTITUISCE IL TEMPLATE DELL'HOME PAGE
    @GetMapping("/")
    public String getHomePage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

        return "auth/home.html";
    }

    //LOGIN
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "auth/login.html";
    }

    @GetMapping("/success")
    public String getHomeAfterLogin(Model model) {
        
        //capire come vengono passati  o comunque cosa ci fanno
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credenziali credenziali = this.credenzialiService.getCredenzialiByUsername(userDetails.getUsername());

        return "auth/home.html";
    }


    //REGISTRAZIONE
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        
        model.addAttribute("utente", new Utente());
        model.addAttribute("credenziali", new Credenziali());

        return "auth/register.html";
    }
    
    @PostMapping("/register")
    public String postNewUtente(@Valid @ModelAttribute("utente") Utente utente, BindingResult utenteBindingResult, @Valid @ModelAttribute("credenziali") Credenziali credenziali, BindingResult credenzialiBindingResult,@RequestParam("immagine") MultipartFile immagine, Model model) throws IOException {
        
        if(!immagine.isEmpty()){
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (utente.getImmagini() == null) {
                utente.setImmagini(new ArrayList<>());
            }
            utente.getImmagini().add(img);
            this.immagineService.save(img);
        }

        if(!utenteBindingResult.hasErrors() && !credenzialiBindingResult.hasErrors()){
            utenteService.save(utente);
            credenziali.setUtente(utente);
            credenziali.setRole("USER"); // Imposta il ruolo di default
            credenzialiService.saveCredenziali(credenziali);

            model.addAttribute("utente", utente);

            return "redirect:/";
        }
        return "auth/register.html";
    }

    @GetMapping("/search")
    public String getFormSearch(Model model) {
        return "formSearch.html";
    }
    
    

    //TODO far sparire i warning
}
