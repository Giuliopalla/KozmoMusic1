package model.utente;


import model.utility.ConPool;
import model.utility.Paginator;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UtenteDao {

    public List<Utente> doRetriveAll() {
        List<Utente> lista = new ArrayList<>();
        Utente utente;
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT *FROM utente Where 1=1");
            while (rs.next()) {
                utente = new Utente();
                utente.setId(rs.getInt(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setPassword((rs.getString(4)));
                utente.setEmail((rs.getString(5)));
                utente.setAdmin(rs.getBoolean(6));
                lista.add(utente);
            }
            con.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (nome, cognome, password, email, admin) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getPassword());
            ps.setString(4, utente.getEmail());
            ps.setBoolean(5, utente.isAdmin());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            utente.setId(id);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE idutente=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getInt(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setPassword(rs.getString(4));
                utente.setEmail(rs.getString(5));
                utente.setAdmin(rs.getBoolean(6));
                return utente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente findUtente(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            Utente utente=null;
            if (rs.next()) {
                utente=new Utente();
                utente.setId(rs.getInt(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setEmail(rs.getString(5));
                utente.setAdmin(rs.getBoolean(6));
            }
            return utente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateUtente(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            Statement s = con.createStatement();
            int bool= utente.isAdmin() ? 1:0;
            String query = "update utente set nome='" + utente.getNome() + "', cognome='" + utente.getCognome() + "', email='" + utente.getEmail() + "', admin='" + bool + "' where idutente='" + utente.getId() + "';";
            s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUtentebyId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM utente WHERE idutente=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Utente> doRetriveForPage(Paginator paginator) {
        List<Utente> lista = new ArrayList<>();
        Utente utente;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM utente LIMIT ?,?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,paginator.getOffset());
            ps.setInt(2,paginator.getLimit());
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                utente=new Utente();
                utente.setId(rs.getInt(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setEmail(rs.getString(5));
                utente.setAdmin(rs.getBoolean(6));
                lista.add(utente);
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
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM utente");
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
