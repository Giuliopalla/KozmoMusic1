package model.prodotto;

public class Prodotto {
    private int idprodotto;
    private int quantita;
    private String nome,descrizione,foto;
    private boolean iscd;
    private double prezzo;

    public Prodotto() {
    };

    public int getIdprodotto() {
        return idprodotto;
    }

    public void setIdprodotto(int idprodotto) {
        this.idprodotto = idprodotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isIscd() {
        return iscd;
    }

    public void setIscd(boolean iscd) {
        this.iscd = iscd;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
