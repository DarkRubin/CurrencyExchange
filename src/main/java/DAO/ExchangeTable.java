package DAO;

import model.Currency;
import model.Exchange;
import model.ExchangeRate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExchangeTable {

    private final DBConnector connector = new DBConnector();
    private Connection connection;

    public ArrayList<ExchangeRate> readAll() throws SQLException {
        connection = connector.getConnection();
        ArrayList<ExchangeRate> exchanges = new ArrayList<>();
        ResultSet allExchangeRates = connector.createResultSet(connection,
                "SELECT * FROM ExchangeRates;");
        while (allExchangeRates.next()) {
            exchanges.add(resultSetToExchangeRate(allExchangeRates));
        }
        connection.close();
        return exchanges;
    }



    public ExchangeRate read(ExchangeRate exchangeRate) throws SQLException {
        connection = connector.getConnection();
        ResultSet result = connector.createResultSet(connection,
                "SELECT * FROM Currencies WHERE Code = '" + exchangeRate.getBase() + "';");
        connection.close();
        return resultSetToExchangeRate(result);
    }

    private ExchangeRate resultSetToExchangeRate(ResultSet result) throws SQLException {
        long id = result.getLong("ID");
        int baseCurrencyId = result.getInt("BaseCurrencyId");
        int targetCurrencyId = result.getInt("TargetCurrencyId");
        double rate = result.getDouble("Rate");
        CurrencyTable table = new CurrencyTable();
        Currency baseCurrency = table.readCurrency(new Currency(baseCurrencyId));
        Currency targetCurrency = table.readCurrency(new Currency(targetCurrencyId));
        return new ExchangeRate(id, baseCurrency, targetCurrency, rate);
    }


}
