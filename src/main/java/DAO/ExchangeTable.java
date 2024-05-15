package DAO;

import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.DbDontWorkExceptionDTO;
import model.ExchangeRate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeTable {

    private final DBConnector connector = new DBConnector();

    public ExchangeRate save(ExchangeRate exchangeRate) throws DbDontWorkExceptionDTO, DbObjectAlreadyAddedException {
        try (var connection = connector.getConnection()) {
            PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate) VALUES (?, ?, ?);");
            preparedStatement.setLong(1, exchangeRate.base());
            preparedStatement.setLong(2, exchangeRate.target());
            preparedStatement.setDouble(3, exchangeRate.rate());
            preparedStatement.execute();
            exchangeRate = find(exchangeRate);
            return exchangeRate;
        } catch (SQLException e) {
            if (e.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                throw new DbObjectAlreadyAddedException();
            }
            throw new DbDontWorkExceptionDTO();
        }

    }

    public ExchangeRate update(ExchangeRate exchangeRate) throws DbDontWorkExceptionDTO {
        try (var connection = connector.getConnection()) {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE ExchangeRates SET Rate = ? WHERE BaseCurrencyId = ? AND TargetCurrencyId = ?");
            preparedStatement.setDouble(1, exchangeRate.rate());
            preparedStatement.setLong(2, exchangeRate.base());
            preparedStatement.setDouble(3, exchangeRate.target());
            preparedStatement.execute();
            connection.close();
            return find(exchangeRate);
        } catch (SQLException e) {
            throw new DbDontWorkExceptionDTO();
        }
    }

    public List<ExchangeRate> findAll() throws DbDontWorkExceptionDTO {
        List<ExchangeRate> exchanges = new ArrayList<>();
        try (var connection = connector.getConnection()) {
            ResultSet allExchangeRates = connection.createStatement().executeQuery("SELECT * FROM ExchangeRates;");
            while (allExchangeRates.next()) {
                exchanges.add(resultSetToExchangeRate(allExchangeRates));
            }
        } catch (SQLException e) {
            throw new DbDontWorkExceptionDTO();
        }
        return exchanges;
    }

    public ExchangeRate find(ExchangeRate exchangeRate) throws DbObjectNotFoundException, DbDontWorkExceptionDTO {
        try (var connection = connector.getConnection()) {
            PreparedStatement statement = connection.
                prepareStatement("SELECT * FROM ExchangeRates WHERE BaseCurrencyId = ? AND TargetCurrencyId = ?");
            statement.setLong(1, exchangeRate.base());
            statement.setLong(2, exchangeRate.target());
            ResultSet resultSet = statement.executeQuery();
            ExchangeRate result = resultSetToExchangeRate(resultSet);
            connection.close();
            return result;
        } catch (DbObjectNotFoundException e) {
            throw new DbObjectNotFoundException();
        } catch (SQLException e) {
            throw new DbDontWorkExceptionDTO();
        }
    }

    private ExchangeRate resultSetToExchangeRate(ResultSet result) throws DbObjectNotFoundException, DbDontWorkExceptionDTO {
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
            throw new DbDontWorkExceptionDTO();
        }
    }
}
