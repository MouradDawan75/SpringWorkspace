package fr.dawan.demoapirest.entities;

import jakarta.persistence.Entity;

@Entity
public class Technicien extends Employe{

    private String poste;

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
