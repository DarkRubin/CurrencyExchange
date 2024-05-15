package service;

import DAO.CurrencyTable;
import DAO.ExchangeTable;
import DTO.ExchangeRateDTO;
import exceptions.DB.DbObjectAlreadyAddedException;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import exceptions.Service.ExchangeRateAlreadyExistExceptionDTO;
import exceptions.Service.ExchangeRateNotFoundExceptionDTO;
import model.Currency;
import model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

import static mapper.ExchangeRateMapper.EXCHANGE_RATE_MAPPER;


public class ExchangeRatesService {

    private final ExchangeTable table = new ExchangeTable();
    CurrencyTable converter = new CurrencyTable();

    public ExchangeRateDTO save(ExchangeRateDTO dto)
            throws CurrencyNotFoundExceptionDTO, DbDontWorkExceptionDTO, ExchangeRateNotFoundExceptionDTO, ExchangeRateAlreadyExistExceptionDTO {
        try {
            ExchangeRate exchangeRate = fromDTO(dto);
            return toDTO(table.save(exchangeRate));
        }catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundExceptionDTO();
        } catch (DbObjectAlreadyAddedException e) {
            throw new ExchangeRateAlreadyExistExceptionDTO();
        }
    }

    public ExchangeRateDTO find(ExchangeRateDTO exchangeRate) throws DbDontWorkExceptionDTO, ExchangeRateNotFoundExceptionDTO, CurrencyNotFoundExceptionDTO {
        try {
            ExchangeRate rate = table.find(fromDTO(exchangeRate));
            return toDTO(rate);
        } catch (DbObjectNotFoundException e) {
            throw new ExchangeRateNotFoundExceptionDTO();
        }
    }
    public ExchangeRateDTO update(ExchangeRateDTO exchangeRate) throws DbDontWorkExceptionDTO, CurrencyNotFoundExceptionDTO, ExchangeRateNotFoundExceptionDTO {
        try {
            ExchangeRate rate = fromDTO(exchangeRate);
            return toDTO(table.update(rate));
        } catch (DbObjectNotFoundException e) {
            throw new ExchangeRateNotFoundExceptionDTO();
        }
    }

    public ExchangeRateDTO codsToDTO(String baseCurrencyCode, String targetCurrencyCode, double rate) {
         return new ExchangeRateDTO(new Currency(baseCurrencyCode), new Currency(targetCurrencyCode), rate);
    }

    public List<ExchangeRateDTO> find() throws CurrencyNotFoundExceptionDTO, DbDontWorkExceptionDTO {
        List<ExchangeRateDTO> dtoList = new ArrayList<>();
        List<ExchangeRate> list = table.findAll();
        for (ExchangeRate rate : list) {
            dtoList.add(toDTO(rate));
        }
        return dtoList;
    }

    private ExchangeRateDTO toDTO(ExchangeRate rate) throws DbDontWorkExceptionDTO, CurrencyNotFoundExceptionDTO {
        return EXCHANGE_RATE_MAPPER.toDTO(table.update(rate), converter);
    }

    private ExchangeRate fromDTO(ExchangeRateDTO rateDTO) throws DbDontWorkExceptionDTO {
        return EXCHANGE_RATE_MAPPER.fromDTO(rateDTO, converter);
    }
}
