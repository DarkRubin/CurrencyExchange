package servlets;

import DTO.CurrencyPair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.Service.CodePairInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

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

    private void codePairIsValid(String codePair) throws CodePairInvalidException {
        if (codePair.length() != 6) {
            throw new CodePairInvalidException();
        }
    }

    protected void printResponseInJSON(Object toPrint, HttpServletResponse response) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter out = response.getWriter();
        gson.toJson(toPrint);
        out.println(toPrint);
    }
}
