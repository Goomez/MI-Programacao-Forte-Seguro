package br.uefs.ecomp.forteseguro.model;

import br.uefs.ecomp.forteseguro.model.System;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import br.uefs.ecomp.forteseguro.util.Aresta;
import br.uefs.ecomp.forteseguro.util.Vertice;

/**
 * Testes de unidade para a classe ({@link System}).
 */
public class SystemTest {
    private System sys;
    private Vertice<String> vertice1, vertice2, vertice3;
    private Aresta aresta1, aresta2;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception{
        sys = new System();
        vertice1 = new Vertice("Feira_de_Santana", 0);
        vertice2 = new Vertice("Serrinha", 0);
        vertice3 = new Vertice("Irara", 2);
        aresta1 = new Aresta(vertice1, vertice3, 6);
        aresta2 = new Aresta(vertice2, vertice3, 16);
    }
    
    /**
     * Teste de unidade que verifica se foi possível criar o grafo com base
     * nos vértices e nas arestas que estão no arquivo de texto grafos.txt.
     */
    @Test
    public void criarGrafo(){
        assertEquals("Grafo criado com sucesso", sys.criarGrafo("grafos.txt"));
        
        assertTrue(sys.getGrafo().buscarVertice("Feira_de_Santana").equals(vertice1));
        assertTrue(sys.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(sys.getGrafo().buscarVertice("Irara").equals(vertice3));
        
        assertTrue(sys.getGrafo().buscarAresta("Feira_de_Santana", "Irara").equals(aresta1));
        assertTrue(sys.getGrafo().buscarAresta("Serrinha", "Irara").equals(aresta2));
        
    }
    
    @Test
    public void removerPontosLigacoes(){
        assertEquals("Grafo criado com sucesso", sys.criarGrafo("grafos.txt"));
        
        assertTrue(sys.getGrafo().buscarVertice("Feira_de_Santana").equals(vertice1));
        assertTrue(sys.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(sys.getGrafo().buscarVertice("Irara").equals(vertice3));
        
        assertTrue(sys.getGrafo().buscarAresta("Feira_de_Santana", "Irara").equals(aresta1));
        assertTrue(sys.getGrafo().buscarAresta("Serrinha", "Irara").equals(aresta2));

        assertEquals("Ligação removida com sucesso!", sys.removerLigacao(aresta1));
        assertNull(sys.getGrafo().buscarAresta("Feira_de_Santana", "Irara"));
        
        assertEquals("Cruzamento removido com sucesso!", sys.removerCruzamento("Irara"));
        assertNull(sys.getGrafo().buscarVertice("Irara"));
        assertNull(sys.getGrafo().buscarAresta(vertice3));
        
        assertEquals("Cruzamento não existe!", sys.removerCruzamento("Irara"));
        assertEquals("Ligação não existe!", sys.removerLigacao(aresta2));
    }
}
