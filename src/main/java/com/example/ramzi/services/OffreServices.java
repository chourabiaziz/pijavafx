package com.example.ramzi.services;

import com.example.ramzi.models.Offre;
import com.example.ramzi.utils.mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreServices implements Iservice<Offre>{
    private Connection connection;

    public OffreServices() {
        connection = mydatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Offre offre) throws SQLException {
        String req = "INSERT INTO offre (titre, description, assurance, email_assurance, num_assurance) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement os = connection.prepareStatement(req);
        os.setString(1, offre.getTitre());
        os.setString(2, offre.getDescription());
        os.setString(3, offre.getAssurance());
        os.setString(4, offre.getEmail_assurance());
        os.setString(5, offre.getNum_assurance());
        os.executeUpdate();
        System.out.println("Offre ajoutée avec succès");
    }

    @Override
    public void modifier(Offre offre) throws SQLException {
        String req = "UPDATE offre SET titre = ?, description = ?, assurance = ?, email_assurance = ?, num_assurance = ? WHERE id = ?";
        PreparedStatement os = connection.prepareStatement(req);
        os.setString(1, offre.getTitre());
        os.setString(2, offre.getDescription());
        os.setString(3, offre.getAssurance());
        os.setString(4, offre.getEmail_assurance());
        os.setString(5, offre.getNum_assurance());
        os.setInt(6, offre.getId());
        os.executeUpdate();
        System.out.println("Offre modifiée avec succès");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM offre WHERE id = ?";
        PreparedStatement os = connection.prepareStatement(req);
        os.setInt(1, id);
        os.executeUpdate();
        System.out.println("Offre supprimée avec succès");
    }

    @Override
    public Offre getOneById(int id) throws SQLException {
        String req = "SELECT * FROM offre WHERE id = ?";
        PreparedStatement os = connection.prepareStatement(req);
        os.setInt(1, id);
        ResultSet rs = os.executeQuery();
        if (rs.next()) {
            Offre offre = new Offre();
            offre.setId(rs.getInt("id"));
            offre.setTitre(rs.getString("titre"));
            offre.setDescription(rs.getString("description"));
            offre.setAssurance(rs.getString("assurance"));
            offre.setEmail_assurance(rs.getString("email_assurance"));
            offre.setNum_assurance(rs.getString("num_assurance"));
            return offre;
        }
        return null;
    }

    @Override
    public List<Offre> getAll() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String req = "SELECT * FROM offre";
        PreparedStatement os = connection.prepareStatement(req);
        ResultSet rs = os.executeQuery();
        while (rs.next()) {
            Offre offre = new Offre();
            offre.setId(rs.getInt("id"));
            offre.setTitre(rs.getString("titre"));
            offre.setDescription(rs.getString("description"));
            offre.setAssurance(rs.getString("assurance"));
            offre.setEmail_assurance(rs.getString("email_assurance"));
            offre.setNum_assurance(rs.getString("num_assurance"));
            offres.add(offre);
        }
        return offres;
    }

    @Override
    public List<Offre> getByIdUser(int id) {
        // Cette méthode n'est pas implémentée dans votre code original
        return null;
    }
}
