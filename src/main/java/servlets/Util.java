package servlets;

import DTO.CurrencyPair;
import exceptions.Service.CodePairInvalidException;
import jakarta.servlet.http.HttpServletRequest;

public class Util {

    protected CurrencyPair getCurrencyPairFromUrl(HttpServletRequest request) throws CodePairInvalidException {
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

    public void codePairIsValid(String codePair) throws CodePairInvalidException {
        if (codePair.length() != 6) {
            throw new CodePairInvalidException();
        }
    }
}
