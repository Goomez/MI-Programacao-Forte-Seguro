/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <29/07/2019>
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

/**
 * Classe responsável por armazenar e gerenciar o objeto que será armazenado
 * nesta classe do id Vértice.
 *
 * @author Kevin Cerqueira.
 * @author Allan Capistrano.
 * @param <T> Tipo que a classe irá assumir.
 */
public class Vertice<T> {

    private T obj;
    private int grauVertice;
    private int posX;
    private int posY;
    private int id;

    /**
     * Construtor da classe. Inicializa as váriaveis.
     *
     * @param obj Objeto que será armazenado na classe.
     * @param id ID do objeto.
     * @param posX Posição X do vértice no plano.
     * @param posY Posição Y do vértice no plano.
     */
    public Vertice(T obj, int id, int posX, int posY) {
        this.obj = obj;
        this.grauVertice = 0;
        this.posX = posX;
        this.posY = posY;
        this.id = id;
    }

    /**
     * Construtor da classe. Inicializa as váriaveis.
     *
     * @param obj Objeto que será armazenado na classe.
     * @param id ID do objeto.
     */
    public Vertice(T obj, int id) {
        this.obj = obj;
        this.id = id;
        this.grauVertice = 0;
    }

    /**
     * Retorna no objeto do vértice.
     *
     * @return objeto armazenado no vértice
     */
    public T getObj() {
        return obj;
    }

    /**
     * Atualiza o objeto do vértice.
     *
     * @param obj Novo objeto.
     */
    public void setObj(T obj) {
        this.obj = obj;
    }
    
    /**
     * Retorna o grau do vértice.
     *
     * @return inteiro com o grau do vértice.
     */
    public int getGrauVertice() {
        return grauVertice;
    }

    /**
     * Atualiza o grau do vértice.
     *
     * @param grauVertice Novo grau.
     */
    public void setGrauVertice(int grauVertice) {
        this.grauVertice = grauVertice;
    }

    /**
     * Retorna a posição X do vértice.
     *
     * @return inteiro com a posição X do vértice.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Atualiza a posição X do vértice.
     *
     * @param posX Nova posição X.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Retorna a posição Y do vértice.
     *
     * @return inteiro com a posição Y do vértice.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Atualiza a posição Y do vértice.
     *
     * @param posY Nova posição Y do vértice.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Retorna o ID do vértice.
     *
     * @return inteiro com o ID do vértice.
     */
    public int getId() {
        return id;
    }

    /**
     * Atualiza o ID do vértice.
     *
     * @param id Novo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metódo equals da classe. Verifica se dois objetos do tipo Vertice são
     * iguais.
     *
     * @param vertice Vertice a ser verificado.
     * @return true: caso os dois Vertices sejam iguais, false: caso contrário.
     */
    public boolean equals(Vertice vertice) {
        return this.obj.equals(vertice.getObj())
                && this.grauVertice == vertice.getGrauVertice()
                && this.id == vertice.getId();
    }
}
