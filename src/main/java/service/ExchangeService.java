package service;

import DTO.ExceptionDTO;
import DTO.ExchangeRateDTO;
import exceptions.Service.CurrencyNotFoundExceptionDTO;
import exceptions.Service.DbDontWorkExceptionDTO;
import exceptions.Service.ExchangeRateNotFoundExceptionDTO;
import model.Currency;
import model.Exchange;

import java.math.BigDecimal;
import java.util.Optional;

public class ExchangeService {


    private final ExchangeRatesService exchangeService = new ExchangeRatesService();

    public Exchange calculateExchange(String from, String to, double amount) throws CurrencyNotFoundExceptionDTO, DbDontWorkExceptionDTO, ExchangeRateNotFoundExceptionDTO {
        ExchangeRateDTO toCalculate = exchangeService.codsToDTO(from, to, 0);
        Optional<Exchange> exchange = calculateDefault(toCalculate, amount);
        if (exchange.isEmpty()) {
            exchange = calculateReversedExchange(toCalculate, amount);
        }
        if (exchange.isEmpty()) {
            if (from.equals("USD") || to.equals("USD")) {
                throw new ExchangeRateNotFoundExceptionDTO();
            } else {
                return (calculateByUSD(toCalculate, amount));
            }
        }
        return exchange.get();

    }

    private Optional<Exchange> calculateDefault(ExchangeRateDTO toCalculate, double amount) {
        try {
            return Optional.of(calculate(exchangeService.find(toCalculate), amount));
        } catch (ExceptionDTO ignored) {
            return Optional.empty();
        }
    }

    private Exchange calculateByUSD(ExchangeRateDTO toCalculate, double amount) {
        Currency baseCurrency = toCalculate.getBaseCurrency();
        Currency targetCurrency = toCalculate.getTargetCurrency();
        Exchange baseToUSD = calculateExchange("USD", baseCurrency.getCode(), amount);
        Exchange targetToUSD = calculateExchange("USD", targetCurrency.getCode(), amount);
        double rate = baseToUSD.getRate() * targetToUSD.getRate();
        toCalculate.setRate(rate);
        return calculate(toCalculate, amount);
    }

    private Exchange calculate(ExchangeRateDTO toCalculate, double amount) {
        Double rate = toCalculate.getRate();
        return new Exchange(toCalculate, amount, new BigDecimal(amount*rate));
    }

    private Optional<Exchange> calculateReversedExchange(ExchangeRateDTO toCalculate, double amount) {
        toCalculate.revers();
        try {
            ExchangeRateDTO rateDTO = exchangeService.find(toCalculate);
            Double reversedRate = rateDTO.getRate();
            double trueRate = Math.pow(reversedRate, -1);
            toCalculate.revers();
            toCalculate.setRate(trueRate);
            return Optional.of(calculate(toCalculate, amount));
        } catch (ExceptionDTO ignore) {
            return Optional.empty();
        }
    }
}
