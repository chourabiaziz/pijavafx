package tn.esprit.services;



import tn.esprit.interfaces.IService;
import tn.esprit.models.Constat;

import java.sql.*;
import java.util.ArrayList;


public class ServiceConstat implements IService<Constat> {

    private Connection cnx ;

    public ServiceConstat(){
        cnx = tn.esprit.utils.MyDataBase.getInstance().getCnx();
    }
    @Override
    public void add(Constat constat) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `constat`(a_preneur_nom, a_preneur_prenom, a_preneur_tel, a_vehicule_moteur_marque, a_vehicule_moteur_num_immatriculation , a_societe_assurance_agence_nom) VALUES (?,?,?)";
        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);

            pstm.setString(1,constat.getA_preneur_nom());
            pstm.setString(2,constat.getA_preneur_prenom());
            pstm.setString(3,constat.getA_preneur_tel());
            pstm.setString(4,constat.getA_vehicule_moteur_marque());
            pstm.setString(5,constat.getA_vehicule_moteur_num_immatriculation());
            pstm.setString(3,constat.getA_societe_assurance_agence_nom());

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


                constats.add(c);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return constats;
    }

    @Override
    public void update(Constat assurance) {

    }

    @Override
    public boolean delete(Constat assurance) {
        return false;
    }
}
