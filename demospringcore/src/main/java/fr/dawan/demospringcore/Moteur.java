package fr.dawan.demospringcore;

import org.springframework.stereotype.Component;

@Component
public class Moteur {

    public void demarrer(){
        System.out.println("Moteur démarré......");
    }
}
