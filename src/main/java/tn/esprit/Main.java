package tn.esprit;


import tn.esprit.models.Assurance;
import tn.esprit.models.Constat;
import tn.esprit.services.ServiceAssurance;
import tn.esprit.services.ServiceConstat;

public class Main {
    public static void main(String[] args) {
        Assurance a = new Assurance(4,"Gprod","tunis lac 1","2011","7321544","grod@gmail.com");
        Constat c = new Constat(17,"mariem" ,"bannour","amal.selmi@esprit.com","fiat","1254","assure") ;


        ServiceAssurance sa = new ServiceAssurance();
        ServiceConstat sc = new ServiceConstat();
        sa.add(a);
        sc.add(c);



        System.out.println(sa.getAll());
        System.out.println(sc.getAll());
    }
}