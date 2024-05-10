package DTO;

import model.Currency;

public class ExchangeRateDTO {
    private Long id;
    private Currency baseCurrency;
    private Currency targetCurrency;
    private Double rate;

    @Override
    public String toString() {
        return "ExchangeRateDTO{" +
                "id=" + id +
                ", baseCurrency=" + baseCurrency +
                ", targetCurrency=" + targetCurrency +
                ", rate=" + rate +
                '}';
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public Double getRate() {
        return rate;
    }

    public ExchangeRateDTO(Currency baseCurrency, Currency targetCurrency, Double rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public ExchangeRateDTO(Long id, Currency baseCurrency, Currency targetCurrency, Double rate) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void revers() {
        Currency oldBase = this.baseCurrency;
        this.baseCurrency = this.targetCurrency;
        this.targetCurrency = oldBase;
    }
}
