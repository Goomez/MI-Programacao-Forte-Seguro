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

import java.util.ArrayList;
import java.util.List;

public class Grafo<T>{

    private int quantidadeVertices;
    private int quantidadeArestas;
    private List<Vertice<T>> listaVertices;
    private List<Aresta<T>> listaArestas;


    public Grafo(){
        this.quantidadeVertices = 0;
        this.quantidadeArestas = 0;
        this.listaVertices = new ArrayList<>();
        this.listaArestas = new ArrayList<>();
    }

    public int quantidadeVertices() {
        return this.quantidadeVertices;
    }

    public int quantidadeArestas() {
        return this.quantidadeArestas;
    }

    public List<Vertice<T>> getVertices(){
        return this.listaVertices;
    }

    public List<Aresta<T>> getArestas(){
        return this.listaArestas;
    }

    public Vertice<T> inserir(T obj, int posX, int posY) {
        if(obj == null)
            return null;
        Vertice<T> vertice = new Vertice<>(obj, posX, posY);
        this.listaVertices.add(vertice);
        this.quantidadeVertices++;
        return vertice;
    }
    
    public Vertice<T> inserir(T obj, int tipo) {
        if(obj == null)
            return null;
        Vertice<T> vertice = new Vertice<>(obj, tipo);
        this.listaVertices.add(vertice);
        this.quantidadeVertices++;
        return vertice;
    }

    public boolean inserirAresta(Vertice<T> vertice1, Vertice<T> vertice2, int peso) {
        if(vertice1 == null || vertice2 == null)
            return false;
        this.listaArestas.add(new Aresta<>(vertice1, vertice2, peso));
        this.quantidadeArestas++;
        return true;
    }

    public void inserirArestaNaoOrientada(Vertice<T> vertice1, Vertice<T> vertice2, int peso) {
        this.inserirAresta(vertice1 , vertice2 , peso);
        this.inserirAresta(vertice2 , vertice1 , peso);
    }

    public void removerAresta(Aresta<T> aresta) {
        Aresta<T> auxAresta = this.buscarAresta(aresta.getVertice1(), aresta.getVertice2());
        if(auxAresta != null)
            this.listaArestas.remove(auxAresta);
        this.quantidadeArestas--;
    }

    public Vertice removerVertice(T obj){
        Vertice<T> vertice = this.buscarVertice(obj);
        if(this.buscarVertice(obj) == null)
            return null;
        
        Aresta<T> auxAresta = this.buscarAresta(vertice);
        int qtdArestasRemovidas = 0;
        
        while(auxAresta != null){
            this.listaArestas.remove(auxAresta);
            qtdArestasRemovidas++;
            auxAresta = this.buscarAresta(vertice);
        }
        this.quantidadeVertices--;
        this.quantidadeArestas -= qtdArestasRemovidas;
        this.listaVertices.remove(vertice);
        return vertice;
    }

    public Vertice<T> buscarVertice(T obj){
        for(Vertice<T> auxVertice : this.listaVertices)
            if(auxVertice.getObj().equals(obj))
                return auxVertice;
        return null;
    }

    public Aresta<T> buscarAresta(Vertice<T> vertice1, Vertice<T> vertice2){
        for(Aresta<T> auxAresta : this.listaArestas)
            if(auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2))
                return auxAresta;
        return null;
    }
    
    public Aresta<T> buscarAresta(T v1, T v2){
        Vertice<T> vertice1 = buscarVertice(v1);
        Vertice<T> vertice2 = buscarVertice(v2);
        for(Aresta<T> auxAresta : this.listaArestas)
            if(auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2))
                return auxAresta;
        return null;
    }

    /** Método que busca uma aresta que possua ligação com um determinado vértice
     * 
     * @param vertice Vertice - Vértice que se deseja procurar as arestas que o
     * mesmo possui.
     * @return Aresta - Aresta que possui relação o vértice passado como parâmetro
     * null - Caso não exista nenhuma aresta relacionada com o vértice passado 
     * por parâmetro.
     */
    public Aresta<T> buscarAresta(Vertice<T> vertice){
        for(Aresta<T> auxAresta : this.listaArestas)
            if(auxAresta.getVertice1().equals(vertice) || auxAresta.getVertice2().equals(vertice))
                return auxAresta;
        return null;
    }

    public List<Aresta<T>> arestasIncidentes(String nome){
        List<Aresta<T>> arestasIncidentes = new ArrayList<>();
        
        this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().getObj().equals(nome) 
                || auxAresta.getVertice2().getObj().equals(nome))).forEachOrdered((auxAresta) -> {
            arestasIncidentes.add(auxAresta);
        });
        
        if(arestasIncidentes.isEmpty())
            return null;
        return arestasIncidentes;
    }

    public AlgoritmoDijkstra menorCaminho(){
        return new AlgoritmoDijkstra(this);
    }
}
