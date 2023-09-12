package model.ordine;

import model.carrello.Carrello;
import model.prodotto.Prodotto;

import java.util.Hashtable;
import java.util.List;

public class Ordine {
    private int idordine,utente_id;
    private String destinazione;
    private double totale;
    private Carrello carrello;
    private Hashtable<Prodotto,Integer> prodottoList;

    public Ordine() {
    };

    public int getIdordine() {
        return idordine;
    }

    public void setIdordine(int idordine) {
        this.idordine = idordine;
    }

    public int getUtente_id() {
        return utente_id;
    }

    public void setUtente_id(int utente_id) {
        this.utente_id = utente_id;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }
    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Hashtable<Prodotto,Integer> getProdottoList() {
        return prodottoList;
    }

    public void setProdottoList(Hashtable<Prodotto,Integer> prodottoList) {
        this.prodottoList = prodottoList;
    }
}
