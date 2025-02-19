package fr.dawan.demoapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends BaseEntity {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

 */

    @Column(unique = true, nullable = false)
    private String name;

    /*
    Chargement des relations:
    Lazy: chargement tardif - chargement à la demande
    Eager: chargement immédiat

    Spring Data utilise le mode Lazy pour les relations Many et le mode Eager pour le One

     */

    @OneToMany(mappedBy = "category" /*fetch = FetchType.EAGER*/, cascade = CascadeType.REMOVE) // category_id est une clé de jointure
    private Set<Product> products = new HashSet<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
