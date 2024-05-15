package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeDTO {
    private ExchangeRateDTO exchangeRate;
    private double amount;
    private double convertedAmount;

}
