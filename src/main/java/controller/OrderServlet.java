package controller;

import model.carrello.Carrello;
import model.carrello.CartItems;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import model.utente.UtenteDao;
import model.utente.UtenteSession;
import model.utility.Controller;
import model.utility.ErrorHandler;
import model.utility.InvalidRequestException;
import model.utility.Paginator;
import model.utility.validator.OrderValidator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/orders/*")
public class OrderServlet extends Controller implements ErrorHandler {
    private OrdineDAO ordineDAO;

    public void init() throws ServletException {
        super.init();
        ordineDAO = new OrdineDAO();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/make":
                    request.getRequestDispatcher("/WEB-INF/views/site/makeOrder.jsp").forward(request, response);
                    break;
                case"/add":
                    request.setAttribute("back","/WEB-INF/views/site/makeOrder.jsp");
                    validate(OrderValidator.validateSignin(request));
                    Ordine ordine=new Ordine();
                    Utente utente=new Utente();
                    utente.setNome(request.getParameter("nome"));
                    utente.setCognome(request.getParameter("cognome"));
                    utente.setEmail(request.getParameter("email"));
                    UtenteSession utenteSession=getUtenteSession(request.getSession(false));
                    if(utenteSession!=null){
                        utente.setId(utenteSession.getId());
                        ordine.setUtente_id(utenteSession.getId());
                    }else{
                        UtenteDao utenteDao=new UtenteDao();
                        utente.setId(utenteDao.doSave(utente));
                        ordine.setUtente_id(utente.getId());
                    }
                    ordine.setDestinazione(request.getParameter("destinazione"));
                    Carrello carrello=getSessionCart(request.getSession(false));
                    ordine.setTotale(carrello.totale());
                    ordine.setIdordine(ordineDAO.doSave(ordine));
                    ordine.setCarrello(carrello);
                    ordineDAO.doSaveProdotti(ordine);
                    ProdottoDAO prodottoDAO=new ProdottoDAO();
                    for(CartItems prodotto:ordine.getCarrello().getItems()){
                        prodottoDAO.buyied(prodotto);
                    }
                    request.setAttribute("nome",utente.getNome());
                    request.setAttribute("cognome",utente.getCognome());
                    request.setAttribute("email",utente.getEmail());
                    request.setAttribute("destinazione",ordine.getDestinazione());
                    carrello.resetCart();
                    request.getSession(false).setAttribute("carrello",carrello);
                    request.getRequestDispatcher("/WEB-INF/views/site/doneOrder.jsp").forward(request, response);
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
                case "/showall":
                    authorize(request.getSession(false));
                    int intPage;
                    if (request.getParameter("page") == null) {
                        intPage = 1;
                    } else {
                        intPage = parsePage(request);
                    }
                    Paginator paginator = new Paginator(intPage, 8);
                    List<Ordine> ordini = ordineDAO.doRetriveForPage(paginator);
                    int size = ordineDAO.countAll();
                    request.setAttribute("ordini", ordini);
                    request.setAttribute("pages", paginator.getPages(size));
                    if (getUtenteSession(request.getSession(false)).isAdmin()) {
                        request.getRequestDispatcher("/WEB-INF/views/admin/orders.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("/WEB-INF/views/profile/orders.jsp").forward(request, response);
                    }
                    break;
                case "/info":
                    authenticate(request.getSession(false));
                    Ordine tmpOrdine;
                    tmpOrdine = ordineDAO.doRetrieveById(Integer.parseInt(request.getParameter("ordineid")));
                    tmpOrdine.setProdottoList(ordineDAO.doRetriveProdottiOrdine(tmpOrdine.getIdordine()));
                    request.setAttribute("ordine", tmpOrdine);
                    request.getRequestDispatcher("/WEB-INF/views/admin/orderInfo.jsp").forward(request, response);
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