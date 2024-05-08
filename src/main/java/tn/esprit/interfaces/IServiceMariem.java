package tn.esprit.interfaces;

import java.util.ArrayList;

public interface IServiceMariem<T> {

        void add (T t );
        ArrayList<T> getAll();

        boolean update(T t );
        boolean delete (T t);
//findby..

    //getby ...

}
