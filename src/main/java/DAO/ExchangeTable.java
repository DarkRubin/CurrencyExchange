package DAO;

import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.DbDontWorkException;
import model.ExchangeRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeTable implements DAO<ExchangeRate> {

    private final DBConnector connector = new DBConnector();
    private Connection connection;

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) throws DbDontWorkException, DbObjectAlreadyAddedException {
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate) VALUES (?, ?, ?);");
            preparedStatement.setLong(1, exchangeRate.getBase());
            preparedStatement.setLong(2, exchangeRate.getTarget());
            preparedStatement.setDouble(3, exchangeRate.getRate());
            preparedStatement.execute();
            connection.close();
            exchangeRate = find(exchangeRate);
            return exchangeRate;
        } catch (SQLException e) {
            if (e.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                throw new DbObjectAlreadyAddedException();
            }
            throw new DbDontWorkException();
        }

    }

    @Override
    public ExchangeRate update(ExchangeRate exchangeRate) throws DbDontWorkException {
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE ExchangeRates SET Rate = ? WHERE BaseCurrencyId = ? AND TargetCurrencyId = ?");
            preparedStatement.setDouble(1, exchangeRate.getRate());
            preparedStatement.setLong(2, exchangeRate.getBase());
            preparedStatement.setDouble(3, exchangeRate.getTarget());
            preparedStatement.execute();
            connection.close();
            return find(exchangeRate);
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    @Override
    public List<ExchangeRate> findAll() throws DbDontWorkException {
        List<ExchangeRate> exchanges = new ArrayList<>();
        try {
            connection = connector.getConnection();
            ResultSet allExchangeRates = connection.createStatement().executeQuery("SELECT * FROM ExchangeRates;");
            while (allExchangeRates.next()) {
                exchanges.add(resultSetToExchangeRate(allExchangeRates));
            }
            connection.close();
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
        return exchanges;
    }

    @Override
    public ExchangeRate find(ExchangeRate exchangeRate) throws DbObjectNotFoundException, DbDontWorkException {
        try {
            connection = connector.getConnection();
            PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM ExchangeRates WHERE BaseCurrencyId = ? AND TargetCurrencyId = ?");
            statement.setLong(1, exchangeRate.getBase());
            statement.setLong(2, exchangeRate.getTarget());
            ResultSet resultSet = statement.executeQuery();
            ExchangeRate result = resultSetToExchangeRate(resultSet);
            connection.close();
            return result;
        } catch (DbObjectNotFoundException e) {
            throw new DbObjectNotFoundException();
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }

    private ExchangeRate resultSetToExchangeRate(ResultSet result) throws DbObjectNotFoundException, DbDontWorkException {
        try {
            long id = result.getInt("ID");
            if (id == 0) {
                throw new DbObjectNotFoundException();
            }
            long baseCurrencyId = result.getInt("BaseCurrencyId");
            long targetCurrencyId = result.getInt("TargetCurrencyId");
            double rate = result.getDouble("Rate");
            return new ExchangeRate(id, baseCurrencyId, targetCurrencyId, rate);
        } catch (DbObjectNotFoundException e) {
          throw new DbObjectNotFoundException();
        } catch (SQLException e) {
            throw new DbDontWorkException();
        }
    }
}
