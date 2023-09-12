package controller;

import model.carrello.Carrello;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utility.Controller;
import model.utility.InvalidRequestException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "CartServlet", value = "/cart/*")
public class CartServlet extends Controller{

    private ProdottoDAO prodottoDAO;
    @Override
    public void init() throws ServletException{
        super.init();
        prodottoDAO=new ProdottoDAO();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/add":
                    int quantity=0;
                    int id= Integer.parseInt(request.getParameter("idprodotto"));
                    String info=request.getParameter("info");
                    if(request.getParameter("productquantity")==""){
                         quantity=1;
                    }else {
                         quantity = Integer.parseInt(request.getParameter("productquantity"));
                    }
                    Prodotto optProd= (prodottoDAO.doRetrieveById(id));
                    if(optProd!=null){
                        if(request.getSession(false).getAttribute("carrello")==null){
                            request.getSession(true).setAttribute("carrello",new Carrello(new ArrayList<>()));
                        }
                        getSessionCart(request.getSession(false)).addProduct(optProd,quantity);
                        System.out.println(info);
                        if(info!=null){
                            response.sendRedirect("/KozmoMusic_war_exploded/products/show?id="+id);
                        }else{
                            response.sendRedirect("/KozmoMusic_war_exploded/accounts/home");
                        }
                    }else{
                        notFound();
                    }
                    break;
                case"/remove":
                    int removeid=Integer.parseInt(request.getParameter("idprodotto"));
                    if(getSessionCart(request.getSession(false)).removeProduct(removeid)){
                        response.sendRedirect("/KozmoMusic_war_exploded/cart/show");
                    }else{
                        notFound();
                    }
                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request, response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/show":
                    request.getRequestDispatcher("/WEB-INF/views/site/showcart.jsp").forward(request,response);
                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request, response);
        }
            }

    }
