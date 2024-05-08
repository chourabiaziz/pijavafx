package com.example.ramzi.services;

import com.example.ramzi.models.Dvis;
import com.example.ramzi.utils.mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DvisServices implements Iservice<Dvis>{
    private Connection connection;

    public DvisServices() {
        connection = mydatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Dvis dvis) throws SQLException {
        String req = "INSERT INTO devis (nom, prenom, civilite, telephone, email, voiture, matricule, puissance, cylindre, description, statut, couverture, offre_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement os = connection.prepareStatement(req);
        os.setString(1, dvis.getNom());
        os.setString(2, dvis.getPrenom());
        os.setString(3, dvis.getCivilite());
        os.setInt(4, dvis.getTelephone());
        os.setString(5, dvis.getEmail());
        os.setString(6, dvis.getVoiture());
        os.setString(7, dvis.getMatricule());
        os.setInt(8, dvis.getPuissance());
        os.setInt(9, dvis.getCylindre());
        os.setString(10, dvis.getDescription());
        os.setString(11, dvis.getStatut());
        os.setString(12, dvis.getCouverture());
        os.setInt(13, dvis.getOffre_id());
        os.executeUpdate();
        System.out.println("Dvis ajouté avec succès");
    }

    @Override
    public void modifier(Dvis dvis) throws SQLException {
        String req = "UPDATE devis SET nom = ?, prenom = ?, civilite = ?, telephone = ?, email = ?, voiture = ?, matricule = ?, puissance = ?, cylindre = ?, description = ?, statut = ?, couverture = ?, offre_id = ? WHERE id = ?";
        PreparedStatement os = connection.prepareStatement(req);
        os.setString(1, dvis.getNom());
        os.setString(2, dvis.getPrenom());
        os.setString(3, dvis.getCivilite());
        os.setInt(4, dvis.getTelephone());
        os.setString(5, dvis.getEmail());
        os.setString(6, dvis.getVoiture());
        os.setString(7, dvis.getMatricule());
        os.setInt(8, dvis.getPuissance());
        os.setInt(9, dvis.getCylindre());
        os.setString(10, dvis.getDescription());
        os.setString(11, dvis.getStatut());
        os.setString(12, dvis.getCouverture());
        os.setInt(13, dvis.getOffre_id());
        os.setInt(14, dvis.getId());
        os.executeUpdate();
        System.out.println("Dvis modifié avec succès");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM devis WHERE id = ?";
        PreparedStatement os = connection.prepareStatement(req);
        os.setInt(1, id);
        os.executeUpdate();
        System.out.println("Dvis supprimé avec succès");
    }

    @Override
    public Dvis getOneById(int id) throws SQLException {
        String req = "SELECT * FROM devis WHERE id = ?";
        PreparedStatement os = connection.prepareStatement(req);
        os.setInt(1, id);
        ResultSet rs = os.executeQuery();
        if (rs.next()) {
            Dvis dvis = new Dvis();
            dvis.setId(rs.getInt("id"));
            dvis.setNom(rs.getString("nom"));
            dvis.setPrenom(rs.getString("prenom"));
            dvis.setCivilite(rs.getString("civilite"));
            dvis.setTelephone(rs.getInt("telephone"));
            dvis.setEmail(rs.getString("email"));
            dvis.setVoiture(rs.getString("voiture"));
            dvis.setMatricule(rs.getString("matricule"));
            dvis.setPuissance(rs.getInt("puissance"));
            dvis.setCylindre(rs.getInt("cylindre"));
            dvis.setDescription(rs.getString("description"));
            dvis.setStatut(rs.getString("statut"));
            dvis.setCouverture(rs.getString("couverture"));
            dvis.setOffre_id(rs.getInt("offre_id"));
            return dvis;
        }
        return null;
    }

    @Override
    public List<Dvis> getAll() throws SQLException {
        List<Dvis> dvisList = new ArrayList<>();
        String req = "SELECT * FROM devis";
        PreparedStatement os = connection.prepareStatement(req);
        ResultSet rs = os.executeQuery();
        while (rs.next()) {
            Dvis dvis = new Dvis();
            dvis.setId(rs.getInt("id"));
            dvis.setNom(rs.getString("nom"));
            dvis.setPrenom(rs.getString("prenom"));
            dvis.setCivilite(rs.getString("civilite"));
            dvis.setTelephone(rs.getInt("telephone"));
            dvis.setEmail(rs.getString("email"));
            dvis.setVoiture(rs.getString("voiture"));
            dvis.setMatricule(rs.getString("matricule"));
            dvis.setPuissance(rs.getInt("puissance"));
            dvis.setCylindre(rs.getInt("cylindre"));
            dvis.setDescription(rs.getString("description"));
            dvis.setStatut(rs.getString("statut"));
            dvis.setCouverture(rs.getString("couverture"));
            dvis.setOffre_id(rs.getInt("offre_id"));
            dvisList.add(dvis);
        }
        return dvisList;
    }

    @Override
    public List<Dvis> getByIdUser(int id) throws SQLException {
        // Cette méthode n'est pas implémentée dans votre code original
        return null;
    }
}
