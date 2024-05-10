package servlets;

import exceptions.Service.CodePairInvalidException;
import jakarta.servlet.http.HttpServletRequest;

public interface AddressReader {

    static CurrencyPair getCurrencyPair(HttpServletRequest request) throws CodePairInvalidException {
        String[] pathParts = request.getPathInfo().split("/");
        String codePair = "";
        for (String string : pathParts) {
            codePair = string;
        }
        codePairIsValid(codePair);
        String base = codePair.substring(0, 3);
        String target = codePair.substring(3);
        return new CurrencyPair(base, target);
    }

    static void codePairIsValid(String codePair) throws CodePairInvalidException {
        if (codePair.length() != 6) {
            throw new CodePairInvalidException();
        }
    }

    class CurrencyPair {
        public final String base;
        public final String target;

        public CurrencyPair(String base, String target) {
            this.base = base;
            this.target = target;
        }
    }
}
