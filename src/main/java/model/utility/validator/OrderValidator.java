package model.utility.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class OrderValidator {
    public static RequestValidator validateSignin(HttpServletRequest request){
        RequestValidator requestValidator=new RequestValidator(request);
        requestValidator.assertMatch("nome", Pattern.compile("^[\\s\\S]{5,30}$"),"nome compreso fra 5 e 30 caratteri");
        requestValidator.assertMatch("cognome",Pattern.compile("^[\\s\\S]{5,30}$"),"cognome compreso fra 5 e 30 caratteri");
        requestValidator.assertEmail("email","Inserisci email in formato x@x.x");
        requestValidator.assertDestinazione("destinazione","La destinazione deve essere in formato citta',nomevia numerocivico");
        return requestValidator;
    }
}
