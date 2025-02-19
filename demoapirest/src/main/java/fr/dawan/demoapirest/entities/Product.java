package fr.dawan.demoapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Product extends BaseEntity{

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

     */

    @Column(unique = true, nullable = false, length = 512)
    private String description;



    @Column(name = "priceht", precision = 2)
    private double price;



    //@Lob: large object
    //@Blob: Big Large Object
    //@Clob: Caracter Large Object
    //private byte[] productImage; //stocker le binaire de l'image dans la BD
    private String imagePath; // la BD contient uniquement le chemin du fichier

    public enum ProductState{
        NEUF,EXCELENT,MAUVAIS
    }

    @Enumerated(EnumType.STRING)
    private ProductState state;

    //champs multi lignes
    @Column(columnDefinition = "TEXT")
    private String comment;

    @ElementCollection //1 table product_ingredient (PK/FK: product_id)
    private Set<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "t_prices_promo")
    @MapKeyColumn(name = "promotion")
    @Column(name = "price")
    private Map<String, Double> priceByPromotion;

    //Champs ignoré lors du Mapping
    @Transient
    private String champsIgnore;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //product_id est une clé de jointure: gestion de la suppression en cascade
    @ManyToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private Set<Supplier> suppliers = new HashSet<>();

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<String, Double> getPriceByPromotion() {
        return priceByPromotion;
    }

    public void setPriceByPromotion(Map<String, Double> priceByPromotion) {
        this.priceByPromotion = priceByPromotion;
    }

    public String getChampsIgnore() {
        return champsIgnore;
    }

    public void setChampsIgnore(String champsIgnore) {
        this.champsIgnore = champsIgnore;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
