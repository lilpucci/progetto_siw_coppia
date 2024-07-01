package it.uniroma3.siwovernight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siwovernight.model.Credenziali;
import it.uniroma3.siwovernight.service.CredenzialiService;

@ControllerAdvice
public class GlobalController {
    

    @Autowired
    private CredenzialiService credenzialiService;

    
    @ModelAttribute("userDetails")
    public UserDetails getUser() {
        UserDetails user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        }
        return user;
    }

	@ModelAttribute("credenziali")
    public Credenziali getCredenziali() {
        UserDetails user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credenziali credentials = credenzialiService.getCredenzialiByUsername(user.getUsername());
            return credentials;
        }
        return null;
    }
    
}
