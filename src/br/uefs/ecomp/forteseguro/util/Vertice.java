/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <//2019>
 *
 * Declaro que este código foi elaborado por nós de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package br.uefs.ecomp.forteseguro.util;

public class Vertice<T> {
    private T obj;
    private int grauVertice;
    private boolean foiVisitado;
    private int posX;
    private int posY;
    private int tipo;
    //private List<Arestas<T>> listaArestas;

    public Vertice(T obj, int posX, int posY){
        this.obj = obj;
        this.grauVertice = 0;
        this.foiVisitado = false;
        this.posX = posX;
        this.posY = posY;
        //listaArestas = new ArrayList<>();
    }
    
    public Vertice(T obj, int tipo){
        this.obj = obj;
        this.tipo = tipo;
        this.grauVertice = 0;
        this.foiVisitado = false;
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
    
    public int getTipo(){
        return tipo;
    }
    
    public void setTipo(int tipo){
        this.tipo = tipo;
    }
    
    public boolean equals(Vertice vertice){
        return this.obj.equals(vertice.getObj()) &&
                this.foiVisitado == vertice.foiVisitado() &&
                this.grauVertice == vertice.getGrauVertice() /*&&
                this.tipo == vertice.getTipo()*/; /*REVER*/
    }
}
