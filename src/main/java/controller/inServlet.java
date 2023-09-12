package controller;

import com.mysql.cj.Session;
import model.carrello.Carrello;
import model.carrello.CartItems;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.utility.Paginator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "inServlet", value = "/inServlet", loadOnStartup = 0)
public class inServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorie = categoriaDAO.doRetriveAll();
        getServletContext().setAttribute("categorie", categorie);
        super.init();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}