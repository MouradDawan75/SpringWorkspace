package fr.dawan.demoapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass // annotation qui ne génère pas de table mais qui permet aux classes filles de récupérer les attributs de cette classe
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
