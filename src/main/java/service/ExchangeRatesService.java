package service;

import DAO.CurrencyTable;
import DAO.ExchangeTable;
import DTO.ExchangeRateDTO;
import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import exceptions.Service.ExchangeRateAlreadyExistException;
import exceptions.Service.ExchangeRateNotFoundException;
import model.Currency;
import model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

import static mapper.ExchangeRateMapper.EXCHANGE_RATE_MAPPER;


public class ExchangeRatesService {

    private final ExchangeTable table = new ExchangeTable();
    CurrencyTable converter = new CurrencyTable();

    public ExchangeRateDTO save(ExchangeRateDTO dto)
            throws CurrencyNotFoundException, DbDontWorkException, ExchangeRateNotFoundException, ExchangeRateAlreadyExistException {
        try {
            ExchangeRate exchangeRate = fromDTO(dto);
            return toDTO(table.save(exchangeRate));
        }catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundException();
        } catch (DbObjectAlreadyAddedException e) {
            throw new ExchangeRateAlreadyExistException();
        }
    }

    public ExchangeRateDTO find(ExchangeRateDTO exchangeRate) throws DbDontWorkException, ExchangeRateNotFoundException, CurrencyNotFoundException {
        try {
            ExchangeRate rate = table.find(fromDTO(exchangeRate));
            return toDTO(rate);
        } catch (DbObjectNotFoundException e) {
            throw new ExchangeRateNotFoundException();
        }
    }
    public ExchangeRateDTO update(ExchangeRateDTO exchangeRate) throws DbDontWorkException, CurrencyNotFoundException, ExchangeRateNotFoundException {
        try {
            ExchangeRate rate = fromDTO(exchangeRate);
            return toDTO(table.update(rate));
        } catch (DbObjectNotFoundException e) {
            throw new ExchangeRateNotFoundException();
        }
    }

    public ExchangeRateDTO codsToDTO(String baseCurrencyCode, String targetCurrencyCode, double rate) {
         return new ExchangeRateDTO(new Currency(baseCurrencyCode), new Currency(targetCurrencyCode), rate);
    }

    public List<ExchangeRateDTO> find() throws CurrencyNotFoundException, DbDontWorkException {
        List<ExchangeRateDTO> dtoList = new ArrayList<>();
        List<ExchangeRate> list = table.findAll();
        for (ExchangeRate rate : list) {
            dtoList.add(toDTO(rate));
        }
        return dtoList;
    }

    private ExchangeRateDTO toDTO(ExchangeRate rate) throws DbDontWorkException, CurrencyNotFoundException {
        return EXCHANGE_RATE_MAPPER.toDTO(table.update(rate), converter);
    }

    private ExchangeRate fromDTO(ExchangeRateDTO rateDTO) throws DbDontWorkException {
        return EXCHANGE_RATE_MAPPER.fromDTO(rateDTO, converter);
    }
}
