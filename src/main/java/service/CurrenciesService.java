package service;

import DAO.CurrencyTable;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import model.Currency;


public class CurrenciesService {
    private final CurrencyTable table = new CurrencyTable();

    public Currency saveToTable(Currency currency) throws DbDontWorkException {
        return table.save(currency);
    }

    public Currency findInTable(String code) throws CurrencyNotFoundException, DbDontWorkException {
        try {
            return table.find(new Currency(code));
        } catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundException();
        }
    }


}
