package DAO;


import exceptions.DB.DbConnectException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {

    private static final String URL = "jdbc:sqlite:E:/DEV/Projects/CurrencyExchange/src/main/resources/exchangeDB.sqlite";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Connection getConnection() throws DbConnectException {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new DbConnectException();
        }
    }



}
