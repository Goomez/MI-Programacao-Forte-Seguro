/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano>
 * Data:  <11/08/2019>
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package br.uefs.ecomp.forteseguro.util;

/**
 * Classe responsável por fazer todos os procedimentos de uma lista encadeada, 
 * adcionando, removendo e fazendo outras funções com nós.
 *
 * @author Kevin
 */
public class MyLinkedList<T>{
    protected Link<T> firstLink;
    protected Link<T> lastLink;
    protected int size = 0;
    
    public MyLinkedList(){
        this.firstLink = null;
        this.lastLink = null;
    }
    
    /**
     * Retorna o size da lista, ou seja, a quantidades de nós.
     * @return Quantidade de nós em inteiro, onde 0 significa que a lista está 
     * vazia.
     */
    public int size() {
        return size;
    }
    
    /**
     * Adiciona um novo objeto a um nó que será adicionado no final da lista.
     * @param obj novo objeto a ser adcionado ao nó.
     */
    public void add(T obj) {
        Link newLink = new Link(obj);
        if(firstLink == null){
            this.firstLink = newLink;
            this.lastLink = newLink;
        }
        else{
            this.lastLink.setNext(newLink);
            this.lastLink = newLink;
        }
        size++;
    }
    
    /**
     * Adiciona um novo objeto ao um novo nó que será adicionado a uma 
     * determinada posição da lista.
     * @param pos posição da lista que o novo nó será adcionado.
     * @param obj objeto que será adicionado ao novo nó.
     * @return true, se o nó for adicionado com sucesso ou false, caso pos 
     * (posição referente a lista) seja maior que o size da lista ou menor 
     * que 0 (zero).
     */
    public boolean add(int pos, T obj) {
        Link novoNo = new Link(obj);
        if(pos > size || pos < 0)
            return false;
        else if(pos == 0){
            novoNo.setNext(firstLink);
            firstLink = novoNo;
            size++;
            return true;
        }else{
            Link current = firstLink;
            Link previous = current;
            
            for(int i = 0; i < pos; i++){
                previous = current;
                current = current.getNext();
            }
            previous.setNext(novoNo);
            novoNo.setNext(current);
            size++;
            return true;
        }
    }
    
    /**
     * Remove um nó e o objeto contido nele que está em uma determiada posição 
     * na lista.
     * @param pos posição do nó a ser removido.
     * @return Objeto contido no nó removido ou null caso a lista esteja vazia 
     * ou se pos (posição referente a lista) for menor que 0 (zero) ou maior que
     * o size da lista.
     */
    public T remove(int pos) {
        if(firstLink == null || pos > size - 1 || pos < 0)
            return null;
        else if(pos == 0){
            T obj = firstLink.getObj();
            firstLink = firstLink.getNext();
            size--;
            return obj;
        }else{
            Link<T> current = firstLink;
            Link<T> previous  = current;
            
            for(int i = 0; i < pos; i++){
                previous = current;
                current = current.getNext();
            }
            previous.setNext(current.getNext());
            size--;
            return current.getObj();
        }
    }
    
    /**
     * Retorna um objeto em uma determinada posição na lista, sem remove-lo.
     * @param index posição do objeto a ser retornado.
     * @return Objeto recuperado da lista, ou null caso a lista esteja vazia ou 
     * se index (posição referente a lista) for maior que o size da lista ou 
     * menor que 0 (zero).
     */
    public T get(int index) {
        if(firstLink == null || index > size -1 || index<0) 
            return null;
        else if(index == 0)
            return firstLink.getObj();
        else{
            Link<T> aux = firstLink;
            for(int i = 0; i < index - 1; i++)
                aux = aux.getNext();
            return aux.getNext().getObj();
        }
    }
    
    /**
     * Indica se a lista estiver vazia ou não.
     * @return true, caso a lista esteja vazia ou false, caso não esteja.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Retorna um novo iterador na primeira posição da lista.
     * @return Um novo iterator, referenciando o firstLink nó da lista
     */
    public MyIterator iterator() {
       return new MyIterator(firstLink);
    }
}
