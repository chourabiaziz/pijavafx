package tn.esprit.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Contrat {

    private int id, prix, engagement;
    private String client, couverture;
    private Date debut, fin;
    private List<Facture> factures;
    public Contrat(int id, int prix, int engagement, String couverture, String debutStr, String finStr) {
        this.id = id;
        this.prix = prix;
        this.engagement = engagement;
        this.couverture = couverture;
        this.debut = convertToDate(debutStr);
        this.fin = convertToDate(finStr);
        this.client = client;

    }

    public Contrat(int id, int prix, int engagement, String couverture, Date debut, Date fin) {
        this.id = id;
        this.prix = prix;
        this.engagement = engagement;
        this.couverture = couverture;
        this.debut = debut;
        this.fin = fin;


    }

    public Contrat() {
    }

    private Date convertToDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date parsedDate = format.parse(dateStr);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle this error appropriately in your application
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getEngagement() {
        return engagement;
    }

    public void setEngagement(int engagement) {
        this.engagement = engagement;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Contrat N°" + id + ", prix=" + prix + ", engagement=" + engagement + ", couverture=" + couverture
                + ", debut=" + debut + ", fin=" + fin + "]";
    }


}
