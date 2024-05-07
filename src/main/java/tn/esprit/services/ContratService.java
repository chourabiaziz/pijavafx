package tn.esprit.services;


import tn.esprit.interfaces.IContrat;
import tn.esprit.models.Contrat;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ContratService implements IContrat<Contrat> {

    Connection cnx = MyDataBase.getInstance().getCnx();

    @Override
    public boolean add(Contrat c) {

        String sql = "INSERT INTO `contrat`(`id`, `couverture`, `prix`, `debut`, `fin`, `engagement`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {

            pstmt.setInt(1, c.getId());
            pstmt.setString(2, c.getCouverture());
            pstmt.setInt(3, c.getPrix());
            pstmt.setDate(4, c.getDebut());
            pstmt.setDate(5, c.getFin());
            pstmt.setInt(6, c.getEngagement());

            int res = pstmt.executeUpdate();

            if (res > 0) {
                System.out.println("Ajout effectuée avec succès !");
                return true ;
            } else {
                System.out.println("Aucune ajout effectuée ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false ;
        }

        return false;
    }

    @Override
    public void edit(Contrat c) {
        String req = "UPDATE contrat SET couverture=?, prix=?, debut=?, fin=?, engagement=? WHERE id=?";
        try (PreparedStatement pstmt = cnx.prepareStatement(req)) {
            pstmt.setString(1, c.getCouverture());
            pstmt.setInt(2, c.getPrix());
            pstmt.setDate(3, c.getDebut());
            pstmt.setDate(4, c.getFin());
            pstmt.setInt(5, c.getEngagement());
            pstmt.setInt(6, c.getId());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Modification effectuée avec succès !");
            } else {
                System.out.println("Vérifier l' id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM contrat WHERE id=?";
        try (PreparedStatement pstmt = cnx.prepareStatement(req)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("Suppression effectuée avec succès !");
            } else {
                System.out.println("Aucune ligne supprimée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            // Handle the exception more gracefully, e.g., log the error or display a user-friendly message
            e.printStackTrace();
        }
    }

    @Override
    public List<Contrat> getAll() {
        List<Contrat> contrats = new ArrayList<>();
        String req = "SELECT * FROM contrat";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                int prix = res.getInt("prix");
                int engagement = res.getInt("engagement");
                String couverture = res.getString("couverture");
                Date debut = res.getDate("debut");
                Date fin = res.getDate("fin");

                Contrat c = new Contrat(id, prix, engagement, couverture, debut, fin);
                contrats.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrats;

    }

    @Override
    public Contrat getById(int id) {
        String req = "SELECT * FROM contrat WHERE id = ?";


        Contrat contrat = null;
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id); // Set the value of the id parameter
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int contratId = rs.getInt("id");
                int prix = rs.getInt("prix");
                int engagement = rs.getInt("engagement");
                String couverture = rs.getString("couverture");
                Date debut = rs.getDate("debut");
                Date fin = rs.getDate("fin");

                contrat = new Contrat(contratId, prix, engagement, couverture, debut, fin);
                return contrat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contrat;

    }

}