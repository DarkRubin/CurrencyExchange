package DAO;

import java.sql.Connection;

public interface DAO {
    DBConnector connector = new DBConnector();
    Connection connection = null;


    Class<?> readAll();

    Class<?> read();


}
