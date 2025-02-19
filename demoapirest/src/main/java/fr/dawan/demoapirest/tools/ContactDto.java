package fr.dawan.demoapirest.tools;

public class ContactDto{
private String nom;
private String prenom;

public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}

public String getPrenom() {
    return prenom;
}

public void setPrenom(String prenom) {
    this.prenom = prenom;
}

    @Override
    public String toString() {
        return "ContactDto{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
