package tn.esprit.models;


import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Panne {
    private int id, atelier_id, etat;
    private String localisation, panne, description ;
    private Date date;

//    @OneToMany(mappedBy = "panne")
//    private List<Panne> pannes;
//
//    public List<Panne> getPannes() {
//        return pannes;
//    }
//
//    public void setPannes(List<Panne> pannes) {
//        this.pannes = pannes;
//    }

    public Panne() {
    }

    public Panne(int id, int atelier_id, int etat, String localisation, String panne, String description, Date date) {
        this.id = id;
        this.atelier_id = atelier_id;
        this.etat = etat;
        this.localisation = localisation;
        this.panne = panne;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAtelier_id() {
        return atelier_id;
    }

    public void setAtelier_id(int atelier_id) {
        this.atelier_id = atelier_id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPanne() {
        return panne;
    }

    public void setPanne(String panne) {
        this.panne = panne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panne panne1 = (Panne) o;
        return id == panne1.id &&
                atelier_id == panne1.atelier_id &&
                etat == panne1.etat &&
                Objects.equals(localisation, panne1.localisation) &&
                Objects.equals(panne, panne1.panne) &&
                Objects.equals(description, panne1.description) &&
                Objects.equals(date, panne1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, atelier_id, etat, localisation, panne, description, date);
    }
}
