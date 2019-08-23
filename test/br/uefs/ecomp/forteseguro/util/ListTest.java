package br.uefs.ecomp.forteseguro.util;

import br.uefs.ecomp.forteseguro.util.MyLinkedList;
import br.uefs.ecomp.forteseguro.util.MyIterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe que testa a lista encadeada ({@link MyLinkedList}).
 */
public class ListTest {
    MyLinkedList<Object> list;
    Object data1, data2, data3;

    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir), 
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp(){
        list = new MyLinkedList<>();
        data1 = 1;
        data2 = 2;
        data3 = 3;
    }    

    /**
     * Teste de unidade que verifica se a inserção de objetos na lista está
     * ocorrendo de forma correta.
     */
    @Test
    public void testInsert(){
        assertEquals(0, list.size());

        list.add(data1);
        assertEquals(1, list.size());

        list.add(data2);
        assertEquals(2, list.size());

        list.add(0, data3);
        assertEquals(3, list.size());
        
        assertEquals(data3, list.get(0));
        assertEquals(data1, list.get(1));
        assertEquals(data2, list.get(2));
        
        assertEquals(3, list.size());
    }
    
    /**
     * Teste de unidade que verifica se os dados presentes na lista estão sendo
     * recuperados corretamente.
     */
    @Test    
    public void testGet() {
        list.add(0,data1);
        list.add(1,data2);
        list.add(2,data3);
        assertEquals(data1, list.get(0));
        assertEquals(data2, list.get(1));
        assertEquals(data3, list.get(2));       
    }
    
    /**
     * Teste de unidade que verifica se a remoção de objetos na lista está sendo
     * feita corretamente.
     */
    @Test
    public void testDelete(){
        assertNull(list.remove(0));
        assertNull(list.remove(1));
        
        list.add(data1);
        list.add(data2);
        list.add(data3);
        
        assertEquals(data2, list.remove(1));
        assertEquals(2, list.size());

        assertEquals(data3, list.remove(1));
        assertEquals(1, list.size());

        assertNull(list.remove(1));

        assertEquals(data1, list.remove(0));
        assertEquals(0, list.size());
        
        assertTrue(list.isEmpty());
    }
    
    /**
     * Teste de unidade que verifica se os métodos de inserção e remoção de objetos 
     * na lista estão funcionando corretamente.
     */
    @Test
    public void testInsertDelete() {
        list.add(data1);
        list.add(data2);
        list.add(data3);
        assertFalse(list.isEmpty());

        assertEquals(data3, list.remove(2));
        assertFalse(list.isEmpty());

        assertEquals(data2, list.remove(1));
        assertFalse(list.isEmpty());

        assertEquals(data1, list.remove(0));
        assertTrue(list.isEmpty());
    }
    
    /**
     * Teste de unidade que verifica se a lista está vazia ou não.
     */
    @Test
    public void testisEmpty() {
        assertTrue(list.isEmpty());
        list.add(data1);
        assertFalse(list.isEmpty());
        list.remove(0);
        assertTrue(list.isEmpty());
    }
    
    /**
     * Teste de unidade que verifica o tamanho da lista antes e após inserções 
     * e remoções.
     */
    @Test
    public void testSize() {
        assertEquals(0, list.size());

        list.add(data1);
        assertEquals(1, list.size());

        list.add(data2);
        list.add(data3);
        assertEquals(3, list.size());

        list.remove(0);
        assertEquals(2, list.size());

        list.remove(0);
        list.remove(0);
        assertEquals(0, list.size());
    }

    /**
     * Teste de unidade que verifica se os métodos de inserção e remoção de 
     * objetos em índices específicos estão funcionando corretamente.
     */
    @Test
    public void testInsertRemovePosition() {
        assertNull(list.remove(0));
        assertNull(list.get(0));
        assertFalse(list.add(1, data1));
        assertTrue(list.isEmpty());
        
        list.add(0, data3);
        list.add(0, data2);
        list.add(0, data1);        
        
        assertEquals(data1, list.get(0));
        assertEquals(data2, list.get(1));
        assertEquals(data3, list.get(2));
        
        assertEquals(data1, list.remove(0));        
        assertEquals(data2, list.remove(0));
        assertEquals(data3, list.remove(0));
                
        assertTrue(list.isEmpty());
    }

    /**
     * Teste de unidade que verifica se o método iterator está listando os
     * objetos corretamente.
     */
    @Test
    public void testIterator() {
        MyIterator it = list.iterator();
        assertFalse(it.hasNext());

        list.add(data1);
        list.add(data2);
        list.add(data3);
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(data1, it.next());
        assertTrue(it.hasNext());
        assertEquals(data2, it.next());
        assertTrue(it.hasNext());
        assertEquals(data3, it.next());
        assertFalse(it.hasNext());
    }
}
