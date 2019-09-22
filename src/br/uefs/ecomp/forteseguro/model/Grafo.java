/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <09/08/2019>
 *
 * Declaro que este código foi elaborado por nós de forma individual e não
 * contém nenhum trecho de código de outro colega ou de outro autor, tais como
 * provindos de livros e apostilas, e páginas ou documentos eletrônicos da
 * Internet. Qualquer trecho de código de outra autoria que uma citação para o
 * não a minha está destacado com autor e a fonte do código, e estou ciente que
 * estes trechos não serão considerados para fins de avaliação. Alguns trechos
 * do código podem coincidir com de outros colegas pois estes foram discutidos
 * em sessões tutorias.
 */
package br.uefs.ecomp.forteseguro.model;

import br.uefs.ecomp.forteseguro.model.Aresta;
import br.uefs.ecomp.forteseguro.model.AlgoritimoDijkstra;
import br.uefs.ecomp.forteseguro.exception.ArestaDuplicadaException;
import br.uefs.ecomp.forteseguro.exception.VerticeDuplicadoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o grafo com os vértices e as arestas.
 *
 * @author Kevin Cerqueira
 * @author Allan Capistrano
 * @param <T> Tipo que a classe irá assumir.
 */
public class Grafo<T> {

    private int quantidadeVertices;
    private int quantidadeArestas;
    private List<Vertice<T>> listaVertices;
    private List<Aresta<T>> listaArestas;

    /**
     * Construtor da classe. Inicializa as váriaveis.
     */
    public Grafo() {
        this.quantidadeVertices = 0;
        this.quantidadeArestas = 0;
        this.listaVertices = new ArrayList<>();
        this.listaArestas = new ArrayList<>();
    }

    /**
     * Retorna a quantidade de vertices do grafo.
     *
     * @return inteiro com a quantidade de vértices
     */
    public int quantidadeVertices() {
        return this.quantidadeVertices;
    }

    /**
     * Retorna a quantidade de arestas do grafo.
     *
     * @return inteiro com a quantidade de arestas.
     */
    public int quantidadeArestas() {
        return this.quantidadeArestas;
    }

    /**
     * Retorna a lista que estão os vértices.
     *
     * @return lista com os vértices do grafo.
     */
    public List<Vertice<T>> getVertices() {
        return this.listaVertices;
    }

    /**
     * Retonr a lista que estão as arestas.
     *
     * @return lista com as arestas do grafo.
     */
    public List<Aresta<T>> getArestas() {
        return this.listaArestas;
    }

    /**
     * Cria um novo objeto do tipo Vertice e o insere na lista de vértice.
     *
     * @param obj Objeto que será armazenado pelo vértice.
     * @param id ID do objeto.
     * @param posX Posição X do vértice.
     * @param posY Posição Y do vértice.
     * @return o novo vértice criado.
     * @throws VerticeDuplicadoException caso o objeto já exista em um vértice
     * também ja existente.
     */
    public Vertice<T> novoVertice(T obj, int id, int posX, int posY) throws VerticeDuplicadoException {
        if (obj == null) {
            return null;
        }
        Vertice<T> vertice = new Vertice<>(obj, id, posX, posY);
        this.listaVertices.add(vertice);
        this.quantidadeVertices++;
        return vertice;
    }

    /**
     * Cria um novo objeto do tipo Vertice e o insere na lista de vértice.
     *
     * @param obj Objeto que será armazenado pelo vértice.
     * @param id ID do objeto.
     * @return o novo vértice criado.
     * @throws VerticeDuplicadoException caso o objeto já exista em um vértice
     * também ja existente.
     */
    public Vertice<T> novoVertice(T obj, int id) throws VerticeDuplicadoException {
        if (obj == null) {
            return null;
        }

        if (this.buscarVertice(obj) != null) {
            throw new VerticeDuplicadoException();
        }

        Vertice<T> vertice = new Vertice<>(obj, id);
        this.listaVertices.add(vertice);
        this.quantidadeVertices++;
        return vertice;
    }

    /**
     * Cria uma nova aresta com os vértices passados por parametro.
     *
     * @param vertice1 Vértice 1 da ligação.
     * @param vertice2 Vértice 2 da ligação.
     * @param peso Peso da ligação.
     * @return true: caso a aresta tenha sido criada e inserida corretamente não
     * havendo duplicatas, false: caso contrário.
     * @throws ArestaDuplicadaException caso a ligação já exista em uma Aresta.
     */
    public boolean criarAresta(Vertice<T> vertice1, Vertice<T> vertice2,
            int peso) throws ArestaDuplicadaException {

        if (vertice1 == null || vertice2 == null) {
            return false;
        }

        if (this.buscarAresta(vertice1, vertice2) != null) {
            throw new ArestaDuplicadaException();
        }

        this.listaArestas.add(new Aresta<>(vertice1, vertice2, peso));
        this.quantidadeArestas++;
        return true;
    }

    /**
     * Cria uma nova aresta não orientada com os vértices passados pelo
     * parametro.
     *
     * @param vertice1 Vértice 1 da ligação.
     * @param vertice2 Vértice 2 da ligação.
     * @param peso Peso da ligação.
     */
    public void criarArestaNaoOrientada(Vertice<T> vertice1, Vertice<T> vertice2, int peso) {
        try {
            this.criarAresta(vertice1, vertice2, peso);
            this.criarAresta(vertice2, vertice1, peso);
        } catch (ArestaDuplicadaException a) {
            a.toString();
        }
    }

    /**
     * Remove uma aresta do grafo.
     *
     * @param aresta Aresta a ser removida.
     */
    public void removerAresta(Aresta<T> aresta) {
        Aresta<T> auxAresta = this.buscarAresta(aresta.getVertice1(), aresta.getVertice2());
        if (auxAresta != null) {
            this.listaArestas.remove(auxAresta);
        }
        this.quantidadeArestas--;
    }

    /**
     * Remove um vértice do grafo.
     *
     * @param obj Objeto contido no vértice a ser removido.
     * @return vertice removido, null caso o objeto não exista
     */
    public Vertice<T> removerVertice(T obj) {
        Vertice<T> vertice = this.buscarVertice(obj);
        if (this.buscarVertice(obj) == null) {
            return null;
        }
        Aresta<T> auxAresta = this.buscarAresta(vertice);
        int qtdArestasRemovidas = 0;

        while (auxAresta != null) {
            this.listaArestas.remove(auxAresta);
            qtdArestasRemovidas++;
            auxAresta = this.buscarAresta(vertice);
        }
        this.quantidadeVertices--;
        this.quantidadeArestas -= qtdArestasRemovidas;
        this.listaVertices.remove(vertice);
        return vertice;
    }

    /**
     * Busca um vértice no grafo a partir de um objeto.
     *
     * @param obj Objeto contido no vértice
     * @return vertice encontrado, null contrário.
     */
    public Vertice<T> buscarVertice(T obj) {
        for (Vertice<T> auxVertice : this.listaVertices) {
            if (auxVertice.getObj().equals(obj)) {
                return auxVertice;
            }
        }
        return null;
    }

    /**
     * Busca uma aresta no grafo a partir de dois vértices.
     *
     * @param vertice1 Vértice 1 da ligação da aresta.
     * @param vertice2 Vértice 2 da ligação da aresta.
     * @return aresta encontrada, null caso contrário.
     */
    public Aresta<T> buscarAresta(Vertice<T> vertice1, Vertice<T> vertice2) {
        for (Aresta<T> auxAresta : this.listaArestas) {
            if (auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2)) {
                return auxAresta;
            }
        }
        return null;
    }

    /**
     * Busca uma aresta no grafo a partir de dois objetos.
     *
     * @param v1 Objeto 1 do Vertice 1 da ligação.
     * @param v2 Objeto 2 do Vertice 2 da ligação.
     * @return aresta encontrada, null caso contrário.
     */
    public Aresta<T> buscarAresta(T v1, T v2) {
        Vertice<T> vertice1 = buscarVertice(v1);
        Vertice<T> vertice2 = buscarVertice(v2);
        for (Aresta<T> auxAresta : this.listaArestas) {
            if (auxAresta.getVertice1().equals(vertice1) && auxAresta.getVertice2().equals(vertice2)) {
                return auxAresta;
            }
        }
        return null;
    }

    /**
     * Método que busca uma aresta que possua ligação com um determinado vértice
     *
     * @param vertice Vertice - Vértice que se deseja procurar as arestas que o
     * mesmo possui.
     * @return Aresta - Aresta que possui relação o vértice passado como
     * parâmetro null - Caso não exista nenhuma aresta relacionada com o vértice
     * passado por parâmetro.
     */
    public Aresta<T> buscarAresta(Vertice<T> vertice) {
        for (Aresta<T> auxAresta : this.listaArestas) {
            if (auxAresta.getVertice1().equals(vertice) || auxAresta.getVertice2().equals(vertice)) {
                return auxAresta;
            }
        }
        return null;
    }

    /**
     * Retorna uma lista com arestas incidentes do vértice que o objeto passado
     * por parametro se encontra.
     *
     * @param obj Objeto contido no vértice a ser procurado.
     * @return lista com as arestas incidentes do vértice, null caso contrário.
     */
    public List<Aresta<T>> arestasIncidentes(T obj) {
        List<Aresta<T>> arestasIncidentes = new ArrayList<>();

        this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().getObj().equals(obj)
                || auxAresta.getVertice2().getObj().equals(obj))).forEachOrdered((auxAresta) -> {
            arestasIncidentes.add(auxAresta);
        });

        if (arestasIncidentes.isEmpty()) {
            return null;
        }
        return arestasIncidentes;
    }

    /**
     * Retorna uma lista com arestas incidentes do vértice que o objeto passado
     * por parametro se encontra.
     *
     * @param vertice Vértice a ser procurado.
     * @return lista com as arestas incidentes do vértice, null caso contrário.
     */
    public List<Aresta<T>> arestasIncidentes(Vertice<T> vertice) {
        List<Aresta<T>> arestasIncidentes = new ArrayList<>();

        this.listaArestas.stream().filter((auxAresta) -> (auxAresta.getVertice1().equals(vertice)
                || auxAresta.getVertice2().equals(vertice))).forEachOrdered((auxAresta) -> {
            arestasIncidentes.add(auxAresta);
        });

        if (arestasIncidentes.isEmpty()) {
            return null;
        }
        return arestasIncidentes;
    }

    /**
     * Retorna a excecução do Algoritimo de Dijkstra.
     * @return a excecução do Dijkstra.
     */
    public AlgoritimoDijkstra executarDijkstra() {
        return new AlgoritimoDijkstra(this);
    }
}
