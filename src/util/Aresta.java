
package util;

public class Aresta<T>{
    private Vertice<T> vertice1;
    private Vertice<T> vertice2;
    private int peso;

    public Aresta(Vertice<T> vertice1, Vertice<T> vertice2, int peso){
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public Vertice<T> getVertice2() {
        return this.vertice2;
    }

    public void setVertice2(Vertice<T> vertice2) {
        this.vertice2 = vertice2;
    }

    public Vertice<T> getVertice1() {
        return this.vertice1;
    }

    public void setVertice1(Vertice<T> vertice1) {
        this.vertice1 = vertice1;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
