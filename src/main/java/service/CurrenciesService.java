package service;

import DAO.CurrencyTable;
import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyAlreadyExistException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import model.Currency;


public class CurrenciesService {
    private final CurrencyTable table = new CurrencyTable();

    public Currency saveToTable(Currency currency) throws DbDontWorkException, CurrencyAlreadyExistException, CurrencyNotFoundException {
        try {
            return table.save(currency);
        } catch (DbObjectAlreadyAddedException e) {
            throw new CurrencyAlreadyExistException();
        }
    }

    public Currency findInTable(String code) throws CurrencyNotFoundException, DbDontWorkException {
        try {
            return table.find(new Currency(code));
        } catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundException();
        }
    }


}
