package fr.dawan.demoapirest.controllers;

import fr.dawan.demoapirest.dtos.CategoryDto;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.SupplierDto;
import fr.dawan.demoapirest.services.ICategoryService;
import fr.dawan.demoapirest.services.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;


    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
   public List<SupplierDto> getAllBy(@PathVariable("page") int page, @PathVariable("size") int size,
                                     @PathVariable(value = "search", required = false) Optional<String> search) throws Exception{

        if(search.isPresent()){
            return supplierService.getAllBy(page,size,search.get());
        }else{
            return supplierService.getAllBy(page,size,"");
        }

   }

    @GetMapping(value = {"/count/{search}", "/count"}, produces = "application/json")
   public CountDto countBy(@PathVariable(value = "search", required = false) Optional<String> search) throws Exception{
        if(search.isPresent()){
            return supplierService.countBy(search.get());
        }else{
            return supplierService.countBy("");
        }
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<Object> getById(@PathVariable("id") long id) throws Exception{

        SupplierDto dto = supplierService.findById(id);
        if(dto != null){
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier with id = "+id+" not found.");
        }
   }

   @DeleteMapping(value = "/{id}", produces = "text/plain")
   public ResponseEntity<String> deleteById(@PathVariable("id") long id) throws Exception{

       SupplierDto dto = supplierService.findById(id);
       if(dto != null){
           supplierService.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Supplier deleted.");
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier with id = "+id+" not found.");
       }
   }

   @PostMapping(value="/save", consumes = "application/json", produces = "application/json")
   public ResponseEntity<SupplierDto> saveOrUpdate(@RequestBody SupplierDto supDto) throws Exception{

         SupplierDto savedSupplier = supplierService.saveOrUpdate(supDto);

       return ResponseEntity.status(HttpStatus.OK).body(savedSupplier);

   }

}
