
package br.uefs.ecomp.forteseguro.util;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Kevin Cerqueira.
 * @author Allan Capistrano.
 * @param <T>
 */
public class AlgoritmoDijkstra<T>{

    private final List<Aresta<T>> listaArestas;
    private List<List<Vertice<T>>> listaResultados;
    private Set<Vertice<T>> verticesVisitados;
    private Set<Vertice<T>> verticesNaoVisitados;
    private Map<Vertice<T>, ArrayList<Vertice<T>>> antecessores;
    private Map<Vertice<T>, Integer> peso;

    public AlgoritmoDijkstra(Grafo<T> grafo) {
        this.listaArestas = new ArrayList<>(grafo.getArestas());
    }

    public void executar(Vertice<T> raiz) {
        this.listaResultados = new ArrayList<>();
        this.verticesVisitados = new HashSet<>();
        this.verticesNaoVisitados = new HashSet<>();
        this.peso = new HashMap<>();
        this.antecessores = new HashMap<>();
        this.peso.put(raiz, 0);
        this.verticesNaoVisitados.add(raiz);

        while (this.verticesNaoVisitados.size() > 0) {
            Vertice auxVertice = this.getVerticeDaMenorDistancia(this.verticesNaoVisitados);
            this.verticesVisitados.add(auxVertice);
            this.verticesNaoVisitados.remove(auxVertice);
            acharDistanciaMinima(auxVertice);
        }
    }

    private void acharDistanciaMinima(Vertice<T> vertice) {
        List<Vertice> verticesAdjacentes = this.getVizinhos(vertice);
        ArrayList<Vertice<T>> auxList;
        for (Vertice<T> auxVertice : verticesAdjacentes) {
            if (this.getMenorDistancia(auxVertice) > this.getMenorDistancia(vertice) + this.getDistancia(vertice, auxVertice)) {
                this.peso.put(auxVertice, this.getMenorDistancia(vertice) + this.getDistancia(vertice, auxVertice));
                auxList = new ArrayList<>();
                auxList.add(vertice);
                this.antecessores.put(auxVertice, auxList);
                this.verticesNaoVisitados.add(auxVertice);
            }else if(this.getMenorDistancia(auxVertice) == this.getMenorDistancia(vertice) + this.getDistancia(vertice, auxVertice))
                this.antecessores.get(auxVertice).add(vertice);
        }

    }

    private int getDistancia(Vertice<T> vertice1, Vertice<T> vertice2) {
        for (Aresta auxAresta : this.listaArestas) 
            if (auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2))
                return auxAresta.getPeso();
        throw new RuntimeException("Should not happen");
    }

    private List<Vertice> getVizinhos(Vertice vertice) {
            List<Vertice> adjacentes = new ArrayList<>();

            this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().equals(vertice) 
                    && !isVisitado(auxAresta.getVertice2()))).forEachOrdered((auxAresta) -> {
                adjacentes.add(auxAresta.getVertice2());
            });

            //isso ai em cima é o mesmo disso aqui em baixo
            /*
            for(Aresta<T> auxAresta : this.listaArestas) {
                if (auxAresta.getVertice1().equals(vertice) && !isVisitado(auxAresta.getVertice2()))
                    adjacentes.add(auxAresta.getVertice2());
            }
             */

            return adjacentes;
    }

    private Vertice<T> getVerticeDaMenorDistancia(Set<Vertice<T>> vertices) {
        Vertice<T> minimo = null;
        for (Vertice auxVertice : vertices) {
            if (minimo == null) 
                minimo = auxVertice;
            else
                if (this.getMenorDistancia(auxVertice) < this.getMenorDistancia(minimo))
                    minimo = auxVertice;
        }
        return minimo;
    }

    private boolean isVisitado(Vertice<T> vertice) {
        return verticesVisitados.contains(vertice);
    }

    private int getMenorDistancia(Vertice<T> vertice) {
        Integer distancia = peso.get(vertice);
        if (distancia == null)
            return Integer.MAX_VALUE;
        else
            return distancia;
    }


    public List<List<Vertice<T>>> getCaminho(ArrayList<Vertice<T>> listVertices, Vertice<T> vertice) {
        if(listVertices == null)
            listVertices = new ArrayList<>();
        ArrayList<Vertice<T>> caminho = new ArrayList<>(listVertices);
        Vertice<T> auxVertice = vertice;
        ArrayList<Vertice<T>> auxListVertice;

        if (this.antecessores.get(auxVertice) == null) 
            return null;

        caminho.add(auxVertice);
        while (this.antecessores.get(auxVertice) != null) {

            auxListVertice = this.antecessores.get(auxVertice);

            if(auxListVertice.size() > 1){
                for(int i = 1; i < auxListVertice.size(); i++){
                    auxVertice = auxListVertice.get(i);
                    this.getCaminho(caminho, auxVertice);
                }
            }
            auxVertice = auxListVertice.get(0);
            caminho.add(auxVertice);
        }

        Collections.reverse(caminho);
        this.listaResultados.add(caminho);
        return this.listaResultados;
    }

} 
