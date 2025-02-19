package fr.dawan.demoapirest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.ProductDto;
import fr.dawan.demoapirest.entities.Product;
import fr.dawan.demoapirest.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${storage.folder}")
    private String storageFolder;


    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
   public List<ProductDto> getAllBy(@PathVariable("page") int page, @PathVariable("size") int size,
                                    @PathVariable(value = "search", required = false) Optional<String> search) throws Exception{

        if(search.isPresent()){
            return productService.getAllBy(page,size,search.get());
        }else{
            return productService.getAllBy(page,size,"");
        }

   }

    @GetMapping(value = {"/count/{search}", "/count"}, produces = "application/json")
   public CountDto countBy(@PathVariable(value = "search", required = false) Optional<String> search) throws Exception{
        if(search.isPresent()){
            return productService.countBy(search.get());
        }else{
            return productService.countBy("");
        }
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<Object> getById(@PathVariable("id") long id) throws Exception{

        ProductDto dto = productService.findById(id);
        if(dto != null){
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id = "+id+" not found.");
        }
   }

   @DeleteMapping(value = "/{id}", produces = "text/plain")
   public ResponseEntity<String> deleteById(@PathVariable("id") long id) throws Exception{

       ProductDto dto = productService.findById(id);
       if(dto != null){
           productService.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Product deleted.");
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id = "+id+" not found.");
       }
   }

   @PostMapping(value="/save", consumes = "multipart/form-data", produces = "application/json")
   public ResponseEntity<ProductDto> saveOrUpdate(@RequestParam("file") MultipartFile file, @RequestParam("productString") String productString) throws Exception{

        //Gestion du param productString
       ProductDto productDto = objectMapper.readValue(productString, ProductDto.class);

       //Gestion du param file
       String filePath = "/"+productDto.getDescription()+"-"+file.getOriginalFilename();
       String path = storageFolder+filePath;
       File f = new File(path);
       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
       bos.write(file.getBytes());
       bos.close();

       productDto.setImagePath(filePath);
       ProductDto savedProduct = productService.saveOrUpdate(productDto);

       return ResponseEntity.status(HttpStatus.OK).body(savedProduct);

   }

    @GetMapping(value = "/image/{productId}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("productId") long id) throws Exception{

        //appel de getById pour récupérer le Produit depuis la BD
        ProductDto p = productService.findById(id);

        /*
        Paths.get("."): revenir au dossier racine
         */
        Path path = Paths.get(".").resolve(storageFolder+p.getImagePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+resource.getFilename())
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath())) //préciser le type effectif du contenu duf fichier - à ajouter en cas de besoin
                .body(resource);

    }

}
