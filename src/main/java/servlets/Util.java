package servlets;

import DTO.CurrencyPair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.Service.CodeInvalidException;
import exceptions.Service.CodePairInvalidException;
import exceptions.Service.NeedFieldEmptyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Util {

    protected void printResponseInJSON(Object toPrint, HttpServletResponse response) throws IOException {
        response.setContentType("text/JSON;charset=utf-8");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(toPrint));
    }

    private String getUrlArguments(HttpServletRequest request) {
        return request.getPathInfo().replace("/", "");
    }

    protected CurrencyPair getCurrencyPairFromPatch(HttpServletRequest request) throws CodePairInvalidException {
        String codePair = getUrlArguments(request);
        String base = codePair.substring(0, 3);
        String target = codePair.substring(3);
        if (codeIsValid(base) && codeIsValid(target)) {
            return new CurrencyPair(base, target);
        }
        throw new CodePairInvalidException();
    }

    protected String getCodeFromPatch(HttpServletRequest request) throws CodeInvalidException {
        String code = getUrlArguments(request);
        if (codeIsValid(code)) {
            return code;
        }
        throw new CodeInvalidException();
    }

    private boolean codeIsValid(String code) {
        return code != null && code.length() == 3;
    }

    public void isNotEmpty(List<String> fields) throws NeedFieldEmptyException {
        for (String string : fields) {
            if (string.isEmpty()) {
                throw new NeedFieldEmptyException();
            }
        }
    }
}
