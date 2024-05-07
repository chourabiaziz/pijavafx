package tn.esprit.interfaces;

import java.util.List;

import tn.esprit.models.Contrat;

public interface IContrat<T> {
    public boolean add(Contrat c);

    public void edit(Contrat c);

    public void delete(int id);

    public Contrat getById(int id);

    public List<T> getAll();

}
