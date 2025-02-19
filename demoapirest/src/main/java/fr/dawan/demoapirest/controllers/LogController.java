package fr.dawan.demoapirest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.demoapirest.dtos.LogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    /*
    Controller destiné aux admin de l'api: consultation des fichiers des logs à distance
     */

    private static Logger rootLogger = LoggerFactory.getLogger(LogController.class);
    private static  Logger debugLogger = LoggerFactory.getLogger("debugLogger");

    @Value("${storage.folder}")
    private String storageFolder;

    //ObjectMapper: fait partie de la dépendance Jackson utilisée par Spring pour la sérialisation json
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/{fileName}", produces = "application/octet-stream")
    public ResponseEntity<Resource> getLogFile(@PathVariable("fileName") String fileName) throws Exception{

        File f = new File(storageFolder+"/"+fileName);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
        /*
        Soit afficher le contenu du fichier dans le navigateur.
        Soit générer un lien de téléchargement
        Ajoutez ce paramétre dans le header de la réponse: Content-disposition, attachment;filename=nom_fichier
         */

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .contentLength(f.length()) //optionnel
                .contentType(MediaType.APPLICATION_OCTET_STREAM) //optionnel
                .body(resource);
    }

    //Méthode pour insérer un log dans le fichier des logs

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postLog(@RequestBody LogDto logDto) throws Exception{
        /*
        Conversion du logDto en string
        ObjectMapper: conversion d'objet et string et vice versa
        ModelMapper: conversion d'entity en dto et vice versa
         */

        String logString = objectMapper.writeValueAsString(logDto);

        switch (logDto.getLevel()){
            case INFO:
                rootLogger.info(logString);
                break;

            case ERROR:
                rootLogger.error(logString);
                break;

            case WARN:
                rootLogger.warn(logString);
                break;

            case DEBUG:
                debugLogger.debug(logString);
                break;
        }

        return ResponseEntity.status(HttpStatus.OK).body("Ecriture du log OK.");
    }

}
