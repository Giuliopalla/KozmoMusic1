package model.prodotto;

import model.carrello.CartItems;
import model.utility.ConPool;
import model.utility.Paginator;
import model.utility.Parametri;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    public List<Prodotto> doRetriveAll() {
        List<Prodotto> lista = new ArrayList<>();
        Prodotto prodotto;
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT *FROM prodotto Where 1=1");
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                lista.add(prodotto);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetriveForPage(Paginator paginator) {
        List<Prodotto> lista = new ArrayList<>();
        Prodotto prodotto;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM prodotto LIMIT ?,?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, paginator.getOffset());
            ps.setInt(2, paginator.getLimit());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                lista.add(prodotto);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (nome,descrizione,prezzo,foto,iscd,quantita) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getDescrizione());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setString(4, prodotto.getFoto());
            ps.setBoolean(5, prodotto.isIscd());
            ps.setInt(6, prodotto.getQuantita());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            prodotto.setIdprodotto(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSaveCategoriaProdotto(Prodotto prodotto, int idcategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotti_categoria(idprodotto,idcategoria) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prodotto.getIdprodotto());
            ps.setInt(2, idcategoria);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM prodotto WHERE idprodotto=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                return prodotto;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateProdotto(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            int iscd = prodotto.isIscd() ? 1 : 0;
            Statement s = con.createStatement();
            String query = "update prodotto set nome='" + prodotto.getNome() + "', descrizione='" + prodotto.getDescrizione() + "', prezzo='" + prodotto.getPrezzo() + "',foto='" + prodotto.getFoto() + "', iscd='" + iscd + "', quantita='" + prodotto.getQuantita() + "' where idprodotto='" + prodotto.getIdprodotto() + "';";
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteProdottobyId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE idprodotto=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCategoriaProdottobyId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM prodotti_categoria WHERE idprodotto=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return !(rows == 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetriveProdottibyCategoria(int id) {
        List<Prodotto> prodotti = new ArrayList<>();
        Prodotto prodotto;
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT *FROM prodotto INNER JOIN prodotti_categoria on prodotto.idprodotto=prodotti_categoria.idprodotto where prodotti_categoria.idcategoria=" + id + ";");
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                prodotti.add(prodotto);
            }
            con.close();
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetriveCD() {
        List<Prodotto> prodotti = new ArrayList<>();
        Prodotto prodotto;
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM prodotto WHERE iscd=" + true + ";");
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                prodotti.add(prodotto);
            }
            con.close();
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetriveVynil() {
        List<Prodotto> prodotti = new ArrayList<>();
        Prodotto prodotto;
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM prodotto WHERE iscd=" + false + ";");
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                prodotti.add(prodotto);
            }
            con.close();
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countAll() {
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM prodotto");
            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Prodotto> search(Parametri parametri, Paginator paginator) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT distinct prodotto.idprodotto, nome, descrizione, prezzo, foto, iscd, quantita FROM prodotto INNER JOIN prodotti_categoria on prodotto.idprodotto=prodotti_categoria.idprodotto");
        sb.append(" WHERE prodotto.nome LIKE '%" + parametri.getName() + "%' ");
        if (parametri.getCategoria() != -1) {
            sb.append("AND prodotti_categoria.idcategoria='" + parametri.getCategoria() + "' ");
        }
        if ((parametri.getPricemax() != -99) && (parametri.getPricemin() != -99)) {
            sb.append("AND prodotto.prezzo BETWEEN '" + parametri.getPricemin() + "' AND '" + parametri.getPricemax() + "' ");
        } else if ((parametri.getPricemax() != -99) && (parametri.getPricemin() == -99)) {
            sb.append("AND prodotto.prezzo <" + parametri.getPricemax() + " ");
        } else if ((parametri.getPricemax() == -99) && (parametri.getPricemin() != -99)) {
            sb.append("AND prodotto.prezzo >" + parametri.getPricemin() + " ");
        }
        if (parametri.getType() != -1) {
            sb.append("AND prodotto.iscd='" + parametri.getType() + "' ");
        }
        sb.append("LIMIT " + paginator.getOffset() + "," + paginator.getLimit() + " ");
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement s = con.prepareStatement(sb.toString());
            ResultSet rs=s.executeQuery();
            List<Prodotto> prodottos=new ArrayList<>();
            while(rs.next()){
                Prodotto prodotto=new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                prodottos.add(prodotto);
            }
            return prodottos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int searchCount(Parametri parametri) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT distinct prodotto.idprodotto, nome, descrizione, prezzo, foto, iscd, quantita FROM prodotto INNER JOIN prodotti_categoria on prodotto.idprodotto=prodotti_categoria.idprodotto");
        sb.append(" WHERE prodotto.nome LIKE '%" + parametri.getName() + "%' ");
        if (parametri.getCategoria() != -1) {
            sb.append("AND prodotti_categoria.idcategoria='" + parametri.getCategoria() + "' ");
        }
        if ((parametri.getPricemax() != -99) && (parametri.getPricemin() != -99)) {
            sb.append("AND prodotto.prezzo BETWEEN '" + parametri.getPricemin() + "' AND '" + parametri.getPricemax() + "' ");
        } else if ((parametri.getPricemax() != -99) && (parametri.getPricemin() == -99)) {
            sb.append("AND prodotto.prezzo <" + parametri.getPricemax() + " ");
        } else if ((parametri.getPricemax() == -99) && (parametri.getPricemin() != -99)) {
            sb.append("AND prodotto.prezzo >" + parametri.getPricemin() + " ");
        }
        if (parametri.getType() != -1) {
            sb.append("AND prodotto.iscd='" + parametri.getType() + "' ");
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement s = con.prepareStatement(sb.toString());
            ResultSet rs=s.executeQuery();
            int size=0;
            while(rs.next()){
                size++;
            }
            return size;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buyied(CartItems prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE prodotto SET quantita=quantita-? WHERE idprodotto=?");
            ps.setInt(1,prodotto.getQuantita());
            ps.setInt(2, prodotto.getProdotto().getIdprodotto());
            int rows = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
