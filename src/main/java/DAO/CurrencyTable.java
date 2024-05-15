package DAO;


import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import model.Currency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CurrencyTable {

    private final DBConnector connector = new DBConnector();

    public Currency save(Currency currency) throws DbDontWorkExceptionDTO, DbObjectAlreadyAddedException {
        try (var connection = connector.getConnection()) {
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO Currencies (Code, FullName, Sign) VALUES (?, ?, ?);");
            statement.setString(1, currency.getCode());
            statement.setString(2, currency.getFullName());
            statement.setString(3, currency.getSign());
            statement.executeUpdate();
            return find(currency);
        } catch (SQLException e) {
            if (e.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                throw new DbObjectAlreadyAddedException();
            }
            throw new DbDontWorkExceptionDTO();
        }
    }

    public Currency find(Currency currency) throws DbDontWorkExceptionDTO, DbObjectNotFoundException {
        try (var connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Currencies WHERE Code = ? ");
            statement.setString(1, currency.getCode());
            ResultSet resultSet = statement.executeQuery();
            return resultSetToCurrency(resultSet);
        } catch (DbObjectNotFoundException e) {
            throw new DbObjectNotFoundException();
        } catch (SQLException e) {
            throw new DbDontWorkExceptionDTO();
        }
    }

    public Currency findById(long id) throws DbDontWorkExceptionDTO, CurrencyNotFoundExceptionDTO {
        try (var connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Currencies WHERE ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSetToCurrency(resultSet);
        } catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundExceptionDTO();
        } catch (SQLException e) {
            throw new DbDontWorkExceptionDTO();
        }
    }

    public List<Currency> findAll() throws DbDontWorkExceptionDTO {
        try (var connection = connector.getConnection()) {
            ResultSet allCurrencies = connection.createStatement().executeQuery("SELECT * FROM Currencies;");
            List<Currency> currencies = new ArrayList<>();
            while (allCurrencies.next()) {
                currencies.add(resultSetToCurrency(allCurrencies));
            }
            return currencies;
        } catch (SQLException e) {
            throw new DbDontWorkExceptionDTO();
        }
    }

    private Currency resultSetToCurrency(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt("ID");
        if (id == 0) {
            throw new DbObjectNotFoundException();
        }
        String code = resultSet.getString("Code");
        String fullName = resultSet.getString("FullName");
        String sign = resultSet.getString("Sign");
        return new Currency(id, code, fullName, sign);
    }
}
