package service;

import DTO.Exchange;
import DTO.ExchangeRateDTO;
import exceptions.Service.CurrencyNotFoundException;
import exceptions.Service.DbDontWorkException;
import exceptions.Service.ExchangeRateNotFoundException;
import model.Currency;

public class ExchangeService {


    private final ExchangeRatesService exchangeService = new ExchangeRatesService();

    public Exchange calculateExchange(String from, String to, double amount)
            throws CurrencyNotFoundException, DbDontWorkException, ExchangeRateNotFoundException {
        ExchangeRateDTO rate = exchangeService.codsToDTO(from, to, 0);
        try {
            ExchangeRateDTO toCalculate = exchangeService.findInTable(rate);
            return calculate(toCalculate, amount);
        } catch (CurrencyNotFoundException | ExchangeRateNotFoundException e) {
            rate.revers();
        } try {
            return calculateReversedExchange(rate, amount);
        } catch (DbDontWorkException | ExchangeRateNotFoundException | CurrencyNotFoundException e) {
            rate.revers();
            if (from.equals("USD") || to.equals("USD")) {
                throw new ExchangeRateNotFoundException();
            }
            return calculateByUSD(rate, amount);
        }

    }

    private Exchange calculateByUSD(ExchangeRateDTO toCalculate, double amount)
            throws DbDontWorkException, ExchangeRateNotFoundException, CurrencyNotFoundException {
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
        return new Exchange(toCalculate, amount, amount*rate);
    }

    private Exchange calculateReversedExchange(ExchangeRateDTO toCalculate, double amount)
            throws DbDontWorkException, ExchangeRateNotFoundException, CurrencyNotFoundException {
        ExchangeRateDTO rateDTO = exchangeService.findInTable(toCalculate);
        Double reversedRate = rateDTO.getRate();
        double trueRate = Math.pow(reversedRate, -1);
        toCalculate.revers();
        toCalculate.setRate(trueRate);
        return calculate(toCalculate, amount);
    }


}
