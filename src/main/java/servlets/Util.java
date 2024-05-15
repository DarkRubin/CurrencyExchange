package servlets;

import DTO.CurrencyPair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.Service.CodeInvalidExceptionDTO;
import exceptions.Service.CodePairInvalidExceptionDTO;
import exceptions.Service.NeedFieldEmptyExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Util {

    protected void printResponseInJSON(Object toPrint, HttpServletResponse response) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(toPrint));
    }

    private String getUrlArguments(HttpServletRequest request) {
        return request.getPathInfo().replace("/", "");
    }

    protected CurrencyPair getCurrencyPairFromPatch(HttpServletRequest request) throws CodePairInvalidExceptionDTO {
        String codePair = getUrlArguments(request);
        String base = codePair.substring(0, 3);
        String target = codePair.substring(3);
        if (codeIsValid(base) && codeIsValid(target)) {
            return new CurrencyPair(base, target);
        }
        throw new CodePairInvalidExceptionDTO();
    }

    protected String getCodeFromPatch(HttpServletRequest request) throws CodeInvalidExceptionDTO {
        String code = getUrlArguments(request);
        if (codeIsValid(code)) {
            return code;
        }
        throw new CodeInvalidExceptionDTO();
    }

    private boolean codeIsValid(String code) {
        return code != null && code.length() == 3;
    }

    public void isNotEmpty(List<String> fields) throws NeedFieldEmptyExceptionDTO {
        for (String string : fields) {
            if (string.isEmpty()) {
                throw new NeedFieldEmptyExceptionDTO();
            }
        }
    }
}
