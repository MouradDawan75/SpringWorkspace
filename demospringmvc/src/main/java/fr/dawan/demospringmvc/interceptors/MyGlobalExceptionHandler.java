package fr.dawan.demospringmvc.interceptors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String displayErrorPage(Exception ex, Model model){
        /*
        En cas de besoin, possibilité de fournir des sonnées à la page d'erreur.
        model.addAttribute("exceptionMessage", ex.getMessage());

         */
        return "erreur";
    }


    /* On a la possibilité d'afficher des pages perso. selon le type de l'exception
    @ExceptionHandler(NullPointerException.class)
    public String displayOtherPage(){
        return "erreur1";
    }

     */
}
