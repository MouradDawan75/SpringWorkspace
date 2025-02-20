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

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private IProductService productService;

    @GetMapping("/display")
    public String display(Model model) throws Exception{
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
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
        model.addAttribute("products", productService.getAll());

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
        List<Product> products = productService.findByKey(key);
        model.addAttribute("products", products);
        return "products";
    }


    @ModelAttribute("productForm")
    public ProductForm getProductForm(){
        return new ProductForm();
    }
}
