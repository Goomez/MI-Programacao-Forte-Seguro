package util;

public class Vertice<T> {
    private T obj;
    private int grauVertice;
    private boolean foiVisitado;
    //private List<Arestas<T>> listaArestas;

    public Vertice(T obj){
        this.obj = obj;
        this.grauVertice = 0;
        this.foiVisitado = false;
        //listaArestas = new ArrayList<>();
    }
    
    public boolean foiVisitado() {
        return foiVisitado;
    }

    public void setFoiVisitado(boolean foiVisitado) {
        this.foiVisitado = foiVisitado;
    }

    public int getGrauVertice() {
        return grauVertice;
    }

    public void setGrauVertice(int grauVertice) {
        this.grauVertice = grauVertice;
    }

    public T getObjeto() {
        return this.obj;
    }

    public void setObjeto(T obj) {
        this.obj = obj;
    }
}
