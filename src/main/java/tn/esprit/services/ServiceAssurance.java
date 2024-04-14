package tn.esprit.services;


import tn.esprit.utils.MyDataBase;
import tn.esprit.interfaces.IService;
import tn.esprit.models.Assurance;

import java.sql.*;
import java.util.ArrayList;

public class ServiceAssurance implements IService<Assurance> {

    private Connection cnx ;

    public ServiceAssurance(){
        cnx = MyDataBase.getInstance().getCnx();
        if (cnx == null) {
            // Handle null connection (e.g., throw an exception, log an error)
            throw new IllegalStateException("Database connection is null");
        }
    }
    @Override
    public void add(Assurance assurance) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `assurance`(nom_assurance, adresse_assurance, code_postal_assurance, tel_assurance, email_assurance) VALUES (?,?,?)";
        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);

            pstm.setString(1,assurance.getNom_assurance());
            pstm.setString(2,assurance.getAdresse_assurance());
            pstm.setString(3,assurance.getCode_postal_assurance());
            pstm.setString(4,assurance.getTel_assurance());
            pstm.setString(5,assurance.getEmail_assurance());
            pstm.setInt(5,assurance.getId());


            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }

    @Override
    public ArrayList<Assurance> getAll() {
        //1-req SELECT
        //2-recuperation de la base de donné remplissage dans Array
        //3-retour du tableau done
        ArrayList<Assurance> assurances = new ArrayList<>();
        String qry ="SELECT * FROM `assurance`";
        try {
            Statement stm = cnx.createStatement();

          ResultSet rs = stm.executeQuery(qry);

          while (rs.next()){
              Assurance a = new Assurance();

              a.setId(rs.getInt(1));
              a.setNom_assurance(rs.getString("nom_assurance"));
              a.setAdresse_assurance(rs.getString("adresse_assurance"));
              a.setCode_postal_assurance(rs.getString("code_postal_assurance"));
              a.setTel_assurance(rs.getString("tel_assurance"));
              a.setEmail_assurance(rs.getString("email_assurance"));

              assurances.add(a);
          }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }


        return assurances;
    }

    @Override
    public void update(Assurance assurance) {

    }

    @Override
    public boolean delete(Assurance assurance) {
        return false;
    }
}
