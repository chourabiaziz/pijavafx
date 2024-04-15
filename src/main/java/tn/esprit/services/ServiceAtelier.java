package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Atelier;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceAtelier implements IService<Atelier> {

    private Connection cnx;

    public ServiceAtelier() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Atelier atelier) {
        String qry = "INSERT INTO `atelier`(`id`, `nom`, `adresse`, `numero_telephone`, `specialite`, `avis`, `heure_overture`, `heure_fermeture`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, atelier.getId());
            pstm.setString(2, atelier.getNom());
            pstm.setString(3, atelier.getAdresse());
            pstm.setString(4, atelier.getNumero_telephone());
            pstm.setString(5, atelier.getSpecialite());
            pstm.setString(6, atelier.getAvis());
            pstm.setTime(7, atelier.getHeure_overture());
            pstm.setTime(8, atelier.getHeure_fermeture());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'atelier : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Atelier> getAll() {
        ArrayList<Atelier> ateliers = new ArrayList<>();
        String qry = "SELECT * FROM `atelier`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Atelier atelier = new Atelier(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("numero_telephone"),
                        rs.getString("specialite"),
                        rs.getString("avis"),
                        rs.getTime("heure_overture"),
                        rs.getTime("heure_fermeture")
                );
                ateliers.add(atelier);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ateliers : " + e.getMessage());
        }
        return ateliers;
    }

    @Override
    public void update(Atelier atelier) {
        String qry = "UPDATE `atelier` SET `nom`=?, `adresse`=?, `numero_telephone`=?, `specialite`=?, `avis`=?, `heure_overture`=?, `heure_fermeture`=? WHERE `id`=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, atelier.getNom());
            pstm.setString(2, atelier.getAdresse());
            pstm.setString(3, atelier.getNumero_telephone());
            pstm.setString(4, atelier.getSpecialite());
            pstm.setString(5, atelier.getAvis());
            pstm.setTime(6, atelier.getHeure_overture());
            pstm.setTime(7, atelier.getHeure_fermeture());
            pstm.setInt(8, atelier.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'atelier : " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Atelier atelier) {
        String qry = "DELETE FROM `atelier` WHERE `id`=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, atelier.getId());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0; // Vérifie si des lignes ont été supprimées avec succès
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'atelier : " + e.getMessage());
            return false;
        }
    }
    // Méthode pour valider les données de l'atelier
    private boolean validateAtelier(Atelier atelier) {
        if (atelier.getNom() == null || atelier.getNom().isEmpty()) {
            System.out.println("Erreur : Le champ 'nom' est obligatoire.");
            return false;
        }

        if (atelier.getAdresse() == null || atelier.getAdresse().isEmpty()) {
            System.out.println("Erreur : Le champ 'adresse' est obligatoire.");
            return false;
        }

        // Vérification de la longueur maximale des champs
        final int MAX_LONGUEUR_NOM = 50;
        if (atelier.getNom() != null && atelier.getNom().length() > MAX_LONGUEUR_NOM) {
            System.out.println("Erreur : Le champ 'nom' ne doit pas dépasser " + MAX_LONGUEUR_NOM + " caractères.");
            return false;
        }

        // Vérification des numéros de téléphone
        if (atelier.getNumero_telephone() != null && !atelier.getNumero_telephone().matches("\\d{10}")) {
            System.out.println("Erreur : Le numéro de téléphone doit contenir exactement 10 chiffres.");
            return false;
        }

        // Validation des heures d'ouverture et de fermeture
        if (atelier.getHeure_overture() != null && atelier.getHeure_fermeture() != null) {
            // Vérifiez si l'heure d'ouverture est antérieure à l'heure de fermeture
            if (atelier.getHeure_overture().after(atelier.getHeure_fermeture())) {
                System.out.println("Erreur : L'heure d'ouverture doit être antérieure à l'heure de fermeture.");
                return false;
            }
        }



        return true;
    }

}
