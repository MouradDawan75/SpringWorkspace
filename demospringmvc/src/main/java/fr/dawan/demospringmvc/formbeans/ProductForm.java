package fr.dawan.demospringmvc.formbeans;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public class ProductForm {

    private long id;

    @NotEmpty(message = "Ce champs est obligatoire")
    private String description;

    @DecimalMin(value = "10", message = "Prix min 10")
    @DecimalMax(value = "1000", message = "Prix max 1000")
    private double price;

    @Min(1)
    @Max(100)
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
