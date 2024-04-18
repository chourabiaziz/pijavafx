package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Voiture;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceVoiture implements IService<Voiture> {

    private Connection cnx;

    public ServiceVoiture() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Voiture voiture) {
        //1-req sql INSERT
        //2-executer req
        String qry = "INSERT INTO `voiture`(`id`, `marque`, `modele`, `numero_serie`, `type_carburant`, `numero_immatriculation`, `kilometrage`, `couleur`, `prix_achat`, `prix_actuel`, `date_achat`, `carte_grise`, `nom_image`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);

            pstm.setInt(1, voiture.getId());
            pstm.setString(2, voiture.getMarque());
            pstm.setString(3, voiture.getModele());
            pstm.setString(4, voiture.getNumero_serie());
            pstm.setString(5, voiture.getType_carburant());
            pstm.setString(6, voiture.getNumero_immatriculation());
            pstm.setInt(7, voiture.getKilometrage());
            pstm.setString(8, voiture.getCouleur());
            pstm.setDouble(9, voiture.getPrix_achat());
            pstm.setDouble(10, voiture.getPrix_actuel());
            pstm.setDate(11, new Date(2023,02,02));
            pstm.setString(12, voiture.getCarte_grise());
            pstm.setString(13, voiture.getNom_image());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Voiture> getAll() {
        ArrayList<Voiture> voitures = new ArrayList<>();
        String qry = "SELECT * FROM `voiture`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Voiture v = new Voiture();
                v.setId(rs.getInt("id"));
                v.setMarque(rs.getString("marque"));
                v.setModele(rs.getString("modele"));
                v.setNumero_serie(rs.getString("numero_serie"));
                v.setType_carburant(rs.getString("type_carburant"));
                v.setNumero_immatriculation(rs.getString("numero_immatriculation"));
                v.setKilometrage(rs.getInt("kilometrage"));
                v.setCouleur(rs.getString("couleur"));
                v.setPrix_achat(rs.getInt("prix_achat"));
                v.setPrix_actuel(rs.getInt("prix_actuel"));
                v.setDate_achat(rs.getDate("date_achat"));
                v.setCarte_grise(rs.getString("carte_grise"));
                v.setNom_image(rs.getString("nom_image"));

                voitures.add(v);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des voitures : " + e.getMessage());
        }

        return voitures;
    }


    @Override
    public void update(Voiture voiture) {
        String qry = "UPDATE `voiture` SET `marque`=?, `modele`=?, `numero_serie`=?, `type_carburant`=?, `numero_immatriculation`=?, `kilometrage`=?, `couleur`=?, `prix_achat`=?, `prix_actuel`=?, `date_achat`=?, `carte_grise`=?, `nom_image`=? WHERE `id`=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1, voiture.getMarque());
            pstm.setString(2, voiture.getModele());
            pstm.setString(3, voiture.getNumero_serie());
            pstm.setString(4, voiture.getType_carburant());
            pstm.setString(5, voiture.getNumero_immatriculation());
            pstm.setInt(6, voiture.getKilometrage());
            pstm.setString(7, voiture.getCouleur());
            pstm.setDouble(8, voiture.getPrix_achat());
            pstm.setDouble(9, voiture.getPrix_actuel());
            pstm.setDate(10, new Date(voiture.getDate_achat().getTime())); // Convertir la date en java.sql.Date
            pstm.setString(11, voiture.getCarte_grise());
            pstm.setString(12, voiture.getNom_image());
            pstm.setInt(13, voiture.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la voiture : " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Voiture voiture) {
        String qry = "DELETE FROM `voiture` WHERE `id`=?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setInt(1, voiture.getId());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0; // Vérifie si des lignes ont été supprimées avec succès
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la voiture : " + e.getMessage());
            return false;
        }
    }

}
