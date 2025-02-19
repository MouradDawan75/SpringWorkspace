package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.formbeans.PlayerForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


class Player{
    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Player(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Player() {
    }
}

@Controller
public class TestController {

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model){

        model.addAttribute("message", "message fournit par le controller");

        Player player = new Player("Patrice", "RH");
        model.addAttribute("player", player);

        //liste pour la bouce FOR
        List<Player> players = new ArrayList<>();
        players.add(new Player("Thomas", "Admin"));
        players.add(new Player("Marie", "Manager"));

        model.addAttribute("players", players);

        //liste pour rempir la dropdown list
        List<String> options = new ArrayList<>();
        options.add("option 1");
        options.add("option 2");
        options.add("option 3");

        model.addAttribute("options", options);


        return "thymeleaf";

    }

    @PostMapping(value = "/thymeleaf")
    public String formHandle(Model model, @RequestParam("nom") String nom, @RequestParam("role") String role){

        model.addAttribute("nom", nom);
        model.addAttribute("role", role);
        model.addAttribute("formOK", true);

        //return "redirect:/thymeleaf";

        return thymeleaf(model);
    }


    @PostMapping(value = "/thymeleafValidation")
    public String formValidation(@Valid @ModelAttribute("playerForm") PlayerForm playerForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            //for invalide

            return thymeleaf(model);
        }

        model.addAttribute("formValide", true);

        return thymeleaf(model);
    }


    //Méthode qui renvoie l'objet PlayerForm à la page thymeleaf.html
    //Le Model contient dans tous les cas un objet de type PlayerForm

    @ModelAttribute("playerForm")
    public PlayerForm getPlayerForm(){
        return new PlayerForm();
    }
}
