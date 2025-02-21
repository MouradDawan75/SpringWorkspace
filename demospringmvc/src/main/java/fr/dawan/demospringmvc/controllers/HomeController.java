package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.entities.Product;
import fr.dawan.demospringmvc.entities.Utilisateur;
import fr.dawan.demospringmvc.services.IEmailService;
import fr.dawan.demospringmvc.services.IProductService;
import fr.dawan.demospringmvc.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUtilisateurService utilisateurService;

    @Autowired
    private IEmailService emailService;

    @GetMapping(value = {"","/"})
    public String accueil(){
        return "index";
    }

    @GetMapping(value ="/ex")
    public String testException() throws Exception {
        throw new Exception();
    }

    @GetMapping(value ="/unauthorized")
    public String unauthorized() throws Exception {
        return "unauthorized";
    }

    //Méthode d'envoi de mails

    @GetMapping("/contact")
    public String contactUs(){
        return "contact";
    }

    @PostMapping("/contact-us")
    public String contactPost(@RequestParam("email") String email, @RequestParam("sujet") String sujet,
                              @RequestParam("message") String message, Model model) throws Exception{
        emailService.sendEmail(sujet,message,email);
        model.addAttribute("success", true);

        return "contact";
    }

    @GetMapping(value = "/load")
    public String loadTestData() throws Exception{

        if(productService.getAll().size() == 0){
            Product p1 = new Product("PC Dell", 1500, 10);
            Product p2 = new Product("Ecran HP", 99, 5);
            Product p3 = new Product("Table", 45, 7);
            Product p4 = new Product("Copieur", 750, 2);

            productService.create(p1);
            productService.create(p2);
            productService.create(p3);
            productService.create(p4);

        }

        if(utilisateurService.getAll().size() == 0){
            utilisateurService.saveOrUpdate(new Utilisateur("admin", "admin", true, "".getBytes()));
            utilisateurService.saveOrUpdate(new Utilisateur("user", "user", false, "".getBytes()));
        }

        return "redirect:/";
    }
}
