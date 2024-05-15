package model;

import DTO.ExchangeRateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.MathContext;

@ToString
@AllArgsConstructor
@Getter
public class Exchange {
    private static final MathContext ROUND_TO_DECIMAL_PLACES = new MathContext(3);
    private final ExchangeRateDTO exchangeRate;
    private final double amount;
    private BigDecimal convertedAmount;

    public double getRate() {
        return exchangeRate.getRate();
    }

    public double roundToDouble() {

        return convertedAmount.doubleValue();
    }

}
