package tn.esprit.interfaces;


import java.sql.SQLException;
import java.util.List;

public interface IServiceA<T>{
        public void add(T p) throws SQLException;
        public void update(T p) throws SQLException;



}

