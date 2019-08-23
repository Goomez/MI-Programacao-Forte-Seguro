/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <11/08/2019>
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import br.uefs.ecomp.forteseguro.exception.EdgeNotFoundException;
import br.uefs.ecomp.forteseguro.exception.DuplicateKeyException;

/**
 *
 * @author Kevin
 * @param <T>
 */
public class MyGraph<T>{

    private static final long serialVersionUID = 1L;

    /**
     * HashMap de adjacencias. <br>
     * Sendo que as chaves do primeiro <b>HashMap</b> representam os vertices do
     * grafo.      *
     * Não obstante, as chaves do segundo HashMap representam os vertices
     * adjacentes ao vertice do primeiro HashMap, e o valor as edges que ligam
     * os vertices.
     */
    private HashMap<T, HashMap<T, Edge<T>>> adjacencias;

    private List<Edge<T>> edges;

    public MyGraph() {
        adjacencias = new HashMap<>();
        edges = new ArrayList<>();
    }
    
    public MyGraph(ArrayList a) {
        this.edges = a;
    }
    
    // Vertices
    public void addVertice(T object) throws DuplicateKeyException {
        if (adjacencias.containsKey(object)) {
            throw new DuplicateKeyException("Duplicação de vertice não permitida.");
        }
        adjacencias.put(object, new HashMap<>());
    }

    @SuppressWarnings("rawtypes")
    public Iterator vertices() {
        if (!adjacencias.isEmpty()) {
            return adjacencias.keySet().iterator();
        }
        return null;
    }

    public int numberVertices() {
        return adjacencias.keySet().size();
    }
    
    public void removeVertice(T object) {
        if (!adjacencias.get(object).isEmpty()) {
            for (Edge<T> Edge : adjacencias.get(object).values()) {
                if (Edge != null) {
                    edges.remove(Edge);
                }
            }
            adjacencias.remove(object);
        }
    }

    // edges.
    public void addEdge(T origem, T destino, double peso) {
        if (this.adjacencias.containsKey(origem) && this.adjacencias.containsKey(destino)) {	// se existem os limites da Edge
            Edge<T> Edge = new Edge<>(origem, destino, peso);
            if (!this.edges.contains(Edge)) { // se a Edge ja nao existe
                this.edges.add(Edge);  // adiciona no array de edges
                this.adjacencias.get(origem).put(destino, Edge);  // adiciona no map de adjacencia (Ida)
                this.adjacencias.get(destino).put(origem, Edge);	//adiciona no map de adjacencia (Volta).
            }
        }
    }

    public Edge<T> getEdge(T origem, T destino) throws EdgeNotFoundException {
        for (Edge<T> Edge : edges) {
            if (Edge.getOrigin().equals(origem) && Edge.getDestiny().equals(destino)) {
                return Edge;
            }
        }
        throw new EdgeNotFoundException("Aresta não existe.");
    }
    
    public boolean containsEdge(T origem, T destino){
        for (Edge<T> Edge : edges) {
            if (Edge.getOrigin().equals(origem) && Edge.getDestiny().equals(destino)) {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("rawtypes")
    public Iterator edges() {
        if (!edges.isEmpty()) {
            return edges.iterator();
        }
        return null;
    }

    public int numberedges() {
        return this.edges.size();
    }

    public void removeEdge(Edge<T> Edge) throws EdgeNotFoundException {
        if (this.edges.contains(Edge)) {
            edges.remove(Edge);
            adjacencias.get(Edge.getOrigin()).remove(Edge.getDestiny(), Edge);
            adjacencias.get(Edge.getDestiny()).remove(Edge.getOrigin(), Edge);
        }
        throw new EdgeNotFoundException("Edge não existe.");
    }

    /**
     * Função que retorna os vértices adjacentes ao vertice passado como
     * parâmetro, se não existir nenhum vértice adjacente ele retorna
     * @param Edge
     * @return HashMap     *
     */
    public HashMap<T, Edge<T>> getAdjacentes(T Edge) {
        return (this.adjacencias.get(Edge).isEmpty() ? null : this.adjacencias.get(Edge));
    }
}    
