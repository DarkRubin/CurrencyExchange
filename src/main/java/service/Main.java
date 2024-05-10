package service;

import DAO.CurrencyTable;
import DTO.ExchangeRateDTO;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import exceptions.Service.ExchangeRateNotFoundException;
import model.Currency;

import java.util.List;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) throws DbDontWorkException, CurrencyNotFoundException, ExchangeRateNotFoundException {
        exchangeTableTest();
        currencyTableTest();
    }

    private static void exchangeTableTest() throws DbDontWorkException, CurrencyNotFoundException, ExchangeRateNotFoundException {
        ExchangeRatesService service = new ExchangeRatesService();
        List<ExchangeRateDTO> list = service.findAllInTable();
        for (ExchangeRateDTO exchangeRate  : list) {
            out.println(exchangeRate);
        }
        ExchangeRateDTO addedExchange = service.saveToTable(new ExchangeRateDTO(new Currency("AUD"), new Currency("USD"), 0.66));
        out.println(addedExchange);
        ExchangeRateDTO exchangeRate = service.findInTable
                (new ExchangeRateDTO(new Currency("AUD"), new Currency("USD"), null));
        out.println(exchangeRate);
    }

    private static void currencyTableTest() throws DbDontWorkException, CurrencyNotFoundException {
        CurrencyTable table = new CurrencyTable();
        CurrenciesService service = new CurrenciesService();
        List<Currency> list = table.findAll();
        for (Currency currency : list) {
            out.println(currency);
        }
        Currency addedCurrency;
        addedCurrency = service.saveToTable(new Currency("EUR", "Euro", "€"));
        out.println(addedCurrency);
        Currency currency = service.findInTable("USD");
        out.println(currency);
    }
}
