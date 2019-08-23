/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <05/08/2019>
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

/** Classe responsável pela criação e gerenciamento dos nós das árvores AVL 
 * utilizadas no programa.
 *
 * @author Kevin e Allan
 */
public class NodeTree {
    private int key;
    private NodeTree right;
    private NodeTree left;
    private int height;
    
    /** Método construtor utilizado na árvore AVL genérica (int), que inicializa 
     * as variáveis key, left, right e height.
     * 
     * @param key int - Chave do nó que se deseja inserir.
     */
    public NodeTree(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    /** Método que retorna a chave de um nó da árvore genérica (int).
     * 
     * @return int - Chave do nó desejado.
     */
    public int getKey() {
        return key;
    }

    /** Método que modifica a chave de um nó da árvore genérica (int).
     * 
     * @param key int - Chave do nó desejado.
     */
    public void setKey(int key) {
        this.key = key;
    }
    
    /** Método que retorna o filho da direita de um nó da árvore AVL, tanto a 
     * genérica quanto a de imagens.
     * 
     * @return NodeTree - Filho da direita do nó desejado.
     */
    public NodeTree getRight() {
        return right;
    }

    /** Método que modifica o filho da direita de um nó da árvore AVL, tanto a 
     * genérica quanto a de imagens.
     * 
     * @param right NodeTree - Novo nó que se deseja colocar.
     */
    public void setRight(NodeTree right) {
        this.right = right;
    }

    /** Método que retorna o filho da esquerda de um nó da árvore AVL, tanto a 
     * genérica quanto a de imagens.
     * 
     * @return NodeTree - Filho da esquerda do nó desejado.
     */
    public NodeTree getLeft() {
        return left;
    }

    /** Método que modifica o filho da esquerda de um nó da árvore AVL, tanto a 
     * genérica quanto a de imagens.
     * 
     * @param left NodeTree - Novo nó que se deseja colocar.
     */
    public void setLeft(NodeTree left) {
        this.left = left;
    }

    /** Método que retorna a altura da árvore ou da sub-árvore.
     * 
     * @return int - Valor da altura da árvore ou da sub-árvore.
     */
    public int getHeight() {
        return height;
    }

    /** Método que modifica a altura da árvore ou da sub-árvore.
     * 
     * @param height int - Valor da altura que se deseja colocar.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
}