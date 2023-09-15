package controller;


import model.categoria.Categoria;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utente.Utente;
import model.utente.UtenteDao;
import model.utente.UtenteSession;
import model.utility.*;
import model.utility.validator.AccountUpdateValidator;
import model.utility.validator.AccountValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;



@WebServlet(name = "AccountServlet", value = "/accounts/*")
public class AccountServlet extends Controller implements ErrorHandler {
    private UtenteDao utenteDao;
    private ProdottoDAO prodottoDAO;
    private OrdineDAO ordineDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        utenteDao = new UtenteDao();
        prodottoDAO= new ProdottoDAO();
        ordineDAO=new OrdineDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/home":
                    int intPagehome;
                    if(request.getParameter("page")==null){
                        intPagehome=1;
                    }else{
                        intPagehome=parsePage(request);
                    }
                    Paginator paginatorhome=new Paginator(intPagehome,8);
                    List<Prodotto> prodottihome=prodottoDAO.doRetriveForPage(paginatorhome);
                    int sizehome=prodottoDAO.countAll();
                    request.setAttribute("pages",paginatorhome.getPages(sizehome));
                    request.setAttribute("prodotti",prodottihome);
                    request.getRequestDispatcher("/WEB-INF/views/site/home2.jsp").forward(request, response);
                    break;
                case "/admin":
                    authorize(request.getSession(false));
                    int totaleUtenti=utenteDao.countAll();
                    int totaleProdotti=prodottoDAO.countAll();
                    int totaleOrdini=ordineDAO.countAll();
                    double totaleGuadagno=ordineDAO.getGuadagno();
                    request.setAttribute("totaleUtenti",totaleUtenti);
                    request.setAttribute("totaleProdotti",totaleProdotti);
                    request.setAttribute("totaleOrdini",totaleOrdini);
                    request.setAttribute("totaleGuadagno",totaleGuadagno);
                    request.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(request, response);
                    break;
                case "/signin":
                    request.getRequestDispatcher("/WEB-INF/views/site/login.jsp").forward(request, response);
                    break;
                case "/register":
                    request.getRequestDispatcher("/WEB-INF/views/site/signup.jsp").forward(request, response);
                    break;
                case "/profile":
                    authenticate(request.getSession(false));
                        Utente utente1=new Utente();
                        utente1.setId(getUtenteSession(request.getSession(false)).getId());
                        utente1.setEmail(getUtenteSession(request.getSession(false)).getEmail());
                        utente1.setNome(getUtenteSession(request.getSession(false)).getNome());
                        utente1.setCognome(getUtenteSession(request.getSession(false)).getCognome());
                        request.setAttribute("utente1",utente1);
                        request.getRequestDispatcher("/WEB-INF/views/profile/home.jsp").forward(request, response);
                    break;
                case "/logout":
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    if(getSessionCart(request.getSession(false))!=null)
                        getSessionCart(request.getSession(false)).resetCart();
                    session.removeAttribute("carrello");
                    session.removeAttribute("utenteSession");
                    session.invalidate();
                    response.sendRedirect("/KozmoMusic_war_exploded/accounts/home");
                    break;
                case "/update":
                    authenticate(request.getSession(false));
                    if(getUtenteSession(request.getSession(false)).isAdmin()){
                        Utente tmpUtente;
                        tmpUtente=utenteDao.doRetrieveById(Integer.parseInt(request.getParameter("utenteid")));
                        request.setAttribute("utente1",tmpUtente);
                        request.getRequestDispatcher("/WEB-INF/views/admin/profilesUpdate.jsp").forward(request, response);
                    }else {
                        request.getRequestDispatcher("/WEB-INF/views/profile/update.jsp").forward(request, response);
                    }
                    break;
                case "/orders":
                    authenticate(request.getSession(false));
                    int intPage1;
                    if(request.getParameter("page")==null){
                        intPage1=1;
                    }else{
                        intPage1=parsePage(request);
                    }
                    Paginator paginator1=new Paginator(intPage1,8);
                    OrdineDAO ordineDAO=new OrdineDAO();
                    List<Ordine> ordineList=ordineDAO.doRetriveOrdersByCustomerID(getUtenteSession(request.getSession(false)).getId(),paginator1);
                    int size1=ordineDAO.countOrdersUtente(getUtenteSession(request.getSession(false)).getId());
                    request.setAttribute("ordini",ordineList);
                    request.setAttribute("pages",paginator1.getPages(size1));
                    request.getRequestDispatcher("/WEB-INF/views/profile/orders.jsp").forward(request,response);
                    break;
                case"/showall":
                    authorize(request.getSession(false));
                    int intPage;
                    if(request.getParameter("page")==null){
                        intPage=1;
                    }else{
                        intPage=parsePage(request);
                    }
                    Paginator paginator=new Paginator(intPage,8);
                    List<Utente> utenteList=utenteDao.doRetriveForPage(paginator);
                    int size=utenteDao.countAll();
                    request.setAttribute("utenti",utenteList);
                    request.setAttribute("pages",paginator.getPages(size));
                    request.getRequestDispatcher("/WEB-INF/views/admin/profiles.jsp").forward(request, response);
                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/create":
                    request.setAttribute("back", "/WEB-INF/views/site/signup.jsp");
                    validate(AccountValidator.validateSignin(request));
                    Utente utente=new Utente();
                    utente.setNome(request.getParameter("nome"));
                    utente.setCognome(request.getParameter("cognome"));
                    utente.setEmail(request.getParameter("email"));
                    utente.setPassword(request.getParameter("password"));
                    utente.setAdmin(false);
                    utenteDao.doSave(utente);
                    if(utente!=null){
                        UtenteSession utenteSession = new UtenteSession(utente);
                        request.getSession(true).setAttribute("utenteSession", utenteSession);
                        response.sendRedirect("/KozmoMusic_war_exploded/accounts/home");
                    }else{
                        internalError();
                    }
                    break;
                case "/login":
                        request.setAttribute("back", "/WEB-INF/views/site/login.jsp");
                        Utente tmpUtente=new Utente();
                        tmpUtente.setEmail(request.getParameter("email"));
                        tmpUtente.setPassword(request.getParameter("password"));
                        tmpUtente = utenteDao.findUtente(tmpUtente.getEmail(), tmpUtente.getPassword());
                        if (tmpUtente!=null) {
                            UtenteSession utenteSession = new UtenteSession(tmpUtente);
                            request.getSession(true).setAttribute("utenteSession", utenteSession);
                            if (utenteSession.isAdmin()) {
                                response.sendRedirect("/KozmoMusic_war_exploded/accounts/admin");
                            } else {
                                response.sendRedirect("/KozmoMusic_war_exploded/accounts/home");
                            }
                        } else {
                            throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                        }
                    break;
                case "/update":
                    if(getUtenteSession(request.getSession(false)).isAdmin()) {
                        request.setAttribute("back", "/WEB-INF/views/admin/profilesUpdate.jsp");
                    }else{
                        request.setAttribute("back", "/WEB-INF/views/profile/update.jsp");
                    }
                    validate(AccountUpdateValidator.validateSignin(request));
                    Utente tmpUtente1=new Utente();
                    tmpUtente1.setNome(request.getParameter("nomeUtente"));
                    tmpUtente1.setCognome(request.getParameter("cognomeUtente"));
                    tmpUtente1.setEmail(request.getParameter("emailUtente"));
                    if(getUtenteSession(request.getSession(false)).isAdmin()) {
                        tmpUtente1.setId(Integer.parseInt(request.getParameter("idUtente")));
                    }
                    else{
                        tmpUtente1.setId(getUtenteSession(request.getSession(false)).getId());
                    }
                    tmpUtente1.setAdmin(Boolean.parseBoolean((request.getParameter("adminUtente"))));
                    if(tmpUtente1!=null){
                        utenteDao.doUpdateUtente(tmpUtente1);
                        if(getUtenteSession(request.getSession(false)).isAdmin()){
                            response.sendRedirect("/KozmoMusic_war_exploded/accounts/showall");
                        }else {
                            UtenteSession newutenteSession = new UtenteSession(tmpUtente1);
                            request.getSession(false).setAttribute("utenteSession", newutenteSession);
                            response.sendRedirect("/KozmoMusic_war_exploded/accounts/profile");
                        }
                    }else {
                        internalError();
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
}