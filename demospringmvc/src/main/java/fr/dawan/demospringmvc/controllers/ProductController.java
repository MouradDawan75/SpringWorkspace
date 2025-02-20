package fr.dawan.demospringmvc.controllers;

import fr.dawan.demospringmvc.entities.Product;
import fr.dawan.demospringmvc.formbeans.ProductForm;
import fr.dawan.demospringmvc.services.IProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Controller
@RequestMapping("products")
public class ProductController {

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private IProductService productService;

    @GetMapping("/display")
    public String display(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) throws Exception{
        //List<Product> products = productService.getAll();
       // model.addAttribute("products", products);
        displayPaging(model, page, size);
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) throws Exception{
        productService.deleteById(id);
        return "redirect:/products/display";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model) throws Exception{
        Product prod = productService.getById(id);
        ProductForm pForm = mapper.map(prod, ProductForm.class);

        model.addAttribute("productForm", pForm);
        //model.addAttribute("products", productService.getAll());
        displayPaging(model, 1, 3);

        return "products";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("productForm") ProductForm pForm, BindingResult bindingResult, Model model) throws Exception{

        if(bindingResult.hasErrors()){
            //form invalide
            model.addAttribute("products", productService.getAll());
            return "products";
        }


        Product product = mapper.map(pForm, Product.class);
        if(product.getId() == 0)
            productService.create(product);
        else
            productService.update(product);


        return "redirect:/products/display";
    }

    @GetMapping("/findByKey")
    public String findByKey(@RequestParam("motcle") String key, Model model) throws Exception{
        //List<Product> products = productService.findByKey(key);
        //model.addAttribute("products", products);
        model.addAttribute("key", key);
        displayPaging(model, 1, 3);
        return "products";
    }

    @GetMapping("/moins/{id}")
    public String moins(@PathVariable("id") long id, Model model) throws Exception{
        Product product = productService.getById(id);
        if(product.getQuantity() > 1){
            product.setQuantity(product.getQuantity() - 1);
            productService.update(product);
        }

        return "redirect:/products/display";
    }

    @GetMapping("/plus/{id}")
    public String plus(@PathVariable("id") long id, Model model) throws Exception{
        Product product = productService.getById(id);
        if(product.getQuantity() < 100){
            product.setQuantity(product.getQuantity() + 1);
            productService.update(product);
        }

        return "redirect:/products/display";
    }

    private void displayPaging(Model model, int page, int size) throws Exception{
        List<Product> allProducts = new ArrayList<>();

        String key = (String) model.getAttribute("key");

        if(key == null)
            allProducts = productService.getAll();
        else
            allProducts = productService.findByKey(key);

        //Calculer le nbre de pages
        int nbTotalProduct = allProducts.size();
        int totalPages = 0;

        if(nbTotalProduct <= 3){
            totalPages = 1;
        }else{
            if(nbTotalProduct % size == 0){
                totalPages = nbTotalProduct / size;
            }else{
                totalPages = nbTotalProduct / 3 + 1;
            }
        }


        model.addAttribute("size", size);

        //Construire une liste d'entiers pour l'afficher dans la pagination
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        if(key == null)
            model.addAttribute("products", productService.getAllPaging(page, size));
        else
            model.addAttribute("products", productService.getAllPagingByDescriptionContaining(page,size,key));

        ProductForm pForm = (ProductForm) model.getAttribute("productForm");
        if(pForm != null){
            model.addAttribute("productForm", pForm);
        }

    }


    @ModelAttribute("productForm")
    public ProductForm getProductForm(){
        return new ProductForm();
    }
}
