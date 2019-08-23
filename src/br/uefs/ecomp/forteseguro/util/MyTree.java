/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano>
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

/** Classe que cria uma árvore AVL genérica que recebe chaves do tipo inteiro, 
 * e guarda cada informação em um nó, não sendo possível a árvore possuir dois 
 * nós com chaves iguais. Além de conter métodos que adicionam e removem os nós, 
 * fazem as rotações para permitir o auto-balanceamento da árvore e de percorrer 
 * todos os nós da árvore em uma ordem específica.
 *
 * @author Kevin e Allan
 */
public class MyTree {
    protected NodeTree root;
    
    /**
     * Método construtor padrão, que não recebe parâmetros e instancia a raiz 
     * da árvore como nula.
     */
    public MyTree(){
          this.root = null; 
    }
    
    /** Método que indica se a árovre está vazia.
     * 
     * @return true - caso a árvroe esteja vazia; false - caso contrário.
     */
    public boolean isEmpty(){
        return root == null;
    }
    
    /**
     * Método que chama o método de contar a quantidade de nós atual da árvore.
     * @return int - Quantidade de nós que a árvore possui.
     */
    public int countNodes(){
        return countNodes(this.root);
    }
    
    /** Método que indica a quantidade de nós que a árvore possui.
     * 
     * @param node NodeTree - Raiz da árvore ou da subárvore.
     * @return int - Quantidade de nós que a árvore possui.
     */
    private int countNodes(NodeTree node){
        int contador = 0;
        
        if(node != null){
            contador += countNodes(node.getLeft());
            contador += countNodes(node.getRight());
            contador++;
        }
        return contador;
    }
  
    /** Método que retorna a altura atual da árvore AVL ou de uma sub-árvore.
     * 
     * @param N NodeTree - Nó raiz da árvore ou da sub-árvore que se deseja 
     * saber a altura
     * @return int - Tamanho atual da árvore ou sub-árvore desejada.
     */
    public int altura(NodeTree N) { 
        if (N == null) 
            return 0; 
  
        return N.getHeight(); 
    } 
  
    /** Método que retorna o inteiro que possui maior valor.
     * obs: os números inteiro não podem ser iguais.
     * 
     * @param a int - Número inteiro.
     * @param b int - Número inteiro.
     * @return int - O maior número entre os dois passados no parâmetro.
     */
    public int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    /** Método que faz uma rotação simples à direita na árvore.
     * 
     * @param oldRoot NodeTree - Nó raiz da árvore ou sub-árvore.
     * @return NodeTree - Nova raiz da árvore 
     */
    public NodeTree rotacaoDireita(NodeTree oldRoot) { 
        NodeTree newRoot = oldRoot.getLeft(); 
        NodeTree rightSon = newRoot.getRight(); 
  
        // rotação
        this.root = newRoot;
        newRoot.setRight(oldRoot); 
        oldRoot.setLeft(rightSon); 
  
        // atualiza altura
        oldRoot.setHeight(max(altura(oldRoot.getLeft()), 
                altura(oldRoot.getRight())) + 1); 
        newRoot.setHeight(max(altura(newRoot.getLeft()), 
                altura(newRoot.getRight())) + 1); 
  
        // retorna nova raiz
        return newRoot;  
    } 
  
    /** Método que faz uma rotação oldRoot à esquerda na árvore.
     * 
     * @param oldRoot NodeTree - Nó raiz da árvore ou sub-árvore.
     * @return NodeTree - Nova raiz da árvore 
     */
    public NodeTree rotacaoEsquerda(NodeTree oldRoot) {
        NodeTree newRoot = oldRoot.getRight(); 
        NodeTree leftSon = newRoot.getLeft(); 
  
        // rotação
        this.root = newRoot;
        newRoot.setLeft(oldRoot); 
        oldRoot.setRight(leftSon); 
  
        // atualiza altura
        oldRoot.setHeight(max(altura(oldRoot.getLeft()), 
                altura(oldRoot.getRight())) + 1); 
        newRoot.setHeight(max(altura(newRoot.getLeft()), 
                altura(newRoot.getRight())) + 1); 
  
        // retorna nova raiz 
        return newRoot;
    }
    
    /** Método que faz uma rotação dupla à esquerda na árvore.
     * 
     * @param r NodeTree - Nó raiz da árvore ou sub-árvore.
     * @return NodeTree - Nova raiz da árvore 
     */
    public NodeTree rotacaoRL(NodeTree r){
        /*Rotação simples à direita na sub-árvore da direita*/
        rotacaoDireita(r.getRight());
        /*Rotação simples à esquerda na árvore original*/
        return rotacaoEsquerda(r);
    }
    
    /** Método que faz uma rotação dupla à direita na árvore.
     * 
     * @param r NodeTree - Nó raiz da árvore ou sub-árvore.
     * @return NodeTree - Nova raiz da árvore 
     */
    public NodeTree rotacaoLR(NodeTree r){
        /*Rotação simples à esquerda na sub-árvore da esquerda*/
        rotacaoEsquerda(r.getLeft());
        /*Rotação simples à direita na árvore original*/
        return rotacaoDireita(r);
    }
    
    /** Método que encontra o nó que possui a menor chave.
     * 
     * @param node NodeTree - Raiz da árvore ou sub-árvore.
     * @return NodeTree - Nó que possui a menor chave.
     */
    public NodeTree menorValor(NodeTree node){
        NodeTree current = node;
        
        while(current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }
  
    /** Método que verifica o balanceamento da árvore utilizando a altura das 
     * sub-árvores do filho da esquerda e da direita.
     * 
     * @param N NodeTree - Raiz da árvore ou sub-árvore.
     * @return int - Valor do balanceamento.
     */
    public int getBalanceamento(NodeTree N) { 
        if (N == null) 
            return 0; 
  
        return altura(N.getLeft()) - altura(N.getRight()); 
    } 
  
    /** Método que chama o método de adicionar novos nós na árvore.
     * 
     * @param key int - Chave que deseja inserir na árvore.
     * @return NodeTree - Raiz da árvore ou sub-árvore.
     */
    public NodeTree inserir(int key){
        return root = this.inserir(root, key);
    }
    
    /** Método que adiciona novos nós na árvore.
     * 
     * @param node NodeTree - Nó raiz da árvore ou da sub-árvore.
     * @param key int - Chave que deseja inserir na árvore.
     * @return NodeTree - Raiz da árvore.
     */
    private NodeTree inserir(NodeTree node, int key) { 
        
        if (node == null) 
            return (new NodeTree(key)); 
  
        if (key < node.getKey()) 
            node.setLeft(inserir(node.getLeft(), key)); 
        else if (key > node.getKey()) 
            node.setRight(inserir(node.getRight(), key)); 
        else // duplicação de chaves não é permitida
            return node; 
  
        /*Atualiza altura*/
        node.setHeight(1 + max(altura(node.getLeft()), 
                              altura(node.getRight()))); 
  
        /*Obtém o fator de balanceamento deste nó pai*/
        int balance = getBalanceamento(node); 
  
        // Se o nó está desbalanceado, então existe 4 casos
        
        // Simples à direita
        if (balance > 1 && key < node.getLeft().getKey()) 
            return rotacaoDireita(node); 
  
        // Simples à esquerda
        if (balance < -1 && key > node.getRight().getKey()) 
            return rotacaoEsquerda(node); 
  
        // Dupla à direita (esquerda - diteita)
        if (balance > 1 && key > node.getLeft().getKey())
            return rotacaoLR(node);
  
        // Dupla à esquerda (direita - esquerda)
        if (balance < -1 && key < node.getRight().getKey())
            return rotacaoRL(node);
  
        return node; 
    } 
    
    /** Método que chama o método de deletar uma chave da árvore.
     * 
     * @param key int - Chave que se deseja remover.
     */
    public void delete(int key){
        delete(this.root, key);
    }
    
    /** Método que remove uma chave da árvore.
     * 
     * @param node NodeTree - Nó raiz da árvore ou sub-árvore.
     * @param key int - Chave que se deseja remover.
     * @return NodeTree - Raiz da árvore ou sub-árvore.
     */
    private NodeTree delete(NodeTree node, int key) {
        /*Se a árvore estiver vazia*/
        if(node == null)
            return node;
      /*Se o elemento que quer remover tem chave menor do que a do nó que está*/
        if(key < node.getKey())
            node.setLeft(delete(node.getLeft(), key));
      /*Se o elemento que quer remover tem chave maior do que a do nó que está*/
        else if(key > node.getKey())
            node.setRight(delete(node.getRight(), key));
    /*Se o elemento que quer remover tem chave igual a do que a do nó que está*/
        else {
            /*Caso tenha um ou nenhum filho*/
            if(node.getLeft() == null || node.getRight() == null){
                /*Se tiver apenas um filho e ele está na esquerda*/
                if(node.getLeft() != null)
                    node = node.getLeft();
                /*Se tiver apenas um filho e ele está na direita*/
                else
                    node = node.getRight();
            }
            /*Caso tenha dois filhos*/
            else{
                NodeTree temp = menorValor(node.getRight()); /*Procura o menor valor dentre 
                                                    os que estão na direita*/
                
                /*Troca o valor da chave do nó que deseja remover pela chave do 
                nó que é o menor dentre os que estão na direita*/
                node.setKey(temp.getKey());
                /*Remove o nó que a chave foi copiada*/
                node.setRight(delete(node.getRight(), temp.getKey()));
            }
        }
        
        /*Se após a remoção a árvore não tiver mais nenhum nó*/
        if(node == null)
            return node;
        
        /*Atualiza o tamanho da árvore*/
        node.setHeight(1 + max(altura(node.getLeft()), altura(node.getRight())));
        
        /*Variável para verificar o balanceamento*/
        int balance = getBalanceamento(node);
        
        /*Se o balanceamento do nó raiz atual da árvore/sub-árvore for maior que
        um e o balanceamento da sub-árvore da esquerda dessa raiz for maior ou 
        igual a zero, faz rotação simples à direita (rotação LL)*/
        if(balance > 1 && getBalanceamento(node.getLeft()) >= 0)
            return rotacaoDireita(node);
        
        /*Se o balanceamento do nó raiz atual da árvore/sub-árvore for maior que
        um e o balanceamento da sub-árvore da esquerda dessa raiz for menor que 
        zero, faz rotação dupla à direita (rotação LR)*/
        if(balance > 1 && getBalanceamento(node.getLeft()) < 0)
            return rotacaoLR(node);
        
        /*Se o balanceamento do nó raiz atual da árvore/sub-árvore for menor que
        menos um e o balanceamento da sub-árvore da direita dessa raiz for menor 
        que ou igual a zero, faz rotação simples à esquerda (rotação RR)*/
        if(balance < -1 && getBalanceamento(node.getRight()) <= 0)
            return rotacaoEsquerda(node);
        
        /*Se o balanceamento do nó raiz atual da árvore/sub-árvore for menor que
        menos um e o balanceamento da sub-árvore da direita dessa raiz for maior 
        zero, faz rotação dupla à esquerda (rotação RL)*/
        if(balance < -1 && getBalanceamento(node.getRight()) > 0)
            return rotacaoRL(node);
        
        return node;
    }
    
    /** Método que chama o método de buscar um determinado nó da árvore.
     * 
     * @param key int - Chave do nó que se deseja procurar.
     * @return true - Se encontrou o nó que possui a chave passada | false - Se 
     * não encontou o nó que possui a chave passada.
     */
    public boolean search(int key){
        return search(this.root, key);
    }
    
    /** Método que busca determinado nó da árvore com base na chave.
     * 
     * @param node NodeTree - Nó raiz da árvore ou sub-árvore.
     * @param key int - Chave do nó que se deseja procurar.
     * @return true - Se encontrou o nó que possui a chave passada | false - Se 
     * não encontou o nó que possui a chave passada.
     */
    private boolean search(NodeTree node, int key){
        if(node != null){
            boolean teste = false;
            
            if(key == node.getKey())
                return true;
            if(key < node.getKey())
                teste = search(node.getLeft(), key);
            else if(key > node.getKey())
                teste = search(node.getRight(), key);
            return teste;
        }
        return false;
    }
    
    /** Método que chama os métodos para percorrer a árvore AVL.
     * 
     * @param choice String pre - Percorrer em pré-ordem | String post - 
     * Percorrer em pós-ordem | String in - Percorrer em ordem.
     * @return String - Sequência correta desejada | String - "Escolha inválida!"
     * caso a o parâmetro passado não corresponda com nenhum método de percorrer
     * existente.
     */
    public String percorrer(String choice){
        switch(choice){
            case "pre":
                return preOrder(this.root);
            case "post":
                return postOrder(this.root);
            case "in":
                return inOrder(this.root);
            default:
                return "Escolha inválida!";
        }
    }
    
    /** Método que percorre todos os nós da árvore mostrando primeiro o conteúdo 
     * do nó pai, do filho da esquerda e depois do filho da direita.
     * 
     * @param node NodeTree - Nó raiz da árvore.
     * @return String - Uma String que contém as informações dos nós da árvore, 
     * organizados em pré-ordem.
     */
    protected String preOrder(NodeTree node){
        String show = "";
        
        if(node != null){
            show += node.getKey() + " ";
            show += preOrder(node.getLeft());
            show += preOrder(node.getRight());
        }
        return show;
    }
    
    /** Método que percorre todos os nós da árvore mostrando primeiro o conteúdo 
     * do nó da esquerda, o pai e depois o filho da direita.
     * 
     * @param node NodeTree - Nó raiz da árvore.
     * @return String - Uma String que contém as informações dos nós da árvore, 
     * organizados em ordem.
     */
    protected String inOrder(NodeTree node){
        String show = "";
        
        if(node != null){
            show += inOrder(node.getLeft());
            show += node.getKey() + " ";
            show += inOrder(node.getRight());
        }
        return show;
    }
    
    /** Método que percorre todos os nós da árvore mostrando primeiro os nós da 
     * esquerda, da direita e depois o pai.
     * 
     * @param node NodeTree - Nó raiz da árvore.
     * @return String - Uma String que contém as informações dos nós da árvore, 
     * organizados em pós-ordem.
     */
    protected String postOrder(NodeTree node){
        String show = "";
        
        if(node != null){
            show += postOrder(node.getLeft());
            show += postOrder(node.getRight());
            show += node.getKey() + " ";
        }
        return show;
    }
    
}
