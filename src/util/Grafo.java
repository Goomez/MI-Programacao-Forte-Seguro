
package util;

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

    public Vertice<T> inserir(T obj) {
        if(obj == null)
            return null;
        Vertice<T> vertice = new Vertice<>(obj);
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
    }

    public Vertice removerVertice(T obj){
        Vertice<T> vertice = this.buscarVertice(obj);
        if(this.buscarVertice(obj) == null)
            return null;
        // isso ai em baixo é o msm que o for each
        this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().equals(vertice) 
                || auxAresta.getVertice2().equals(vertice))).forEachOrdered((auxAresta) -> {
            this.listaArestas.remove(auxAresta);
        });
        this.listaVertices.remove(vertice);
        return vertice;
    }

    public Vertice<T> buscarVertice(T obj){
        for(Vertice<T> auxVertice : this.listaVertices)
            if(auxVertice.getObjeto().equals(obj))
                return auxVertice;
        return null;
    }

    public Aresta<T> buscarAresta(Vertice<T> vertice1, Vertice<T> vertice2){
        for(Aresta<T> auxAresta : this.listaArestas)
            if(auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2))
                return auxAresta;
        return null;
    }

    public List<Aresta<T>> arestasIncidentes(String nome){
        List<Aresta<T>> arestasIncidentes = new ArrayList<>();
        
        this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().getObjeto().equals(nome) 
                || auxAresta.getVertice2().getObjeto().equals(nome))).forEachOrdered((auxAresta) -> {
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
