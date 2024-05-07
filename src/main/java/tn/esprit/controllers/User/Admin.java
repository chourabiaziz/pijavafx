package tn.esprit.controllers.User;


import tn.esprit.models.Role;
import tn.esprit.models.User;

public class Admin extends User {

    public Admin(String nom, String prenom, int number, String mail, String password,Role role) {
        super(nom, prenom, number, mail, password,role);
    }

    public Admin(int id, String nom, String prenom, int number, String mail, String password, Role role) {
        super(id,nom,prenom,number,mail,password,role);
    }



    public Admin() {

    }
}

