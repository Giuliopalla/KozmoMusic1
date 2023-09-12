package model.utility;


import javax.servlet.http.HttpServletRequest;

public class Parametri {
    private final String name;
    private final int type;
    private final int categoria;
    private final int pricemin,pricemax;
    public Parametri(HttpServletRequest request) {
        if(request.getParameter("searchname")==null){
            this.name="";
        }else {
            this.name = request.getParameter("searchname");
        }

        if(request.getParameter("searchtype")==null){
            this.type=-1;
        }else {
            this.type = Integer.parseInt(request.getParameter("searchtype"));
        }

        if(Integer.parseInt(request.getParameter("searchcategoria"))==0){
            this.categoria=-1;
        }else {
            this.categoria = Integer.parseInt(request.getParameter("searchcategoria"));
        }

        if(request.getParameter("pricemin")==""){
            this.pricemin=-99;
        }else {
            this.pricemin = Integer.parseInt(request.getParameter("pricemin"));
        }

       if(request.getParameter("pricemax")==""){
            this.pricemax=-99;
        }else {
            this.pricemax = Integer.parseInt(request.getParameter("pricemax"));
        }
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getPricemin() {
        return pricemin;
    }

    public int getPricemax() {
        return pricemax;
    }
}
