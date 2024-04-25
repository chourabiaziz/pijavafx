package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Panne;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePanne implements IService<Panne> {

    private Connection cnx;

    public ServicePanne() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Panne panne) {
        // Contrôles de saisie
        if (!validatePanne(panne)) {
            System.out.println("Erreur : Données de la panne non valides.");
            return;
        }

        // Obtenez la date actuelle
        java.util.Date currentDate = new java.util.Date();

        String qry = "INSERT INTO `panne`(`id`, `atelier_id`, `etat`, `localisation`, `panne`, `description`, `date`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, panne.getId());
            pstm.setInt(2, panne.getAtelier_id());
            pstm.setInt(3, panne.getEtat());
            pstm.setString(4, panne.getLocalisation());
            pstm.setString(5, panne.getPanne());
            pstm.setString(6, panne.getDescription());
            pstm.setDate(7, new java.sql.Date(currentDate.getTime())); // Utilisez la date actuelle

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la panne : " + e.getMessage());
        }
    }


    @Override
    public ArrayList<Panne> getAll() {
        List<Panne> pannes = new ArrayList<>();
        String qry = "SELECT * FROM `panne`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Panne panne = new Panne(
                        rs.getInt("id"),
                        rs.getInt("atelier_id"),
                        rs.getInt("etat"),
                        rs.getString("localisation"),
                        rs.getString("panne"),
                        rs.getString("description"),
                        rs.getDate("date")
                );
                pannes.add(panne);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des pannes : " + e.getMessage());
        }
        return (ArrayList<Panne>) pannes;
    }

    @Override
    public void update(Panne panne) {
        // Contrôles de saisie
        if (!validatePanne(panne)) {
            System.out.println("Erreur : Données de la panne non valides.");
            return;
        }

        String qry = "UPDATE `panne` SET `atelier_id`=?, `etat`=?, `localisation`=?, `panne`=?, `description`=?, `date`=? WHERE `id`=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, panne.getAtelier_id());
            pstm.setInt(2, panne.getEtat());
            pstm.setString(3, panne.getLocalisation());
            pstm.setString(4, panne.getPanne());
            pstm.setString(5, panne.getDescription());
            pstm.setDate(6, new java.sql.Date(panne.getDate().getTime()));
            pstm.setInt(7, panne.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la panne : " + e.getMessage());
        }

    }

    @Override
    public boolean delete(Panne panne) {
        String qry = "DELETE FROM `panne` WHERE `id`=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, panne.getId());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0; // Vérifie si des lignes ont été supprimées avec succès
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la panne : " + e.getMessage());
            return false;
        }
    }

    // Méthode pour valider les données de la panne
    private boolean validatePanne(Panne panne) {
        if (panne.getLocalisation() == null || panne.getLocalisation().isEmpty()) {
            System.out.println("Erreur : Le champ 'localisation' est obligatoire.");
            return false;
        }

        if (panne.getPanne() == null || panne.getPanne().isEmpty()) {
            System.out.println("Erreur : Le champ 'panne' est obligatoire.");
            return false;
        }

        if (panne.getDate() == null) {
            System.out.println("Erreur : La date de la panne est obligatoire.");
            return false;
        }

        return true;
    }

}
