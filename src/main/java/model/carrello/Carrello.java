package model.carrello;



import model.prodotto.Prodotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.round;

public class Carrello {

    private List<CartItems> items;

    public Carrello(List<CartItems> items) {
        this.items = items;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }

    public int quantita() {
        int quantita = 0;
        for (CartItems item : items) {
            quantita += item.getQuantita();
        }
        return quantita;
    }

    public double totale() {
        double totale = 0.0;
        for (CartItems item : items) {
            totale += item.totale();
        }
        BigDecimal bd=new BigDecimal(totale).setScale(2, RoundingMode.HALF_UP);
        double tot=bd.doubleValue();
        return tot;
    }

    public boolean addProduct(Prodotto prodotto, int quantita) {
        for (CartItems product : items) {
                if (product.getProdotto().getIdprodotto() == prodotto.getIdprodotto()) {
                    if(product.getQuantita()<prodotto.getQuantita()) {
                        product.setQuantita(product.getQuantita() + 1);
                        return true;
                    }else if(product.getQuantita()>=prodotto.getQuantita()) {
                        product.setQuantita(prodotto.getQuantita());
                        return true;
                    }
                }
            }

            return items.add(new CartItems(prodotto, quantita));
        }



    public boolean removeProduct(int id) {
        for (CartItems product : items) {
            if (product.getProdotto().getIdprodotto() == id) {
                return items.remove(product);
            }
        }
        return false;
    }

    public void resetCart(){
        items.clear();
    }
}
