package model.carrello;

import model.prodotto.Prodotto;

public class CartItems {

    private  Prodotto prodotto;
    private  int quantita;

    public CartItems(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita){
        this.quantita=quantita;
    }
    public void setProdotto(Prodotto prodotto){
        this.prodotto=prodotto;
    }

    public double totale(){
        return prodotto.getPrezzo()*quantita;
    }


}
