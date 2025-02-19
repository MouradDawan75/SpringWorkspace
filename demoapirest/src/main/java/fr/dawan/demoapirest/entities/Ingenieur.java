package fr.dawan.demoapirest.entities;

import jakarta.persistence.Entity;

@Entity
public class Ingenieur extends Employe{

    private int nbProjets;

    public int getNbProjets() {
        return nbProjets;
    }

    public void setNbProjets(int nbProjets) {
        this.nbProjets = nbProjets;
    }
}
