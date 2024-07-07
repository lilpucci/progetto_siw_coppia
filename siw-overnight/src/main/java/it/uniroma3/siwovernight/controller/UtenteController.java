package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siwovernight.service.UtenteService;

@Controller
public class UtenteController {
    
    @Autowired
    private UtenteService utenteService;

}
