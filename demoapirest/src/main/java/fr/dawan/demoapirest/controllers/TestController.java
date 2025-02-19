package fr.dawan.demoapirest.controllers;


import fr.dawan.demoapirest.dtos.LogDto;
import fr.dawan.demoapirest.exceptions.AuthentificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger rootLogger = LoggerFactory.getLogger(TestController.class);
    private static Logger debugLogger = LoggerFactory.getLogger("debugLogger");

    /*
    RestController: doit fournir des ressources - ends points + méthodes d'accès aux ressources
    Status d'une réponse HTTP:
    1xx: Informatiosn fournies par l'api
    2xx: succès d'exécution de la req. HTTP
    3xx: redirection
    4xx: erreurs côtés client
    5xx: erreurs côtés serveur
     */

    //@RequestMapping(method = RequestMethod.GET, value = "/test/m1")
    @GetMapping(value = "/m1", produces = "text/plain")
    public String m1(){
        rootLogger.info("info......");
        debugLogger.debug("debug.......");
        return "m1";
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/test/m2")
    @GetMapping(value = "/m2", produces = "text/plain")
    public ResponseEntity<String> m2(){
        /*
        ResponseEntity: permet de personnaliser la réponse à renvoyer au client
        Peut renvoyer le status de la réponse et le contenu du body
         */

        //Syntaxe1:
        //return ResponseEntity.ok("m2");

        //Syntaxe2: plus lisible
        return ResponseEntity.status(HttpStatus.OK).body("m2");

        //return "m2";
    }

    //Paramétres obligatoires

    @GetMapping(value = "/m3/{page}", produces = "text/plain")
    public ResponseEntity<String> m3(@PathVariable(value = "page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body("m3 "+page);
    }

    //Paramétres optionnels: multi uri

    @GetMapping(value = {"/m4/{page}", "/m4"}, produces = "text/plain")
    public String m4(@PathVariable(value = "page", required = false) Optional<Integer> page){
        if(page.isEmpty()){
            return "m4";
        }else{
            return "m4 "+page.get();
        }
    }

    //Request Param - paramétres nommés: www.localhost:8085/m5?page=3 ---- le paramétre s'affiche dans l'url (syntaxe non recommandée)

    @GetMapping(value = "/m5")
    public String m5(@RequestParam(value = "page") int page){
        return "m5 "+page;
    }

    //Request Body
    @GetMapping(value = "/m6", consumes = "text/plain", produces = "text/plain")
    public String m6(@RequestBody String message){
       return "m6: "+message;
    }

    @GetMapping(value = "/exception")
    public String testException() throws AuthentificationException {
        throw new AuthentificationException("Erreur: echec connexion....");
    }

    @GetMapping(value = "/genericException")
    public String genericException() throws Exception {
        throw new Exception("Générique exception.....");
    }

    /*
    2 niveau de gestion des exceptions:
    - Niveau controller:


    - Niveau global (application)
    Créer un intercepteur d'exception:
     * Classe qui hérite de la classe ResponseEntityExceptionHandler avec l'annotation @ControlerAdvice
     * Définir une méthode pour chaque type d'exception possible


     */

    // Niveau COntroller: gère toutes les exxceptions que peuvent générer toutes les méthodes
/*
    @ExceptionHandler(AuthentificationException.class)
    public ResponseEntity<LogDto> exceptionHandler(){
        LogDto logDto = new LogDto();
        logDto.setCode(403);
        logDto.setMessage("Echec connexion....");
        logDto.setLevel(LogDto.LogLevel.ERROR);
        logDto.setLogType(LogDto.LogType.ACCESS);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(logDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<LogDto> genericExceptionHandler(){
        LogDto logDto = new LogDto();
        logDto.setCode(500);
        logDto.setMessage("Server Error");
        logDto.setLevel(LogDto.LogLevel.ERROR);
        logDto.setLogType(LogDto.LogType.EXCEPTION);

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(logDto);
    }

 */
}
