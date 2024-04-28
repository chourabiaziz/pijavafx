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
        String qry ="INSERT INTO `assurance`(nom_assurance, adresse_assurance, code_postal_assurance, tel_assurance, email_assurance) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);

            pstm.setString(1,assurance.getNom_assurance());
            pstm.setString(2,assurance.getAdresse_assurance());
            pstm.setString(3,assurance.getCode_postal_assurance());
            pstm.setString(4,assurance.getTel_assurance());
            pstm.setString(5,assurance.getEmail_assurance());
          //  pstm.setInt(6,assurance.getId());


            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }

    @Override
    public  ArrayList<Assurance> getAll() {
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

    // Méthode pour modifier une assurance dans la base de données
    public boolean update(Assurance assurance) {
        try {
            cnx = MyDataBase.getInstance().getCnx();
            String query = "UPDATE assurance SET nom_assurance=?, adresse_assurance=?, code_postal_assurance=?, tel_assurance=?, email_assurance=? WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, assurance.getNom_assurance());
            pstmt.setString(2, assurance.getAdresse_assurance());
            pstmt.setString(3, assurance.getCode_postal_assurance());
            pstmt.setString(4, assurance.getTel_assurance());
            pstmt.setString(5, assurance.getEmail_assurance());

            pstmt.setInt(6, assurance.getId());

            int rowsAffected = pstmt.executeUpdate();

            // Si au moins une ligne a été affectée, cela signifie que la mise à jour a réussi
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'assurance: " + e.getMessage());
            return false; // La mise à jour a échoué
        }
    }



   public Assurance getById(int id) {
        Assurance assurance = null;
        String query = "SELECT * FROM assurance WHERE id = ?";
        try (PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    assurance = new Assurance();
                    assurance.setId(rs.getInt("id"));
                    assurance.setNom_assurance(rs.getString("nom_assurance"));
                    assurance.setAdresse_assurance(rs.getString("adresse_assurance"));
                    assurance.setCode_postal_assurance(rs.getString("code_postal_assurance"));
                    assurance.setTel_assurance(rs.getString("tel_assurance"));
                    assurance.setEmail_assurance(rs.getString("email_assurance"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'assurance par ID: " + e.getMessage());
        }
        return assurance;
    }


    @Override
    public boolean delete(Assurance assurance)
    {
        try {
            cnx = MyDataBase.getInstance().getCnx();
            String query = "DELETE FROM assurance WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, assurance.getId());

            int rowsAffected = pstmt.executeUpdate();

            // If at least one row is affected, deletion is successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'assurance: " + e.getMessage());
            return false; // Deletion failed
        }
    }
}
