package model.utility;

import model.carrello.Carrello;
import model.utente.UtenteSession;
import model.utility.validator.RequestValidator;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet implements ErrorHandler {

    public  void validate(RequestValidator validator) throws InvalidRequestException{
        if(validator.hasErrors()){
            throw new InvalidRequestException("Validation Error",validator.getErrors(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    protected int parsePage(HttpServletRequest request){
        return Integer.parseInt(request.getParameter("page"));
    }

    protected UtenteSession getUtenteSession(HttpSession session){
        return (UtenteSession) session.getAttribute("utenteSession");
    }

    protected Carrello getSessionCart(HttpSession session){
        return (Carrello) session.getAttribute("carrello");
    }

    protected boolean isAjax(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    protected void sendJson(HttpServletResponse response, JSONObject object) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer=response.getWriter();
        writer.print(object.toString());
        writer.flush();
    }
}
