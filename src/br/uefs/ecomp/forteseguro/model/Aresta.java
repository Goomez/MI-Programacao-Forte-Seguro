/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <29/07/2019>
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
package br.uefs.ecomp.forteseguro.model;

/**
 * Classe responsável por gerenciar as ligações entre os vértices.
 * @author Kevin Cerqueira
 * @author Allan Capistrano
 * @param <T> Tipo que a classe irá assumir.
 */
public class Aresta<T>{
    private Vertice<T> vertice1;
    private Vertice<T> vertice2;
    private int peso;
    
    /**
     * Construtor da classe. Inicializa as variáveis.
     * @param vertice1 Vértice 1 da ligação da aresta.
     * @param vertice2 Vértice 2 da ligação da aresta.
     * @param peso Peso da ligação entre os vértices.
     */
    public Aresta(Vertice<T> vertice1, Vertice<T> vertice2, int peso){
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    /**
     * Retorna o Vértice 2 da ligação.
     * @return objeto do tipo Vertice.
     */
    public Vertice<T> getVertice2() {
        return this.vertice2;
    }
    
    /**
     * Atualiza o Vértice 2 da ligação.
     * @param vertice2 novo vértice.
     */
    public void setVertice2(Vertice<T> vertice2) {
        this.vertice2 = vertice2;
    }
    
    /**
     * Retorna o Vértice 1 da ligação.
     * @return objeto do tipo Vertice.
     */
    public Vertice<T> getVertice1() {
        return this.vertice1;
    }

    /**
     * Atualiza o Vértice 1 da ligação.
     * @param vertice1 novo vértice.
     */
    public void setVertice1(Vertice<T> vertice1) {
        this.vertice1 = vertice1;
    }
    /**
     * Retorna o peso da ligação.
     * @return inteiro com o peso da ligação.
     */
    public int getPeso() {
        return this.peso;
    }
    
    /**
     * Atualiza o peso da ligação.
     * @param peso novo peso da ligação.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    /**
     * Método equals da classe. Verifica se dois objetos do tipo Aresta são
     * iguais.
     * @param aresta Aresta a ser verificada.
     * @return true: caso as duas arestas forem iguais, false: caso contrário.
     */
    public boolean equals(Aresta<T> aresta){
        return this.vertice1.equals(aresta.getVertice1()) &&
                this.vertice2.equals(aresta.getVertice2()) &&
                this.peso == aresta.getPeso();
    }
}
