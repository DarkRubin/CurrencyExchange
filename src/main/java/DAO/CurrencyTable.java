package DAO;

import model.Currency;

import java.sql.*;
import java.util.ArrayList;

public class CurrencyTable {

    private final DBConnector connector = new DBConnector();
    private Connection connection;

    public Currency addCurrency(Currency currency) throws SQLException {
        connection = connector.getConnection();
        String code = currency.getCode();
        String fullName = currency.getName();
        String sign = currency.getSign();
        PreparedStatement statement = connector.createPreparedStatement(connection,
                "INSERT INTO Currencies (Code, FullName, Sign) VALUES (?, ?, ?);");
        statement.setString(1, code);
        statement.setString(2, fullName);
        statement.setString(3, sign);
        statement.execute();
        connection.close();
        return readCurrency(currency);
    }

    public Currency readCurrency(Currency currency) throws SQLException {
        connection = connector.getConnection();
        ResultSet result;
        if (currency.getId() == null) {
             result = connector.createResultSet(connection,
                    "SELECT * FROM Currencies WHERE Code = '" + currency.getCode() + "';");
        } else {
            result = connector.createResultSet(connection,
                    "SELECT * FROM Currencies WHERE ID = '" + currency.getId() + "';");
        }
        connection.close();
        return resultSetToCurrency(result);
    }

    public ArrayList<Currency> readAllCurrencies() throws SQLException {
        connection = connector.getConnection();
        ArrayList<Currency> currencies = new ArrayList<>();
        ResultSet allCurrencies = connector.createResultSet(connection,
                "SELECT * FROM Currencies;");
        while (allCurrencies.next()) {
            currencies.add(resultSetToCurrency(allCurrencies));
        }
        connection.close();
        return currencies;
    }

    private Currency resultSetToCurrency(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String code = resultSet.getString("Code");
        String fullName = resultSet.getString("FullName");
        String sign = resultSet.getString("Sign");
        return new Currency(id, code, fullName, sign);
    }


}
