package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.entities.Product;
import fr.dawan.demospringmvc.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = {"","/"})
    public String accueil(){
        return "index";
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

        return "redirect:/";
    }
}
