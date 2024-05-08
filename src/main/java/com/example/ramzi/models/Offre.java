package com.example.ramzi.models;

public class Offre {
    private int id;
    private String titre;
    private String description;
    private  String assurance;
    private String email_assurance;
    private String num_assurance ;

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public String getEmail_assurance() {
        return email_assurance;
    }

    public void setEmail_assurance(String email_assurance) {
        this.email_assurance = email_assurance;
    }

    public String getNum_assurance() {
        return num_assurance;
    }

    public void setNum_assurance(String num_assurance) {
        this.num_assurance = num_assurance;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", assurance='" + assurance + '\'' +
                ", email_assurance='" + email_assurance + '\'' +
                ", num_assurance='" + num_assurance + '\'' +
                '}';
    }
}
