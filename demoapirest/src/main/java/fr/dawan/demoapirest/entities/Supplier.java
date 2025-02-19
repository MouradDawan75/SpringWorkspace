package fr.dawan.demoapirest.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier extends BaseEntity{

    private String name;

    //supplier_id est une clé de jointure: gestion de la suppression en cascade
    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Product> products = new HashSet<>();



    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Mapping avec la propriété productsIds du du SupplierDto

    public Set<Long> getProductsIds(){
        Set<Long> result = new HashSet<>();
        for(Product p : products){
            result.add(p.getId());
        }

        return  result;
    }
}
