package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Personne;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServicePersonne implements IService<Personne> {

    private Connection cnx ;

    public ServicePersonne(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public void add(Personne personne) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `personne`(`nom`, `prenom`, `age`) VALUES (?,?,?)";
        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);

            pstm.setString(1,personne.getNom());
            pstm.setString(2,personne.getPrenom());
            pstm.setInt(3,personne.getAge());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Personne> getAll() {
        //1-req SELECT
        //2-recuperation de la base de donné remplissage dans Array
        //3-retour du tableau done
        ArrayList<Personne> personnes = new ArrayList<>();
        String qry ="SELECT * FROM `personne`";
        try {
            Statement stm = cnx.createStatement();

          ResultSet rs = stm.executeQuery(qry);

          while (rs.next()){
              Personne p = new Personne();
              p.setId(rs.getInt(1));
              p.setNom(rs.getString("nom"));
              p.setPrenom(rs.getString(3));
              p.setAge(rs.getInt("age"));

              personnes.add(p);
          }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return personnes;
    }

    @Override
    public void update(Personne personne) {

    }

    @Override
    public boolean delete(Personne personne) {
        return false;
    }
}
