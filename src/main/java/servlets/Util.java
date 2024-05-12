package servlets;

import DTO.CurrencyPair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.Service.CodeInvalidException;
import exceptions.Service.CodePairInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Util {

    protected void printResponseInJSON(Object toPrint, HttpServletResponse response) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter out = response.getWriter();
        gson.toJson(toPrint);
        out.println(toPrint);
    }

    private String getUrlArguments(HttpServletRequest request) {
        return request.getPathInfo().replace("/", "");
    }

    protected CurrencyPair getCurrencyPairFromPatch(HttpServletRequest request) throws CodePairInvalidException {
        String codePair = getUrlArguments(request);
        String base = codePair.substring(0, 3);
        String target = codePair.substring(3);
        if (!codeIsValid(base) || !codeIsValid(target)) {
            throw new CodePairInvalidException();
        }
        return new CurrencyPair(base, target);
    }

    protected String getCodeFromPatch(HttpServletRequest request) throws CodeInvalidException {
        String code = getUrlArguments(request);
        if (!codeIsValid(code)) {
            throw new CodeInvalidException();
        }
        return code;
    }

    private boolean codeIsValid(String code) {
        return code != null && code.length() == 3;
    }
}
