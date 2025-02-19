package fr.dawan.demoapirest.controllers;

import fr.dawan.demoapirest.entities.Product;
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

@RestController
public class UploadDownloadController {

    @Value("${storage.folder}")
    private String storageFolder;

    //upload

    @PostMapping(value = "/upload-image/{productId}", produces = "application/json", consumes = "multipart/form-data")
    public ResponseEntity<Product> uploadImage(@PathVariable("productId") long id, @RequestParam("file")MultipartFile file) throws Exception{

        //Construction du path + personnalisation du nom du fichier
        String path = storageFolder+"/"+id+"-"+file.getOriginalFilename();
        File f = new File(path);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
        bos.write(file.getBytes());
        bos.close();

        //sauvegarde du produit en BD
        Product p = new Product();
        p.setId(id);
        p.setImagePath(path);

        //save en BD
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    //download

    @GetMapping(value = "/image/{productId}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("productId") long id) throws Exception{

        //appel de getById pour récupérer le Produit depuis la BD
        Product p = new Product();
        p.setImagePath("5-dell.jpg");


        Path path = Paths.get(storageFolder).resolve(p.getImagePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+resource.getFilename())
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath())) //préciser le type effectif du contenu duf fichier - à ajouter en cas de besoin
                .body(resource);

    }
}
