package model.utility.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProductValidator {
    public static RequestValidator validateSignin(HttpServletRequest request) {
        RequestValidator requestValidator=new RequestValidator(request);
        requestValidator.assertMatch("NomeProd", Pattern.compile("^[\\s\\S]{5,50}$"),"nome compreso fra 5 e 50 caratteri");
        requestValidator.assertMatch("DescProd",Pattern.compile("^[\\s\\S]{5,100}$"),"descrizione compresa fra 5 e 100 caratteri");
        requestValidator.assertDouble("PrezzoProd","Inserire prezzo in formato 'x.x'");
        requestValidator.assertInt("QuantitaProdotto","Inserire valore intero");
        return requestValidator;
    }
}
