package model.categoria;

import model.utility.JSONSerializable;
import org.json.JSONObject;

public class Categoria implements JSONSerializable {
    private int idcategoria;
    private String tipologia;

    public Categoria() {
    };

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject object=new JSONObject();
        object.put("id",idcategoria);
        object.put("tipologia",tipologia);
        return object;
    }


}
