package br.uefs.ecomp.forteseguro.model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe responsável por fazer o Algoritmo de Dijkstra (calcular o menor
 * caminho entre dois vértices).
 *
 * @author Kevin Cerqueira.
 * @author Allan Capistrano.
 * @param <T> Tipo que a classe irá assumir.
 */
public class AlgoritimoDijkstra<T> {

    private final List<Aresta<T>> listaArestas;
    private Set<Vertice<T>> verticesVisitados;
    private Set<Vertice<T>> verticesNaoVisitados;
    private List<List<Vertice<T>>> listaResultados;
    private Map<Vertice<T>, ArrayList<Vertice<T>>> antecessores;
    private Map<Vertice<T>, Integer> pesos;

    /**
     * Construtor da classe. Inicializa as váriaveis.
     *
     * @param grafo Grafo ao qual o Dijkstra irá rodar.
     */
    public AlgoritimoDijkstra(Grafo<T> grafo) {
        this.listaArestas = new ArrayList<>(grafo.getArestas());
        this.listaResultados = new ArrayList<>();
        this.verticesVisitados = new HashSet<>();
        this.verticesNaoVisitados = new HashSet<>();
        this.pesos = new HashMap<>();
        this.antecessores = new HashMap<>();
    }

    /**
     * Executa o Algoritimo. Calcula todos os menores caminhos do ponto passado
     * por parametro para os demais do grafo.
     *
     * @param vertice Vértice do qual o Dijkstra irá tomar como princípio.
     */
    public void calcularMenoresCaminhos(Vertice<T> vertice) {
        this.verticesNaoVisitados.add(vertice);
        this.pesos.put(vertice, 0);
        while (this.verticesNaoVisitados.size() > 0) {
            Vertice<T> auxVertice = this.getVerticeComMenorPeso(this.verticesNaoVisitados);
            this.verticesVisitados.add(auxVertice);
            this.verticesNaoVisitados.remove(auxVertice);
            encontrarMenorDistancia(auxVertice);
        }
    }

    /**
     * Retorna todos os caminhos mínimos do vértice principal ao vértice passado
     * por parametro
     *
     * @param listVertices Lista com caminhos mínimos, caso o algoritimo esteja
     * sendo executado pela segunda vez.
     * @param vertice Vértice ao qual o algoritimo quer chegar.
     * @return Lista com listas com os menores caminhos possíveis.
     */
    public List<List<Vertice<T>>> getDijkstra(ArrayList<Vertice<T>> listVertices, Vertice<T> vertice) {
        if (listVertices == null) {
            listVertices = new ArrayList<>();
        }
        
        ArrayList<Vertice<T>> caminho = new ArrayList<>(listVertices);
        Vertice<T> auxVertice = vertice;
        ArrayList<Vertice<T>> auxListVertice;

        if (this.antecessores.get(auxVertice) == null) {
            return null;
        }

        caminho.add(auxVertice);
        while (this.antecessores.get(auxVertice) != null) {
            auxListVertice = this.antecessores.get(auxVertice);
            if (auxListVertice.size() > 1) {
                for (int i = 1; i < auxListVertice.size(); i++) {
                    auxVertice = auxListVertice.get(i);
                    this.getDijkstra(caminho, auxVertice);
                }
            }
            auxVertice = auxListVertice.get(0);
            caminho.add(auxVertice);
        }
        Collections.reverse(caminho);
        this.listaResultados.add(caminho);
        return this.listaResultados;
    }
    /**
     * Método intermediario do algoritimo. Encontra a menor distancia entre os
     * vértices adjacentes do vértice passado por paramentro.
     * @param vertice Vértice ao qual será encontrado a menor distancia entre as
     * suas ligações.
     */
    private void encontrarMenorDistancia(Vertice<T> vertice) {
        List<Vertice<T>> verticesAdjacentes = this.getAdjacentes(vertice);
        ArrayList<Vertice<T>> auxList;
        for (Vertice<T> auxVertice : verticesAdjacentes) {
            if (this.getMenorDistancia(auxVertice) > this.getMenorDistancia(vertice)
                    + this.getPesoDaLigacao(vertice, auxVertice)) {
                this.pesos.put(auxVertice, this.getMenorDistancia(vertice)
                        + this.getPesoDaLigacao(vertice, auxVertice));
                auxList = new ArrayList<>();
                auxList.add(vertice);
                this.antecessores.put(auxVertice, auxList);
                this.verticesNaoVisitados.add(auxVertice);
            } else if (this.getMenorDistancia(auxVertice) == this.getMenorDistancia(vertice)
                    + this.getPesoDaLigacao(vertice, auxVertice)) {
                this.antecessores.get(auxVertice).add(vertice);
            }
        }

    }
    
    /**
     * Retorna o peso da ligação entre dois vértices.
     * @param vertice1 Vértice 1 da ligação.
     * @param vertice2 Vértice 2 da ligação.
     * @return inteiro com o peso da ligação, 
     */
    private int getPesoDaLigacao(Vertice<T> vertice1, Vertice<T> vertice2) {
        for (Aresta auxAresta : this.listaArestas) {
            if (auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2)) {
                return auxAresta.getPeso();
            }
        }
        throw new RuntimeException("Aresta não encontrada");
    }
    
    /**
     * Retorna uma lista com os adjacentes de um determinado vértice.
     * @param vertice Vértice ao qual será pego os adjacentes.
     * @return lista com os vértice adjacentes do passado pelo parametro.
     */
    private List<Vertice<T>> getAdjacentes(Vertice<T> vertice) {
        List<Vertice<T>> adjacentes = new ArrayList<>();

        this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().equals(vertice)
                && !foiVisitado(auxAresta.getVertice2()))).forEachOrdered((auxAresta) -> {
            adjacentes.add(auxAresta.getVertice2());
        });
        return adjacentes;
    }
    
    /**
     * Retorna o vértice com o menor peso possível.
     * @param vertices Set com os vértices que serão pegos os pesos.
     * @return Vértice com o menor peso entre os passados pelo parametro.
     */
    private Vertice<T> getVerticeComMenorPeso(Set<Vertice<T>> vertices) {
        Vertice<T> minimo = null;
        for (Vertice<T> auxVertice : vertices) {
            if (minimo == null) {
                minimo = auxVertice;
            } else if (this.getMenorDistancia(auxVertice) < this.getMenorDistancia(minimo)) {
                minimo = auxVertice;
            }
        }
        return minimo;
    }
    
    /**
     * Retorna o menor peso possível.
     * @param vertice Vértice a ser pego o peso.
     * @return inteiro com o peso.
     */
    private int getMenorDistancia(Vertice<T> vertice) {
        Integer distancia = pesos.get(vertice);
        if (distancia == null) {
            return Integer.MAX_VALUE;
        } else {
            return distancia;
        }
    }
    
    /**
     * Verifica se o vértice já foi verificado. Buscando na lista de vertices
     * verificados.
     * @param vertice Vértice a ser verificado.
     * @return true: caso ele já tenha sido verificado, false: caso contrário.
     */
    private boolean foiVisitado(Vertice<T> vertice) {
        return verticesVisitados.contains(vertice);
    }
}
