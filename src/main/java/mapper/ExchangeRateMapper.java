package mapper;

import DAO.CurrencyTable;
import DTO.ExchangeRateDTO;
import exceptions.DB.DbObjectNotFoundException;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import model.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ExchangeRateMapper {

    ExchangeRateMapper EXCHANGE_RATE_MAPPER = Mappers.getMapper(ExchangeRateMapper.class);

    @Mapping(target = "baseCurrency", expression = "java(converter.findById(exchangeRate.base()))")
    @Mapping(target = "targetCurrency", expression = "java(converter.findById(exchangeRate.target()))")
    ExchangeRateDTO toDTO(ExchangeRate exchangeRate, CurrencyTable converter) throws DbDontWorkException, CurrencyNotFoundException;

    @Mapping(target = "base", expression = "java(converter.find(dto.getBaseCurrency()).getId())")
    @Mapping(target = "target", expression = "java(converter.find(dto.getTargetCurrency()).getId())")
    ExchangeRate fromDTO(ExchangeRateDTO dto, CurrencyTable converter) throws DbDontWorkException, DbObjectNotFoundException;
}
