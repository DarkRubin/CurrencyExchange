package DAO;

import model.Currency;

import java.sql.*;
import java.util.ArrayList;

public class CurrencyTable {

    private final ConnectWithDB connector = new ConnectWithDB();

    public Currency addCurrency(Currency currency) throws SQLException {
        PreparedStatement preparedStatement = connector.
                createPreparedStatement("INSERT INTO Currencies (Code, FullName, Sign) VALUES (?, ?, ?);");
        preparedStatement.setString(1, currency.getCode());
        preparedStatement.setString(2, currency.getName());
        preparedStatement.setString(3, currency.getSign());
        preparedStatement.execute();



        return currency;
    }

    /*public Currency readCurrency() throws SQLException {

        ResultSet result = connect.createResultSet("SELECT * FROM Currencies WHERE Code = ?;");

        return;
    }*/

    public ArrayList<Currency> readAllCurrencies() throws SQLException {
        ArrayList<Currency> currencies = new ArrayList<>();
        ResultSet allCurrencies = connector.createResultSet("SELECT * FROM Currencies;");
        while (allCurrencies.next()) {
            int id = allCurrencies.getInt("ID");
            String code = allCurrencies.getString("Code");
            String fullName = allCurrencies.getString("FullName");
            String sign = allCurrencies.getString("Sign");

            currencies.add(new Currency(id, code, fullName, sign));

        }

        return currencies;
    }

}
