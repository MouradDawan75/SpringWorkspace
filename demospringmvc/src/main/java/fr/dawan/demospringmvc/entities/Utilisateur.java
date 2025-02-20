package fr.dawan.demospringmvc.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private boolean admin;

    @Lob
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @Transient
    private String base64Image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Utilisateur() {
    }

    public Utilisateur(long id, String username, String password, boolean admin, byte[] photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.photo = photo;

    }

    public Utilisateur(String username, String password, boolean admin, byte[] photo) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.photo = photo;

    }
}
