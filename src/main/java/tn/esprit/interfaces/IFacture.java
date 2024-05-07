package tn.esprit.interfaces;

import tn.esprit.models.Facture;

import java.util.List;


public interface IFacture<T> {
    public void add(Facture c);

    public void edit(Facture c);

    public void delete(int id);

    public Facture getById(int id);

    public Facture getLast();

    public List<T> getAll();

}
