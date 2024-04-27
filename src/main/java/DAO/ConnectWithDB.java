package DAO;


import java.sql.*;

public class ConnectWithDB {

    private static final String URL = "jdbc:sqlite:exchangeDB.sqlite";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    Statement createStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.createStatement();
    }

    PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    ResultSet createResultSet(String sqlCommand) throws SQLException {
        return createStatement().executeQuery(sqlCommand);
    }

}
