package model.ordine;



import model.carrello.CartItems;
import model.prodotto.Prodotto;
import model.utility.ConPool;
import model.utility.Paginator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class OrdineDAO {
    public List<Ordine> doRetriveAll(){
        List<Ordine> lista = new ArrayList<>();
        Ordine ordine;
        try (Connection con = ConPool.getConnection()) {
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT *FROM ordine Where 1=1");
            while(rs.next()){
                ordine=new Ordine();
                ordine.setIdordine(rs.getInt(1));
                ordine.setUtente_id(rs.getInt(2));
                ordine.setDestinazione(rs.getString(3));
                ordine.setTotale(rs.getDouble(4));
                lista.add(ordine);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Ordine> doRetriveForPage(Paginator paginator){
        List<Ordine> lista = new ArrayList<>();
        Ordine ordine;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM ordine LIMIT ?,?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,paginator.getOffset());
            ps.setInt(2,paginator.getLimit());
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                ordine=new Ordine();
                ordine.setIdordine(rs.getInt(1));
                ordine.setUtente_id(rs.getInt(2));
                ordine.setDestinazione(rs.getString(3));
                ordine.setTotale(rs.getDouble(4));
                lista.add(ordine);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int doSave(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (utente_id,destinazione,totale) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordine.getUtente_id());
            ps.setString(2,ordine.getDestinazione());
            ps.setDouble(3,ordine.getTotale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            ordine.setIdordine(id);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doSaveProdotti(Ordine ordine){
        try(Connection con=ConPool.getConnection()){
            for(CartItems prodotto: ordine.getCarrello().getItems()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO ordine_prodotti(idordine,idprodotto,quantita,prezzo) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,ordine.getIdordine());
                ps.setInt(2,prodotto.getProdotto().getIdprodotto());
                ps.setInt(3,prodotto.getQuantita());
                ps.setDouble(4,prodotto.getProdotto().getPrezzo());
                if(ps.executeUpdate()!=1){
                    throw new RuntimeException("INSERT error.");
                }
            }
        }catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
    public Ordine doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine WHERE idordine=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ordine ordine=new Ordine();
                ordine.setIdordine(rs.getInt(1));
                ordine.setUtente_id(rs.getInt(2));
                ordine.setDestinazione(rs.getString(3));
                ordine.setTotale(rs.getDouble(4));
                return ordine;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateOrdine(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            Statement s=con.createStatement();
            String query="update ordine set utente_id="+ordine.getUtente_id()+ ", destinazione="+ordine.getDestinazione()+",totale=" +ordine.getTotale()+ " where idordine=" + ordine.getIdordine() +";";
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOrdinebyId(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ordine WHERE idordine=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOrdineProdottibyId(int id){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM ordine_prodotti WHERE idordine=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return !(rows == 0);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetriveOrdersByCustomerID(int id,Paginator paginator){
        List<Ordine> lista = new ArrayList<>();
        Ordine ordine;
        try (Connection con = ConPool.getConnection()) {
            Statement s=con.createStatement();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT *FROM ordine Where utente_id='"+id+"' LIMIT ?,?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,paginator.getOffset());
            ps.setInt(2,paginator.getLimit());
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                ordine=new Ordine();
                ordine.setIdordine(rs.getInt(1));
                ordine.setUtente_id(rs.getInt(2));
                ordine.setDestinazione(rs.getString(3));
                ordine.setTotale(rs.getDouble(4));
                lista.add(ordine);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Hashtable<Prodotto,Integer> doRetriveProdottiOrdine(int id){
        Hashtable<Prodotto,Integer> prodotti=new Hashtable<>();
        int quantita;
        List<Prodotto> products=new ArrayList<>();
        Prodotto prodotto;
        try(Connection con=ConPool.getConnection()){
            Statement s=con.createStatement();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT *FROM prodotto INNER JOIN ordine_prodotti on prodotto.idprodotto=ordine_prodotti.idprodotto where ordine_prodotti.idordine='"+id+"'",
                    Statement.RETURN_GENERATED_KEYS);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                prodotto=new Prodotto();
                prodotto.setIdprodotto(rs.getInt(1));
                prodotto.setNome(rs.getString(2));
                prodotto.setDescrizione(rs.getString(3));
                prodotto.setPrezzo(rs.getDouble(12));
                prodotto.setFoto(rs.getString(5));
                prodotto.setIscd(rs.getBoolean(6));
                prodotto.setQuantita(rs.getInt(7));
                quantita=rs.getInt(11);
                prodotti.put(prodotto,quantita);
            }

            con.close();
            return prodotti;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public double getGuadagno() {
        double guadagno=0.0;

        try(Connection con=ConPool.getConnection()){
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("SELECT totale FROM ordine");
            while(rs.next()){
               guadagno+=rs.getDouble(1);
            }

            con.close();
            return guadagno;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int countAll() {
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM ordine");
            int size=0;
            if(rs.next()){
                size=rs.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countOrdersUtente(int id) {
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM ordine where utente_id='"+id+"'");
            int size=0;
            if(rs.next()){
                size=rs.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countAllProduct(int id) {
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM ordine_prodotti where idordine='"+id+"'");
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