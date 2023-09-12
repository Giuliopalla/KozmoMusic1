package model.utility;

public class Paginator {
    private final int limit;
    private final int offset;

    public Paginator(int page,int prodottiPerPagina){
        this.limit=prodottiPerPagina;
        this.offset=(page ==1)?0:(page-1)*prodottiPerPagina;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getPages(int size){
        int additionalPage=(size%limit==0)?0:1;
        return(size/limit)+additionalPage;
    }
}
