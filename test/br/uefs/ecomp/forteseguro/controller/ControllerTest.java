/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data: <31/08/2019>
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
package br.uefs.ecomp.forteseguro.controller;

import br.uefs.ecomp.forteseguro.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import br.uefs.ecomp.forteseguro.model.Aresta;
import br.uefs.ecomp.forteseguro.model.Vertice;

/**
 * Testes de unidade para a classe ({@link Controller}).
 */
public class ControllerTest {

    private Controller sys;
    private Vertice<String> vertice1, vertice2, vertice3, vertice4, vertice5, vertice6, vertice7;
    private Aresta aresta1, aresta2, aresta3, aresta4;

    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        sys = new Controller();
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
     * Teste de unidade que verifica se foi possível criar o grafo com base nos
     * vértices e nas arestas que estão no arquivo de texto grafos.txt.
     */
    @Test
    public void criarGrafo() {
        assertEquals("Grafo criado com sucesso", sys.criarGrafo("grafos.txt"));

        assertTrue(sys.getGrafo().buscarVertice("Feira_de_Santana").equals(vertice1));
        assertTrue(sys.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(sys.getGrafo().buscarVertice("Irara").equals(vertice3));

        assertTrue(sys.getGrafo().buscarAresta("Feira_de_Santana", "Irara").equals(aresta1));
        assertTrue(sys.getGrafo().buscarAresta("Serrinha", "Irara").equals(aresta2));

    }

    /**
     * Teste de unidade que verifica a criação de pontos e ligações no grafo.
     */
    @Test
    public void adicionarPontos_E_Ligacoes() {
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

        assertEquals("Cruzamento já está adicionado no grafo!", sys.adicionarCruzamento("Santo_Amaro", 0));
        assertEquals("Cruzamento já está adicionado no grafo!", sys.adicionarCruzamento("Juazeiro", 2));
        assertEquals(53, sys.getGrafo().quantidadeVertices());

        assertEquals("Ligação adicionada com sucesso!", sys.adicionarLigacao("Cicero_Dantas", "Valente", 10));
        assertEquals("Ligação adicionada com sucesso!", sys.adicionarLigacao("Ipiau", "Valente", 23));
        assertEquals(238, sys.getGrafo().quantidadeArestas());

        assertEquals("Ligação já está adicionada no sistema!", sys.adicionarLigacao("Feira_de_Santana", "Irara", 6));
        assertEquals("Ligação já está adicionada no sistema!", sys.adicionarLigacao("Serrinha", "Irara", 16));
        assertEquals(238, sys.getGrafo().quantidadeArestas());

        assertTrue(sys.getGrafo().buscarAresta("Ipiau", "Valente").equals(aresta4));
        assertTrue(sys.getGrafo().buscarAresta("Cicero_Dantas", "Valente").equals(aresta3));
    }

    /**
     * Teste de unidade que verifica a remoção de pontos e ligações no grafo.
     */
    @Test
    public void removerPontos_E_Ligacoes() {
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

    /**
     * Teste de unidade que verifica a alteração de pontos e ligações no grafo.
     */
    @Test
    public void alterarPontos() {

        sys.criarGrafo("grafos.txt");

        assertTrue(sys.existeEstacionamento());

        assertTrue(sys.getGrafo().buscarVertice("Salvador").equals(vertice7));
        assertTrue(sys.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(sys.getGrafo().buscarVertice("Irara").equals(vertice3));

        sys.alterarTipoCruzamento("Salvador", 2);
        sys.alterarTipoCruzamento("Serrinha", 1);

        assertFalse(sys.getGrafo().buscarVertice("Salvador").equals(vertice7));
        assertFalse(sys.getGrafo().buscarVertice("Serrinha").equals(vertice2));
        assertTrue(sys.getGrafo().buscarVertice("Irara").equals(vertice3));

        assertFalse(sys.existeEstacionamento());
    }
}
