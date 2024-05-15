package service;

import DAO.CurrencyTable;
import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyAlreadyExistExceptionDTO;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import model.Currency;


public class CurrenciesService {
    private final CurrencyTable table = new CurrencyTable();

    public Currency save(Currency currency) throws DbDontWorkExceptionDTO, CurrencyAlreadyExistExceptionDTO, CurrencyNotFoundExceptionDTO {
        try {
            return table.save(currency);
        } catch (DbObjectAlreadyAddedException e) {
            throw new CurrencyAlreadyExistExceptionDTO();
        }
    }

    public Currency find(String code) throws CurrencyNotFoundExceptionDTO, DbDontWorkExceptionDTO {
        try {
            return table.find(new Currency(code));
        } catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundExceptionDTO();
        }
    }


}
