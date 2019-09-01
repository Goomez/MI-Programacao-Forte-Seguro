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
    
    public boolean equals(Aresta aresta){
        return this.vertice1.equals(aresta.getVertice1()) &&
                this.vertice2.equals(aresta.getVertice2()) &&
                this.peso == aresta.getPeso();
    }
}
