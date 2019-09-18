package br.uefs.ecomp.forteseguro.model;

import br.uefs.ecomp.forteseguro.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import br.uefs.ecomp.forteseguro.util.Aresta;
import br.uefs.ecomp.forteseguro.util.Vertice;

/**
 * Testes de unidade para a classe ({@link Controller}).
 */
public class ControllerTest {
    private Controller ctrl;
    private Vertice<String> vertice1, vertice2, vertice3, vertice4, vertice5, vertice6, vertice7;
    private Aresta aresta1, aresta2, aresta3, aresta4;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception{
        ctrl = new Controller();
        vertice1 = new Vertice("Feira_de_Santana", 1);
        vertice2 = new Vertice("Serrinha", 0);
        vertice3 = new Vertice("Irara", 2);
        vertice4 = new Vertice("Ipiau", 2);
        vertice5 = new Vertice("Cicero_Dantas", 0);
        vertice6 = new Vertice("Valente", 1);
        vertice7 = new Vertice("Salvador", 3);
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
        assertEquals("Grafo criado com sucesso", ctrl.criarGrafo("grafos.txt"));
        
        assertTrue(ctrl.getGrafo().buscarVertice("Feira_de_Santana").equals(vertice1));
        assertTrue(ctrl.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(ctrl.getGrafo().buscarVertice("Irara").equals(vertice3));
        
        assertTrue(ctrl.getGrafo().buscarAresta("Feira_de_Santana", "Irara").equals(aresta1));
        assertTrue(ctrl.getGrafo().buscarAresta("Serrinha", "Irara").equals(aresta2));
        
    }
    
    @Test
    public void adicionarPontos_E_Ligacoes(){
        assertEquals(0, ctrl.getGrafo().quantidadeVertices());
        assertEquals(0, ctrl.getGrafo().quantidadeArestas());
        
        ctrl.criarGrafo("grafos.txt");
        
        assertEquals(50, ctrl.getGrafo().quantidadeVertices());
        assertEquals(236, ctrl.getGrafo().quantidadeArestas());
        
        assertEquals("Cruzamento adicionado com sucesso!", ctrl.adicionarCruzamento("Valente", 1));
        assertEquals("Cruzamento adicionado com sucesso!", ctrl.adicionarCruzamento("Ipiau", 2));
        assertEquals("Cruzamento adicionado com sucesso!", ctrl.adicionarCruzamento("Cicero_Dantas", 0));
        assertEquals(53, ctrl.getGrafo().quantidadeVertices());
        
        assertTrue(ctrl.getGrafo().buscarVertice("Valente").equals(vertice6));
        assertTrue(ctrl.getGrafo().buscarVertice("Ipiau").equals(vertice4));
        assertTrue(ctrl.getGrafo().buscarVertice("Cicero_Dantas").equals(vertice5));
        
        assertEquals("Cruzamento já está adicionado no grafo!", ctrl.adicionarCruzamento("Santo_Amaro", 0));
        assertEquals("Cruzamento já está adicionado no grafo!", ctrl.adicionarCruzamento("Juazeiro", 2));
        assertEquals(53, ctrl.getGrafo().quantidadeVertices());
        
        assertEquals("Ligação adicionada com sucesso!", ctrl.adicionarLigacao("Cicero_Dantas", "Valente", 10));
        assertEquals("Ligação adicionada com sucesso!", ctrl.adicionarLigacao("Ipiau", "Valente", 23));
        assertEquals(238, ctrl.getGrafo().quantidadeArestas());
        
        assertEquals("Ligação já está adicionada no sistema!", ctrl.adicionarLigacao("Feira_de_Santana", "Irara", 6));
        assertEquals("Ligação já está adicionada no sistema!", ctrl.adicionarLigacao("Serrinha", "Irara", 16));
        assertEquals(238, ctrl.getGrafo().quantidadeArestas());
        
        assertTrue(ctrl.getGrafo().buscarAresta("Ipiau", "Valente").equals(aresta4));
        assertTrue(ctrl.getGrafo().buscarAresta("Cicero_Dantas", "Valente").equals(aresta3));
    }
    
    @Test
    public void removerPontos_E_Ligacoes(){
        assertEquals("Grafo criado com sucesso", ctrl.criarGrafo("grafos.txt"));
        
        assertTrue(ctrl.getGrafo().buscarVertice("Feira_de_Santana").equals(vertice1));
        assertTrue(ctrl.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(ctrl.getGrafo().buscarVertice("Irara").equals(vertice3));
        
        assertTrue(ctrl.getGrafo().buscarAresta("Feira_de_Santana", "Irara").equals(aresta1));
        assertTrue(ctrl.getGrafo().buscarAresta("Serrinha", "Irara").equals(aresta2));

        assertEquals("Ligação removida com sucesso!", ctrl.removerLigacao(aresta1));
        assertNull(ctrl.getGrafo().buscarAresta("Feira_de_Santana", "Irara"));
        
        assertEquals("Cruzamento removido com sucesso!", ctrl.removerCruzamento("Irara"));
        assertNull(ctrl.getGrafo().buscarVertice("Irara"));
        assertNull(ctrl.getGrafo().buscarAresta(vertice3));
        
        assertEquals("Cruzamento não existe!", ctrl.removerCruzamento("Irara"));
        assertEquals("Ligação não existe!", ctrl.removerLigacao(aresta2));
    }
    
    @Test
    public void alterarPontos(){
        
        ctrl.criarGrafo("grafos.txt");
        
        assertTrue(ctrl.existeEstacionamento());
        
        assertTrue(ctrl.getGrafo().buscarVertice("Salvador").equals(vertice7));
        assertTrue(ctrl.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(ctrl.getGrafo().buscarVertice("Irara").equals(vertice3));
        
        ctrl.alterarTipoCruzamento("Salvador", 2);
        ctrl.alterarTipoCruzamento("Serrinha", 1);
        
        assertFalse(ctrl.getGrafo().buscarVertice("Salvador").equals(vertice7));
        assertFalse(ctrl.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(ctrl.getGrafo().buscarVertice("Irara").equals(vertice3));
        
        assertFalse(ctrl.existeEstacionamento());
    }
}
