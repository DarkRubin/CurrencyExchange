package DTO;


public class Exchange {
    private final ExchangeRateDTO exchangeRate;
    private final double amount;
    private final double convertedAmount;

    public Exchange(ExchangeRateDTO exchangeRate, double amount, double convertedAmount) {
        this.exchangeRate = exchangeRate;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "exchangeRate=" + exchangeRate +
                ", amount=" + amount +
                ", convertedAmount=" + convertedAmount +
                '}';
    }


    public double getRate() {
        return exchangeRate.getRate();
    }
}
