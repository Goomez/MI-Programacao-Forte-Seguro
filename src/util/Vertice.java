package util;

public class Vertice<T> {
    private T obj;
    private int grauVertice;
    private boolean foiVisitado;
    private int posX;
    private int posY;
    //private List<Arestas<T>> listaArestas;

    public Vertice(T obj, int posX, int posY){
        this.obj = obj;
        this.grauVertice = 0;
        this.foiVisitado = false;
        this.posX = posX;
        this.posY = posY;
        //listaArestas = new ArrayList<>();
    }
    
    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
