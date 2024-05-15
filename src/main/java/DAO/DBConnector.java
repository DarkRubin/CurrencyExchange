package DAO;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DBConnector {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    private static final String URL = "jdbc:sqlite:E:/DEV/Projects/CurrencyExchange/src/main/resources/exchangeDB.sqlite";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        config.setJdbcUrl(URL);
        ds = new HikariDataSource(config);
    }

    Connection getConnection() throws SQLException {
        return ds.getConnection();
    }





}
