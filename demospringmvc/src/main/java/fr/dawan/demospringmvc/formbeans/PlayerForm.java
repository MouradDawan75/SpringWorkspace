package fr.dawan.demospringmvc.formbeans;

import fr.dawan.demospringmvc.validators.PhoneNumberConstraint;
import jakarta.validation.constraints.*;

public class PlayerForm {

    @NotEmpty(message = "Champs obligatoire")
    private String name;

    @NotEmpty(message = "Champs obligatoire")
    private String role;

    @Min(18)
    @Max(64)
    private int age;

    @DecimalMin(value = "100", message = "Valeur min 100")
    @DecimalMax(value = "1000", message = "Valeur max 1000")
    private double size;

    @PhoneNumberConstraint(message = "Numéro invalide.....")
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
