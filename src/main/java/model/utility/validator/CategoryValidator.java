package model.utility.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CategoryValidator {
    public static RequestValidator validateSignin(HttpServletRequest request) {
        RequestValidator requestValidator=new RequestValidator(request);
        requestValidator.assertMatch("tipologia", Pattern.compile("^[\\s\\S]{3,30}$"),"tipologia compresa fra 3 e 30 caratteri");
        return requestValidator;
    }
}
