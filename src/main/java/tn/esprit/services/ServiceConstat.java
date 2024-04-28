package tn.esprit.services;



import tn.esprit.interfaces.IService;
import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;


public class ServiceConstat implements IService<Constat> {

    private Connection cnx ;

    public ServiceConstat(){

        cnx = MyDataBase.getInstance().getCnx();
        if (cnx == null) {
            // Handle null connection (e.g., throw an exception, log an error)
            throw new IllegalStateException("Database connection is null");
        }
    }

    @Override
    public void add(Constat constat) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `constat`( a_preneur_nom, a_preneur_prenom, a_preneur_tel, a_vehicule_moteur_marque, a_vehicule_moteur_num_immatriculation ,  a_societe_assurance_agence_nom, a_societe_assurance_agence_adresse," +
                "b_preneur_nom, b_preneur_prenom, b_preneur_tel, b_vehicule_moteur_marque, b_vehicule_moteur_num_immatriculation ,b_societe_assurance_agence_nom, b_societe_assurance_agence_adresse, " + // Ajout de la virgule ici
                "localisation ,temoins, " + // Ajout de la virgule ici
                "stationnement_arret, quittait_stationnement_arret, prenait_stationnement, sortait_dun_parking_lieu, doublait ,virait_droite ,virait_gauche)" +
                "  , VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);


            pstm.setString(1,constat.getA_preneur_nom());
            pstm.setString(2,constat.getA_preneur_prenom());
            pstm.setString(3,constat.getA_preneur_tel());
            pstm.setString(4,constat.getA_vehicule_moteur_marque());
            pstm.setString(5,constat.getA_vehicule_moteur_num_immatriculation());
            pstm.setString(6,constat.getA_societe_assurance_agence_nom());
            pstm.setString(7, constat.getA_societe_assurance_agence_adresse());


            pstm.setString(8, constat.getB_preneur_nom());
            pstm.setString(9, constat.getB_preneur_prenom());
            pstm.setString(10, constat.getB_preneur_tel());
            pstm.setString(11, constat.getB_vehicule_moteur_marque());
            pstm.setString(12, constat.getB_vehicule_moteur_num_immatriculation());
            pstm.setString(13, constat.getB_societe_assurance_agence_nom());
            pstm.setString(14, constat.getB_societe_assurance_agence_adresse());


            pstm.setString(15,constat.getLocalisation());
            pstm.setString(16,constat.getTemoins());

            pstm.setBoolean(17, constat.isStationnement_arret());
            pstm.setBoolean(18, constat.isQuittait_stationnement_arret());
            pstm.setBoolean(19, constat.isPrenait_stationnement());
            pstm.setBoolean(20, constat.isSortait_dun_parking_lieu());
            pstm.setBoolean(21, constat.isDoublait());
            pstm.setBoolean(22, constat.isVirait_droite());
            pstm.setBoolean(23, constat.isVirait_gauche());
            // pstm.setInt(24, constat.getId());


            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Constat> getAll() {
        //1-req SELECT
        //2-recuperation de la base de donné remplissage dans Array
        //3-retour du tableau done
        ArrayList<Constat> constats = new ArrayList<>();
        String qry ="SELECT * FROM `constat`";
        try {
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()){
                Constat c = new Constat();

                c.setId(rs.getInt(1));
                c.setA_preneur_nom(rs.getString("a_preneur_nom"));
                c.setA_preneur_prenom(rs.getString("a_preneur_prenom"));
                c.setA_preneur_tel(rs.getString("a_preneur_tel"));
                c.setA_vehicule_moteur_marque(rs.getString("a_vehicule_moteur_marque"));
                c.setA_vehicule_moteur_num_immatriculation(rs.getString("a_vehicule_moteur_num_immatriculation"));
                c.setA_societe_assurance_agence_nom(rs.getString("a_societe_assurance_agence_nom"));

                c.setB_preneur_nom(rs.getString("b_preneur_nom"));
                c.setB_preneur_prenom(rs.getString("b_preneur_prenom"));
                c.setB_preneur_tel(rs.getString("b_preneur_tel"));
                c.setB_vehicule_moteur_marque(rs.getString("b_vehicule_moteur_marque"));
                c.setB_vehicule_moteur_num_immatriculation(rs.getString("b_vehicule_moteur_num_immatriculation"));
                c.setB_societe_assurance_agence_nom(rs.getString("b_societe_assurance_agence_nom"));

                c.setLocalisation(rs.getString("localisation"));
                c.setTemoins(rs.getString("temoins"));


                // Récupération des attributs de type boolean
                c.setStationnement_arret(rs.getBoolean("stationnement_arret"));
                c.setQuittait_stationnement_arret(rs.getBoolean("quittait_stationnement_arret"));
                c.setPrenait_stationnement(rs.getBoolean("prenait_stationnement"));
                c.setSortait_dun_parking_lieu(rs.getBoolean("sortait_dun_parking_lieu"));
                c.setDoublait(rs.getBoolean("doublait"));
                c.setVirait_droite(rs.getBoolean("virait_droite"));
                c.setVirait_gauche(rs.getBoolean("virait_gauche"));


                constats.add(c);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return constats;
    }




    public Constat getById(int id) {
        Constat constat = null;
        String query = "SELECT * FROM constat WHERE id = ?";
        try (PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    constat = new Constat();
                    constat.setId(rs.getInt("id"));
                    constat.setA_preneur_nom(rs.getString("a_preneur_nom"));
                    constat.setA_preneur_prenom(rs.getString("a_preneur_prenom"));
                    constat.setA_preneur_tel(rs.getString("a_preneur_tel"));
                    constat.setA_vehicule_moteur_marque(rs.getString("a_vehicule_moteur_marque"));
                    constat.setA_vehicule_moteur_num_immatriculation(rs.getString("a_vehicule_moteur_num_immatriculation"));
                    constat.setA_societe_assurance_agence_nom(rs.getString("a_societe_assurance_agence_nom"));
                    constat.setA_societe_assurance_agence_adresse(rs.getString("a_societe_assurance_agence_adresse"));

                    constat.setB_preneur_nom(rs.getString("b_preneur_nom"));
                    constat.setB_preneur_prenom(rs.getString("b_preneur_prenom"));
                    constat.setB_preneur_tel(rs.getString("b_preneur_tel"));

                    constat.setB_vehicule_moteur_marque(rs.getString("b_vehicule_moteur_marque"));
                    constat.setB_vehicule_moteur_num_immatriculation(rs.getString("b_vehicule_moteur_num_immatriculation"));
                    constat.setB_societe_assurance_agence_nom(rs.getString("b_societe_assurance_agence_nom"));
                    constat.setB_societe_assurance_agence_adresse(rs.getString("b_societe_assurance_agence_adresse"));

                    constat.setLocalisation(rs.getString("localisation"));
                    constat.setTemoins(rs.getString("temoins"));


                    constat.setStationnement_arret(Boolean.parseBoolean(rs.getString("stationnement_arret")));
                    constat.setQuittait_stationnement_arret(Boolean.parseBoolean(rs.getString("quittait_stationnement_arret")));
                    constat.setPrenait_stationnement(Boolean.parseBoolean(rs.getString("prenait_stationnement")));
                    constat.setSortait_dun_parking_lieu(Boolean.parseBoolean(rs.getString("sortait_dun_parking_lieu")));
                    constat.setDoublait(Boolean.parseBoolean(rs.getString("doublait")));
                    constat.setVirait_droite(Boolean.parseBoolean(rs.getString("virait_droite")));
                    constat.setVirait_gauche(Boolean.parseBoolean(rs.getString("virait_gauche")));
                }
            }


        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de constat par ID: " + e.getMessage());
        }
          return constat ;
    }



    @Override
    public boolean update(Constat constat) {

        try {
            cnx = MyDataBase.getInstance().getCnx();
            String query = "UPDATE constat SET a_preneur_nom=?, a_preneur_prenom=?, a_preneur_tel=?, a_vehicule_moteur_marque=?, a_vehicule_moteur_num_immatriculation=?, a_societe_assurance_agence_nom=?, a_societe_assurance_agence_adresse=?," +
                    "b_preneur_nom=?, b_preneur_prenom=?, b_preneur_tel=?, b_vehicule_moteur_marque=?, b_vehicule_moteur_num_immatriculation=?, b_societe_assurance_agence_nom=?, b_societe_assurance_agence_adresse=?," +
                    "localisation=?, temoins=?," +
                    "stationnement_arret=?,quittait_stationnement_arret=?, prenait_stationnement=?,sortait_dun_parking_lieu=?, doublait=?, virait_droite=?, virait_gauche=?" +
                    " WHERE id=?";

            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, constat.getA_preneur_nom());
            pstmt.setString(2, constat.getA_preneur_prenom());
            pstmt.setString(3, constat.getA_preneur_tel());
            pstmt.setString(4, constat.getA_vehicule_moteur_marque());
            pstmt.setString(5, constat.getA_vehicule_moteur_num_immatriculation());
            pstmt.setString(6, constat.getA_societe_assurance_agence_nom());
            pstmt.setString(7, constat.getA_societe_assurance_agence_adresse());

            pstmt.setString(8, constat.getB_preneur_nom());
            pstmt.setString(9, constat.getB_preneur_prenom());
            pstmt.setString(10, constat.getB_preneur_tel());
            pstmt.setString(11, constat.getB_vehicule_moteur_marque());
            pstmt.setString(12, constat.getB_vehicule_moteur_num_immatriculation());
            pstmt.setString(13, constat.getB_societe_assurance_agence_nom());
            pstmt.setString(14, constat.getB_societe_assurance_agence_adresse());

            pstmt.setString(15, constat.getLocalisation());
            pstmt.setString(16, constat.getTemoins());

            pstmt.setBoolean(17, constat.isStationnement_arret());
            pstmt.setBoolean(18, constat.isQuittait_stationnement_arret());
            pstmt.setBoolean(19, constat.isPrenait_stationnement());
            pstmt.setBoolean(20, constat.isSortait_dun_parking_lieu());
            pstmt.setBoolean(21, constat.isDoublait());
            pstmt.setBoolean(22, constat.isVirait_droite());
            pstmt.setBoolean(23, constat.isVirait_gauche());

            pstmt.setInt(24, constat.getId());

            int rowsAffected = pstmt.executeUpdate();

            // Si au moins une ligne a été affectée, cela signifie que la mise à jour a réussi
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du constat " + e.getMessage());
            return false; // La mise à jour a échoué
        }
    }

    @Override
    public boolean delete(Constat constat) {
        System.out.println("Deleting constat with ID: " + constat.getId());

        try {
            cnx = MyDataBase.getInstance().getCnx();
            String query = "DELETE FROM constat WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, constat.getId());

            System.out.println("Delete query: " + query);
            System.out.println("Delete parameter: " + constat.getId());
            int rowsAffected = pstmt.executeUpdate();

            // If at least one row is affected, deletion is successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du constat " + e.getMessage());
            return false; // Deletion failed
        }
    }



}
