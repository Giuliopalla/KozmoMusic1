package model.utility.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class AccountUpdateValidator {
    public static RequestValidator validateSignin(HttpServletRequest request){
        RequestValidator requestValidator=new RequestValidator(request);
        requestValidator.assertMatch("nomeUtente", Pattern.compile("\\w\s{5,30}$"),"nome compreso fra 5 e 30 caratteri");
        requestValidator.assertMatch("cognomeUtente",Pattern.compile("\\w\s{5,30}$"),"cognome compreso fra 5 e 30 caratteri");
        requestValidator.assertEmail("emailUtente","Inserisci email in formato x@x.x");
        return requestValidator;
    }
}