package fr.dawan.demoapirest.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Manager3 extends BaseEntity{

    private String name;

    @OneToOne(mappedBy = "manager", cascade = CascadeType.REMOVE)
    private Player3 player;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player3 getPlayer() {
        return player;
    }

    public void setPlayer(Player3 player) {
        this.player = player;
    }
}
