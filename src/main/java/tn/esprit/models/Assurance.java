package tn.esprit.models;

public class Assurance {
   private  String nom_assurance, adresse_assurance, code_postal_assurance, tel_assurance, email_assurance;
private int id ;



    public Assurance() {
    }


    public String getNom_assurance() {
        return nom_assurance;
    }

    public void setNom_assurance(String nom_assurance) {

        this.nom_assurance = nom_assurance;
    }

    public String getAdresse_assurance() {
        return adresse_assurance;
    }

    public void setAdresse_assurance(String adresse_assurance) {

        this.adresse_assurance = adresse_assurance;
    }

    public String getCode_postal_assurance() {
        return code_postal_assurance;
    }

    public void setCode_postal_assurance(String code_postal_assurance) {

        this.code_postal_assurance = code_postal_assurance;
    }

    public String getTel_assurance() {
        return tel_assurance;
    }

    public void setTel_assurance(String tel_assurance) {

        this.tel_assurance = tel_assurance;
    }

    public String getEmail_assurance() {
        return email_assurance;
    }

    public void setEmail_assurance(String email_assurance) {

        this.email_assurance = email_assurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Assurance(int id, String  nom_assurance, String adresse_assurance, String code_postal_assurance, String tel_assurance, String email_assurance) {
        this.id = id;
        this.nom_assurance = nom_assurance;
        this.adresse_assurance = adresse_assurance;
        this.code_postal_assurance = code_postal_assurance;
        this.tel_assurance = tel_assurance;
        this.email_assurance = email_assurance;

    }




    @Override
    public String toString() {
        return "Assurance{" +
                "id=" + id +
                ", nom_assurance=" + nom_assurance +
                ", adresse_assurance='" + adresse_assurance + '\'' +
                ", code_postal_assurance='" + code_postal_assurance + '\'' +
                ", tel_assurance='" + tel_assurance + '\'' +
                ", email_assurance='" + email_assurance + '\'' +
                "}\n";
    }
}
