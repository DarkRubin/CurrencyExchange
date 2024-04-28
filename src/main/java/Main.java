import DAO.CurrencyTable;
import model.Currency;


import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CurrencyTable table = new CurrencyTable();
        try {
            ArrayList<Currency> list = table.readAllCurrencies();
            for (Currency currency : list) {
                System.out.println(currency);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Currency addedCurrency = null;
        try {
            addedCurrency = table.addCurrency(new Currency(null, "EUR", "Euro", "€"));
        } catch (SQLException e) {
            System.out.println(addedCurrency);
        }
        try {
            Currency currency = table.readCurrency(new Currency(null, "USD", null, null));
            System.out.println(currency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
