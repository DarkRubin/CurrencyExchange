package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Currency;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ExchangeRateDTO {

    private Long id;
    private Currency baseCurrency;
    private Currency targetCurrency;
    private Double rate;


    public ExchangeRateDTO(Currency baseCurrency, Currency targetCurrency, Double rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public void revers() {
        Currency oldBase = this.baseCurrency;
        this.baseCurrency = this.targetCurrency;
        this.targetCurrency = oldBase;
    }
}
