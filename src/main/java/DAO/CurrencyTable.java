package DAO;


import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CurrencyTable implements DAO<Currency> {

    private final DBConnector connector = new DBConnector();
    private Connection connection;

    @Override
    public Currency save(Currency currency) throws DbDontWorkException {
        try {
            connection = connector.getConnection();
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO Currencies (Code, FullName, Sign) VALUES (?, ?, ?);");
            statement.setString(1, currency.getCode());
            statement.setString(2, currency.getName());
            statement.setString(3, currency.getSign());
            statement.execute();
            connection.close();
            return find(currency);
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    @Override
    public Currency update(Currency currency) throws DbDontWorkException{
        try {
            connection = connector.getConnection();
            PreparedStatement statement = connection.
                    prepareStatement("UPDATE Currencies SET FullName = ?, Sign = ? WHERE Code = ?;");
            statement.setString(1, currency.getName());
            statement.setString(2, currency.getSign());
            statement.setString(3, currency.getCode());
            statement.execute();
            connection.close();
            return find(currency);
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    @Override
    public Currency find(Currency currency) throws DbDontWorkException, DbObjectNotFoundException {
        try (var connection = connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Currencies WHERE Code = ? ");
            statement.setString(1, currency.getCode());
            ResultSet resultSet = statement.executeQuery();
            Currency result = resultSetToCurrency(resultSet);
            connection.close();
            return result;
        } catch (DbObjectNotFoundException e) {
            throw new DbObjectNotFoundException(e);
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    public Currency findById(long id) throws DbDontWorkException, CurrencyNotFoundException {
        try {
            connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Currencies WHERE ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Currency result = resultSetToCurrency(resultSet);
            connection.close();
            return result;
        } catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundException();
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    @Override
    public List<Currency> findAll() throws DbDontWorkException {
        try {
            connection = connector.getConnection();
            ResultSet allCurrencies = connection.createStatement().executeQuery("SELECT * FROM Currencies;");
            List<Currency> currencies = new ArrayList<>();
            while (allCurrencies.next()) {
                currencies.add(resultSetToCurrency(allCurrencies));
            }
            connection.close();
            return currencies;
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    private Currency resultSetToCurrency(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt("ID");
        if (id == 0) {
            throw new DbObjectNotFoundException(new SQLException());
        }
        String code = resultSet.getString("Code");
        String fullName = resultSet.getString("FullName");
        String sign = resultSet.getString("Sign");
        return new Currency(id, code, fullName, sign);
    }
}
