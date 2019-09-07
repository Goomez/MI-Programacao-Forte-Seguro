package br.uefs.ecomp.forteseguro.model;

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
    private Vertice<String> vertice1, vertice2, vertice3, vertice4, vertice5, vertice6;
    private Aresta aresta1, aresta2, aresta3, aresta4;
    
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
        vertice4 = new Vertice("Ipiau", 2);
        vertice5 = new Vertice("Cicero_Dantas", 0);
        vertice6 = new Vertice("Valente", 1);
        aresta1 = new Aresta(vertice1, vertice3, 6);
        aresta2 = new Aresta(vertice2, vertice3, 16);
        aresta3 = new Aresta(vertice5, vertice6, 10);
        aresta4 = new Aresta(vertice4, vertice6, 23);
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
    public void adicionarPontos_E_Ligacoes(){
        assertEquals(0, sys.getGrafo().quantidadeVertices());
        assertEquals(0, sys.getGrafo().quantidadeArestas());
        
        sys.criarGrafo("grafos.txt");
        
        assertEquals(50, sys.getGrafo().quantidadeVertices());
        assertEquals(236, sys.getGrafo().quantidadeArestas());
        
        assertEquals("Cruzamento adicionado com sucesso!", sys.adicionarCruzamento("Valente", 1));
        assertEquals("Cruzamento adicionado com sucesso!", sys.adicionarCruzamento("Ipiau", 2));
        assertEquals("Cruzamento adicionado com sucesso!", sys.adicionarCruzamento("Cicero_Dantas", 0));
        assertEquals(53, sys.getGrafo().quantidadeVertices());
        
        assertTrue(sys.getGrafo().buscarVertice("Valente").equals(vertice6));
        assertTrue(sys.getGrafo().buscarVertice("Ipiau").equals(vertice4));
        assertTrue(sys.getGrafo().buscarVertice("Cicero_Dantas").equals(vertice5));
        
        assertEquals("Vértice já está adicionado no grafo!", sys.adicionarCruzamento("Santo_Amaro", 0));
        assertEquals("Vértice já está adicionado no grafo!", sys.adicionarCruzamento("Juazeiro", 2));
        assertEquals(53, sys.getGrafo().quantidadeVertices());
        
        assertEquals("Ligação adicionada com sucesso!", sys.adicionarLigacao("Cicero_Dantas", "Valente", 10));
        assertEquals("Ligação adicionada com sucesso!", sys.adicionarLigacao("Ipiau", "Valente", 23));
        assertEquals(238, sys.getGrafo().quantidadeArestas());
        
        assertEquals("Aresta já está adicionada no sistema!", sys.adicionarLigacao("Feira_de_Santana", "Irara", 6));
        assertEquals("Aresta já está adicionada no sistema!", sys.adicionarLigacao("Serrinha", "Irara", 16));
        assertEquals(238, sys.getGrafo().quantidadeArestas());
        
        assertTrue(sys.getGrafo().buscarAresta("Ipiau", "Valente").equals(aresta4));
        assertTrue(sys.getGrafo().buscarAresta("Cicero_Dantas", "Valente").equals(aresta3));
    }
    
    @Test
    public void removerPontos_E_Ligacoes(){
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
