package br.uefs.ecomp.forteseguro.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Testes de unidade para a classe ({@link MyTree}).
 */
public class MyTreeTest {
    MyTree tree;
    int key1, key2, key3, key4, key5;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception{
        tree = new MyTree();
        key1 = 4;
        key2 = 5;
        key3 = 6;
        key4 = 7;
        key5 = 8;
    }
    
    /**
     * Teste que verifica a inserção de chaves na árvore AVL.
     */
    @Test
    public void inserir(){
        assertTrue(tree.isEmpty());
        
        tree.inserir(key1);
        tree.inserir(key2);
        tree.inserir(key3);
        tree.inserir(key4);
        tree.inserir(key5);
        
        assertFalse(tree.isEmpty());
        assertEquals(5, tree.countNodes());
    }
    
    /**
     * Teste que verifica a remoção de chaves na árvore AVL.
     */
    @Test
    public void remover(){
        assertTrue(tree.isEmpty());
        
        tree.inserir(key1);
        tree.inserir(key2);
        tree.inserir(key3);
        tree.inserir(key4);
        tree.inserir(key5);
        
        assertFalse(tree.isEmpty());
        assertEquals(5, tree.countNodes());
        
        assertTrue(tree.search(key1));
        assertTrue(tree.search(key4));
        assertTrue(tree.search(key3));
        
        tree.delete(key1);
        tree.delete(key3);
        
        assertFalse(tree.search(key3));
        assertFalse(tree.search(key1));
    }
    
    /**
     * Teste que que busca uma determinada chave na árvore AVL.
     */
    @Test
    public void procurar(){
        assertTrue(tree.isEmpty());
        
        tree.inserir(key1);
        tree.inserir(key2);
        tree.inserir(key5);
        
        assertFalse(tree.isEmpty());
        assertEquals(3, tree.countNodes());
        
        assertTrue(tree.search(key1));
        assertTrue(tree.search(key5));
        assertTrue(tree.search(key2));
        
        assertFalse(tree.search(key3));
        assertFalse(tree.search(key4));
    }
    
    /**
     * Teste de unidade que verifica se os métodos de percorrer (pré-ordem, 
     * em ordem, pós-ordem) da árvore AVL está funcionando corretamente.
     */
    @Test
    public void percorrer(){
        assertTrue(tree.isEmpty());
        
        tree.inserir(key1);
        tree.inserir(key2);
        tree.inserir(key3);
        tree.inserir(key4);
        tree.inserir(key5);
        
        assertFalse(tree.isEmpty());
        assertEquals(5, tree.countNodes());
        
        assertEquals("5 4 7 6 8 ", tree.percorrer("pre"));
        assertEquals("4 5 6 7 8 ", tree.percorrer("in"));
        assertEquals("4 6 8 7 5 ", tree.percorrer("post"));
        assertEquals("Escolha inválida!", tree.percorrer("percorrer"));
        
    }
    
    
}
