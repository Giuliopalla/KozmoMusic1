package controller;

import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.ordine.Ordine;
import model.ordine.OrdineDAO;
import model.prodotto.Prodotto;
import model.utility.Controller;
import model.utility.ErrorHandler;
import model.utility.InvalidRequestException;
import model.utility.Paginator;
import model.utility.validator.CategoryValidator;
import model.utility.validator.ProductValidator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriaServlet", value = "/category/*")
public class CategoriaServlet extends Controller implements ErrorHandler {
    private CategoriaDAO categoriaDAO;
    public void init() throws ServletException {
        super.init();
        categoriaDAO=new CategoriaDAO();

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", "/WEB-INF/views/admin/categoryUpdate.jsp");
                    validate(CategoryValidator.validateSignin(request));
                    Categoria categoria=new Categoria();
                    categoria.setIdcategoria(Integer.parseInt(request.getParameter("idcategoria")));
                    categoria.setTipologia(request.getParameter("tipologia"));
                    categoriaDAO.doUpdateCategoria(categoria);
                    response.sendRedirect("/KozmoMusic_war_exploded/category/showall");
                    break;
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", "/WEB-INF/views/admin/categoryAdd.jsp");
                    validate(CategoryValidator.validateSignin(request));
                    Categoria categoria1=new Categoria();
                    categoria1.setTipologia(request.getParameter("tipologia"));
                    categoriaDAO.doSave(categoria1);
                    response.sendRedirect("/KozmoMusic_war_exploded/category/showall");
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", "/WEB-INF/views/admin/categoryUpdate.jsp");
                    if(categoriaDAO.deleteCategoriabyId(Integer.parseInt(request.getParameter("idcategoria")))){
                        categoriaDAO.deleteCategoriaProdottobyId(Integer.parseInt(request.getParameter("idcategoria")));
                        response.sendRedirect("/KozmoMusic_war_exploded/category/showall");
                    }else{
                        throw new InvalidRequestException("Errore eliminazione", List.of("Errore eliminazione categoria"), HttpServletResponse.SC_BAD_REQUEST);
                    }

                    break;
                default:
                    notFound();
            }
            }catch (InvalidRequestException e){
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
                    if(request.getParameter("page")==null){
                        intPage=1;
                    }else{
                        intPage=parsePage(request);
                    }
                    Paginator paginator=new Paginator(intPage,7);
                    List<Categoria> categorie=categoriaDAO.doRetriveForPage(paginator);
                    int size=categoriaDAO.countAll();
                    request.setAttribute("categorie",categorie);
                    request.setAttribute("pages",paginator.getPages(size));
                    request.getRequestDispatcher("/WEB-INF/views/admin/categories.jsp").forward(request,response);
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    Categoria tmpCat=categoriaDAO.doRetrieveById(Integer.parseInt(request.getParameter("categoriaid")));
                    request.setAttribute("categoria1",tmpCat);
                    request.getRequestDispatcher("/WEB-INF/views/admin/categoryUpdate.jsp").forward(request, response);
                    break;
                case "/add":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher("/WEB-INF/views/admin/categoryAdd.jsp").forward(request, response);
                    break;
                case"/api":
                    if(isAjax(request)){
                        List<Categoria> cat=categoriaDAO.doRetriveAll();
                        JSONObject root=new JSONObject();
                        JSONArray arr=new JSONArray();
                        root.put("categories",arr);
                        cat.forEach(categoria -> arr.put(categoria.toJSON()));
                        sendJson(response,root);
                        break;
                    }else{
                        notFound();
                    }
                    break;
                default:
                    notFound();
            }
        }catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request, response);
        }
    }

}
