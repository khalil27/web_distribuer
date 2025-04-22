package tn.esprit.ms.competence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Competence {

    @Id
    @GeneratedValue
    private int id;

    private String nom, description, categorie;
    private Integer niveau;

    public Competence() {
    }

    public Competence(String nom, String description, Integer niveau, String categorie) {
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}