package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.entities.Product;
import fr.dawan.demospringmvc.entities.Utilisateur;
import fr.dawan.demospringmvc.services.IProductService;
import fr.dawan.demospringmvc.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUtilisateurService utilisateurService;

    @GetMapping(value = {"","/"})
    public String accueil(){
        return "index";
    }

    @GetMapping(value ="/ex")
    public String testException() throws Exception {
        throw new Exception();
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
