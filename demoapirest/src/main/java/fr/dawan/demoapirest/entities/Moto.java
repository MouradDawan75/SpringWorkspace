package fr.dawan.demoapirest.entities;

import jakarta.persistence.Entity;

@Entity
public class Moto extends Vehicule{

    private int cylindree;

    public int getCylindree() {
        return cylindree;
    }

    public void setCylindree(int cylindree) {
        this.cylindree = cylindree;
    }
}
