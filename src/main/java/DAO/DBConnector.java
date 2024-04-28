package DAO;


import java.sql.*;

public class DBConnector {

    private static final String URL = "jdbc:sqlite:exchangeDB.sqlite";

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    PreparedStatement createPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    ResultSet createResultSet(Connection connection, String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

}
