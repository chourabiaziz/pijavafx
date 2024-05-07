package tn.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import tn.esprit.interfaces.IFacture;
import tn.esprit.models.Facture;
import tn.esprit.utils.MyDataBase;

import javax.sql.DataSource;

public class FactureService implements IFacture<Facture> {
    Connection cnx = MyDataBase.getInstance().getCnx();

    @Override
    public void add(Facture f) {

        String sql = "INSERT INTO `facture`(`id`, `totale`, `tva`, `createdat`, `statut`, `contrat`) VALUES (?,?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, f.getId());
            pstmt.setInt(2, f.getTotale());
            pstmt.setInt(3, f.getTva());
            pstmt.setDate(4, f.getCreatedat());
            pstmt.setBoolean(5, f.isStatut());
            pstmt.setInt(6, f.getContrat());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la facture : " + e.getMessage());
        }
    }

    @Override
    public void edit(Facture c) {
        String req = "UPDATE facture SET totale=?, tva=?, createdat=?, statut=? WHERE id=?";
        try (PreparedStatement pstmt = cnx.prepareStatement(req)) {
            pstmt.setInt(1, c.getTotale());
            pstmt.setInt(2, c.getTva());
            pstmt.setDate(3, c.getCreatedat());
            pstmt.setBoolean(4, c.isStatut());
            pstmt.setInt(5, c.getId());

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
        String req = "DELETE FROM facture WHERE id=?";
        try (PreparedStatement pstmt = cnx.prepareStatement(req)) {
            pstmt.setInt(1, id);

            boolean res = pstmt.execute();

            if (res) {
                System.out.println("Supprimer effectuée avec succès !");
            } else {
                System.out.println("Vérifier l' id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Facture> getAll() {
        List<Facture> factures = new ArrayList<>();
        String req = "SELECT * FROM facture";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                int totale = res.getInt("totale");
                int tva = res.getInt("tva");
                boolean statut = res.getBoolean("statut");
                Date createdat = res.getDate("createdat");
                int contrat = res.getInt("contrat");

                Facture c = new Facture(id, totale, tva, createdat, statut , contrat);
                factures.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;

    }

    @Override
    public Facture getById(int id) {
        String req = "SELECT * FROM facture WHERE id = ?";
        Facture Facture = null;

        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id); // Set the value of the id parameter
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idfacture = rs.getInt("id");
                int totale = rs.getInt("totale");
                int tva = rs.getInt("tva");
                boolean statut = rs.getBoolean("statut");
                Date createdat = rs.getDate("createdat");
                int contrat = rs.getInt("contrat");
                Facture c = new Facture(idfacture, totale, tva, createdat, statut ,contrat);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Facture;

    }

    @Override
    public Facture getLast() {
        String req = "SELECT * FROM facture ORDER BY id DESC LIMIT 1;";
        Facture Facture = null;

        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idfacture = rs.getInt("id");
                int totale = rs.getInt("totale");
                int tva = rs.getInt("tva");
                boolean statut = rs.getBoolean("statut");
                Date createdat = rs.getDate("createdat");
                int contrat = rs.getInt("contrat");
                Facture c = new Facture(idfacture, totale, tva, createdat, statut,contrat);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Facture;

    }

}