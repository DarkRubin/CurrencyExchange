package mapper;

import DTO.ExchangeDTO;
import model.Exchange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExchangeMapper {

    ExchangeMapper EXCHANGE_MAPPER = Mappers.getMapper(ExchangeMapper.class);

    @Mapping(target = "convertedAmount", expression = "java(exchange.roundToDouble())")
    ExchangeDTO toDTO(Exchange exchange);
}
