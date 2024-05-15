package model;

import DTO.ExchangeRateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@ToString
@AllArgsConstructor
@Getter
public class Exchange {
    private static final MathContext ROUND_TO_DECIMAL_PLACES = new MathContext(2);
    private final ExchangeRateDTO exchangeRate;
    private final double amount;
    private BigDecimal convertedAmount;

    public double getRate() {
        return exchangeRate.getRate();
    }

    public double roundToDouble() {
        BigDecimal decimal = convertedAmount.setScale(3, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }

}
