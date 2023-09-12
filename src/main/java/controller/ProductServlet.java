package controller;


import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utility.Controller;
import model.utility.ErrorHandler;
import model.utility.InvalidRequestException;
import model.utility.Paginator;
import model.utility.Parametri;
import model.utility.validator.ProductValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller implements ErrorHandler {
    private ProdottoDAO prodottoDAO;
    private CategoriaDAO categoriaDAO;
    Parametri parametri;
    @Override
    public void init() throws ServletException {
        super.init();
        prodottoDAO= new ProdottoDAO();
        categoriaDAO=new CategoriaDAO();

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", "/WEB-INF/views/admin/productAdd.jsp");
                    validate(ProductValidator.validateSignin(request));
                    int categoria1 = Integer.parseInt(request.getParameter("Categoria1Prodotto"));
                    int categoria2 = Integer.parseInt(request.getParameter("Categoria2Prodotto"));
                    Prodotto prodotto = new Prodotto();
                    prodotto.setNome(request.getParameter("NomeProd"));
                    prodotto.setDescrizione(request.getParameter("DescProd"));
                    prodotto.setIscd(Boolean.parseBoolean(request.getParameter("IsCDProd")));
                    prodotto.setQuantita(Integer.parseInt(request.getParameter("QuantitaProdotto")));
                    prodotto.setPrezzo(Double.parseDouble(request.getParameter("PrezzoProd")));
                    Part filePart = request.getPart("FotoProd");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    fileName=timestamp.getTime()+fileName;
                    prodotto.setFoto("/images/"+ fileName);
                    try (InputStream fileStream = filePart.getInputStream()) {
                        File file = new File("C:\\Users\\giuli\\IdeaProjects\\KozmoMusic\\src\\main\\webapp\\images\\" + fileName);
                        Files.copy(fileStream, file.toPath());
                    } catch (FileNotFoundException e) {
                        throw new InvalidRequestException("File non valido", List.of("File non valido"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                    prodottoDAO.doSave(prodotto);
                    prodottoDAO.doSaveCategoriaProdotto(prodotto, categoria1);
                    prodottoDAO.doSaveCategoriaProdotto(prodotto, categoria2);
                    response.sendRedirect("/KozmoMusic_war_exploded/products/showall");
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", "/WEB-INF/views/admin/productUpdate.jsp");
                    validate(ProductValidator.validateSignin(request));
                    int categorie1 = Integer.parseInt(request.getParameter("Categoria1Prodotto"));
                    int categorie2 = Integer.parseInt(request.getParameter("Categoria2Prodotto"));
                    Prodotto tmpProd=new Prodotto();
                    tmpProd.setNome(request.getParameter("NomeProd"));
                    tmpProd.setDescrizione(request.getParameter("DescProd"));
                    tmpProd.setIscd(Boolean.parseBoolean(request.getParameter("IsCDProd")));
                    tmpProd.setQuantita(Integer.parseInt(request.getParameter("QuantitaProdotto")));
                    tmpProd.setPrezzo(Double.parseDouble(request.getParameter("PrezzoProd")));
                    tmpProd.setIdprodotto(Integer.parseInt(request.getParameter("idProdotto")));
                    Part filePart1 = request.getPart("FotoProd");
                    Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
                    String fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString();
                    fileName1=timestamp1.getTime()+fileName1;
                    tmpProd.setFoto("/images/"+fileName1);
                    try (InputStream fileStream = filePart1.getInputStream()) {
                        File file = new File("C:\\Users\\giuli\\IdeaProjects\\KozmoMusic\\src\\main\\webapp\\images\\" + fileName1);
                        Files.copy(fileStream, file.toPath());
                    } catch (FileNotFoundException e) {
                        throw new InvalidRequestException("File non valido", List.of("File non valido"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                    prodottoDAO.doUpdateProdotto(tmpProd);
                    prodottoDAO.deleteCategoriaProdottobyId(tmpProd.getIdprodotto());
                    prodottoDAO.doSaveCategoriaProdotto(tmpProd,categorie1);
                    prodottoDAO.doSaveCategoriaProdotto(tmpProd,categorie2);
                    response.sendRedirect("/KozmoMusic_war_exploded/products/showall");
                    break;
                case"/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", "/WEB-INF/views/admin/productUpdate.jsp");
                    if(prodottoDAO.deleteProdottobyId(Integer.parseInt(request.getParameter("idProdotto")))){
                        prodottoDAO.deleteCategoriaProdottobyId(Integer.parseInt(request.getParameter("idProdotto")));
                        response.sendRedirect("/KozmoMusic_war_exploded/products/showall");
                    }else{
                        throw new InvalidRequestException("Errore eliminazione", List.of("Errore eliminazione prodotto"), HttpServletResponse.SC_BAD_REQUEST);
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
                    Paginator paginator=new Paginator(intPage,6);
                    List<Prodotto> prodotti=prodottoDAO.doRetriveForPage(paginator);
                    int size=prodottoDAO.countAll();
                    List<Categoria> categorie= categoriaDAO.doRetriveAll();
                    request.setAttribute("categorie",categorie);
                    request.setAttribute("prodotti",prodotti);
                    request.setAttribute("pages",paginator.getPages(size));
                    request.getRequestDispatcher("/WEB-INF/views/admin/products.jsp").forward(request, response);
                    break;
                case "/add":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher("/WEB-INF/views/admin/productAdd.jsp").forward(request, response);
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    Prodotto tmpProd=prodottoDAO.doRetrieveById(Integer.parseInt(request.getParameter("prodottoid")));
                    request.setAttribute("prodotto1",tmpProd);
                    List<Categoria> categorie1= categoriaDAO.doRetriveAll();
                    request.setAttribute("categorie",categorie1);
                    request.getRequestDispatcher("/WEB-INF/views/admin/productUpdate.jsp").forward(request, response);
                    break;
                case "/home":
                    Parametri newparametri;
                    if(request.getSession().getAttribute("parametri")!=null) {
                        try {
                            newparametri = new Parametri(request);
                            parametri=(Parametri) request.getSession().getAttribute("parametri");
                            if(newparametri!=parametri){
                                parametri=newparametri;
                            }
                        }catch (Exception e){
                            parametri=(Parametri) request.getSession().getAttribute("parametri");
                        }
                    }else{
                         parametri=new Parametri(request);
                         }
                    int intPageSearch;
                    if(request.getParameter("page")==null){
                        intPageSearch=1;
                    }else{
                        intPageSearch=parsePage(request);
                    }
                    Paginator paginatorSearch=new Paginator(intPageSearch,8);
                    List<Prodotto> prodottos=prodottoDAO.search(parametri,paginatorSearch);
                    int sizehome=prodottoDAO.searchCount(parametri);
                    request.setAttribute("pages",paginatorSearch.getPages(sizehome));
                    request.setAttribute("prodotti",prodottos);
                    request.getSession().setAttribute("parametri",parametri);
                    request.getRequestDispatcher("/WEB-INF/views/site/home.jsp").forward(request, response);
                    break;
                case"/show":
                    int id= Integer.parseInt(request.getParameter("id"));
                    Prodotto showProdotto=prodottoDAO.doRetrieveById(id);
                    request.setAttribute("prodotto",showProdotto);
                    request.getRequestDispatcher("/WEB-INF/views/site/showProduct.jsp").forward(request, response);

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