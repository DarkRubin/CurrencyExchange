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

public class ExchangeRatesService {

    private final ExchangeTable table = new ExchangeTable();
    private final CurrencyTable converter = new CurrencyTable();

    public ExchangeRateDTO saveToTable(ExchangeRateDTO dto)
            throws CurrencyNotFoundException, DbDontWorkException, ExchangeRateNotFoundException, ExchangeRateAlreadyExistException {
        try {
            ExchangeRate exchangeRate = convertDTOtoModel(dto);
            return convertModelToDTO(table.save(exchangeRate));
        }catch (DbObjectNotFoundException e) {
            throw new CurrencyNotFoundException();
        } catch (DbObjectAlreadyAddedException e) {
            throw new ExchangeRateAlreadyExistException(findInTable(dto));
        }
    }

    public ExchangeRateDTO findInTable(ExchangeRateDTO exchangeRate) throws DbDontWorkException, ExchangeRateNotFoundException, CurrencyNotFoundException {
        try {
            ExchangeRate rate = table.find(convertDTOtoModel(exchangeRate));
            return convertModelToDTO(rate);
        } catch (DbObjectNotFoundException e) {
            throw new ExchangeRateNotFoundException();
        }
    }
    public ExchangeRateDTO updateInTable(ExchangeRateDTO exchangeRate) throws DbDontWorkException, CurrencyNotFoundException, ExchangeRateNotFoundException {
        try {
            ExchangeRate rate = convertDTOtoModel(exchangeRate);
            return convertModelToDTO(table.update(rate));
        } catch (DbObjectNotFoundException e) {
            throw new ExchangeRateNotFoundException();
        }
    }

    public ExchangeRateDTO codsToDTO(String baseCurrencyCode, String targetCurrencyCode, double rate) {
         return new ExchangeRateDTO(new Currency(baseCurrencyCode), new Currency(targetCurrencyCode), rate);
    }

    public List<ExchangeRateDTO> findAllInTable() throws CurrencyNotFoundException, DbDontWorkException {
        List<ExchangeRateDTO> dtoList = new ArrayList<>();
        List<ExchangeRate> list = table.findAll();
        for (ExchangeRate rate : list) {
            dtoList.add(convertModelToDTO(rate));
        }
        return dtoList;

    }

    private ExchangeRate convertDTOtoModel(ExchangeRateDTO dto) throws DbDontWorkException, DbObjectNotFoundException {
        Currency base = converter.find(dto.getBaseCurrency());
        Long baseId = base.getId();
        Currency target = converter.find(dto.getTargetCurrency());
        Long targetId = target.getId();
        return new ExchangeRate(null, baseId, targetId, dto.getRate());
    }


    private ExchangeRateDTO convertModelToDTO(ExchangeRate exchangeRate) throws DbDontWorkException, CurrencyNotFoundException {
        Long id = exchangeRate.getId();
        Currency base = converter.findById(exchangeRate.getBase());
        Currency target = converter.findById(exchangeRate.getTarget());
        return new ExchangeRateDTO(id, base, target, exchangeRate.getRate());
    }
}
