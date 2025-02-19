package fr.dawan.demospringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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

        Player player = new Player("Patrice", "Admin");
        model.addAttribute("player", player);

        return "thymeleaf";
    }
}
