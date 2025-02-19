package fr.dawan.demoapirest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDto {

    private int id;

    //Si besoin de modifier le nom d'un attribut dans le dto. On doit fournir dans @JsonProperty le nom de la propriété Json à mapper
    //@JsonProperty(value = "name")
  //private String nom;


    private String name;
    private String address;
    private String zipCode;
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
