package fr.dawan.demospringcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Voiture {

    /*
    Dépendance de type composition moins forte d'une dépendance de type héritage
     */

    //@Autowired: demande à Spring d'injecter l'objet moteur dans l'objet voiture
    @Autowired
    private Moteur moteur;



    public void Demarrer(){
        moteur.demarrer();
    }
}
