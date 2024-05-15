package mapper;

import DTO.CurrencyDTO;
import model.Currency;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CurrencyMapper {

    CurrencyMapper CURRENCY_MAPPER = Mappers.getMapper(CurrencyMapper.class);

    @Mapping(source = "fullName", target = "name")
    CurrencyDTO toDTO(Currency currency);

    @InheritInverseConfiguration
    CurrencyDTO fromDTO(Currency currency);



}
