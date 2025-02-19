package fr.dawan.demoapirest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.demoapirest.dtos.CategoryDto;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.ProductDto;
import fr.dawan.demoapirest.services.ICategoryService;
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
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
   public List<CategoryDto> getAllBy(@PathVariable("page") int page, @PathVariable("size") int size,
                                     @PathVariable(value = "search", required = false) Optional<String> search) throws Exception{

        if(search.isPresent()){
            return categoryService.getAllBy(page,size,search.get());
        }else{
            return categoryService.getAllBy(page,size,"");
        }

   }

    @GetMapping(value = {"/count/{search}", "/count"}, produces = "application/json")
   public CountDto countBy(@PathVariable(value = "search", required = false) Optional<String> search) throws Exception{
        if(search.isPresent()){
            return categoryService.countBy(search.get());
        }else{
            return categoryService.countBy("");
        }
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<Object> getById(@PathVariable("id") long id) throws Exception{

        CategoryDto dto = categoryService.findById(id);
        if(dto != null){
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = "+id+" not found.");
        }
   }

   @DeleteMapping(value = "/{id}", produces = "text/plain")
   public ResponseEntity<String> deleteById(@PathVariable("id") long id) throws Exception{

       CategoryDto dto = categoryService.findById(id);
       if(dto != null){
           categoryService.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Category deleted.");
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = "+id+" not found.");
       }
   }

   @PostMapping(value="/save", consumes = "application/json", produces = "application/json")
   public ResponseEntity<CategoryDto> saveOrUpdate(@RequestBody CategoryDto catDto) throws Exception{

         CategoryDto savedCategory = categoryService.saveOrUpdate(catDto);

       return ResponseEntity.status(HttpStatus.OK).body(savedCategory);

   }

}
