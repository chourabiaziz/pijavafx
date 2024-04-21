package tn.esprit.services;



import tn.esprit.interfaces.IService;
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
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?)";

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

    @Override
    public boolean update(Constat constat) {
return false ;
    }

    @Override
    public boolean delete(Constat constat) {
        return false;
    }
}
