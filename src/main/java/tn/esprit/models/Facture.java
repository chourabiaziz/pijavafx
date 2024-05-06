package tn.esprit.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Facture {

    private int id, totale, contrat, tva;
    private Date createdat;
    private boolean statut;
     private String client ;
    public Facture(int id, int totale, int tva, String debutStr, boolean statut, int contrat) {
        this.id = id;
        this.totale = totale;
        this.tva = tva;
        this.createdat = convertToDate(debutStr);
        this.statut = statut;
        this.contrat = contrat;

    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Facture(int id, int totale, int tva, Date createdat, boolean statut, int contrat) {
        this.id = id;
        this.totale = totale;
        this.tva = tva;
        this.createdat = createdat;
        this.statut = statut;
        this.contrat=contrat;

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

    public Facture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Facture [id=" + id + ", totale=" + totale + ", tva=" + tva + ", createdat=" + createdat + ", statut="
                + statut + ", contrats=" + contrat + "]";
    }

    public int getContrat() {
        return contrat;
    }

    public void setContrat(int contrat) {
        this.contrat = contrat;
    }

    /*
     * public void ajouterContrat(Contrat contrat) {
     * contrats.add(contrat); // Ajouter un contrat à la liste
     * }
     * 
     * public List<Contrat> getContrats() {
     * return contrats;
     * }
     */
}
