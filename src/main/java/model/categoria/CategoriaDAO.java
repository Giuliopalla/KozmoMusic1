package model.categoria;

import model.utente.Utente;
import model.utility.ConPool;
import model.utility.Paginator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> doRetriveAll(){
        List<Categoria> lista = new ArrayList<>();
        Categoria categoria;
        try (Connection con = ConPool.getConnection()) {
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT *FROM categoria Where 1=1");
            while(rs.next()){
                categoria=new Categoria();
                categoria.setIdcategoria(rs.getInt(1));
                categoria.setTipologia(rs.getString(2));
                lista.add(categoria);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doSave(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO categoria (tipologia) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getTipologia());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            categoria.setIdcategoria(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT *  FROM categoria WHERE idcategoria=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria categoria=new Categoria();
                categoria.setIdcategoria(rs.getInt(1));
                categoria.setTipologia(rs.getString(2));
                return categoria;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateCategoria(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            Statement s=con.createStatement();
            String query="update categoria set tipologia='"+categoria.getTipologia()+"' where idcategoria='" + categoria.getIdcategoria() +"';";
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCategoriabyId(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM categoria WHERE idcategoria=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCategoriaProdottobyId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM prodotti_categoria WHERE idcategoria=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return !(rows == 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria> doRetriveForPage(Paginator paginator) {
        List<Categoria> lista = new ArrayList<>();
        Categoria categoria;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM categoria LIMIT ?,?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,paginator.getOffset());
            ps.setInt(2,paginator.getLimit());
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                categoria=new Categoria();
                categoria.setIdcategoria(rs.getInt(1));
                categoria.setTipologia(rs.getString(2));
                lista.add(categoria);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countAll() {
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM categoria");
            int size=0;
            if(rs.next()){
                size=rs.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

