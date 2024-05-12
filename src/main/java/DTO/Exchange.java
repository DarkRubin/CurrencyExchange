package DTO;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Exchange {
    private final ExchangeRateDTO exchangeRate;
    private final double amount;
    private final double convertedAmount;

    public double getRate() {
        return exchangeRate.getRate();
    }
}
