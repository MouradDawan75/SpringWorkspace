package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("utilisateurs")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @GetMapping("/display")
    public String display(Model model) throws Exception{

        model.addAttribute("users", utilisateurService.getAll());
        return "utilisateurs";
    }
}
