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
package br.uefs.ecomp.forteseguro.util;

import br.uefs.ecomp.forteseguro.model.Grafo;
import br.uefs.ecomp.forteseguro.model.AlgoritimoDijkstra;
import br.uefs.ecomp.forteseguro.model.Vertice;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes de unidade para a classe ({@link Grafo}).
 */
public class GrafoTest {

    Boolean flag;
    Grafo<String> grafo;
    AlgoritimoDijkstra dijkstra;
    Vertice<String> vert1, vert2, vert3, vert4, vert5, vert6, vert7, vert8, vert9, vert10, vert11, vert12;

    @Before
    public void setUp() throws Exception {
        grafo = new Grafo();
        vert1 = grafo.novoVertice("Feira_de_Santana", 0, 10, 10); // Nome : Posicao X : Posicao Y
        vert2 = grafo.novoVertice("Amelia_Rodrigues", 3, 10, 10);
        vert3 = grafo.novoVertice("Irara", 1, 10, 10);
        vert4 = grafo.novoVertice("Serrinha", 1, 10, 10);
        vert5 = grafo.novoVertice("Banco_do_Brasil", 0, 10, 10);
        vert6 = grafo.novoVertice("Santander", 2, 10, 10);
        vert7 = grafo.novoVertice("Nubank", 0, 10, 10);
        vert8 = grafo.novoVertice("Maria_Quiteria", 2, 10, 10);
        vert9 = grafo.novoVertice("Banco_do_Nordeste", 0, 10, 10);
        vert10 = grafo.novoVertice("Bradesco", 1, 10, 10);
        vert11 = grafo.novoVertice("Salvador", 0, 10, 10);
        vert12 = grafo.novoVertice("Igreja_Universal", 0, 10, 10);

        grafo.criarAresta(vert1, vert2, 3);
        grafo.criarAresta(vert1, vert5, 2);

        grafo.criarAresta(vert2, vert1, 3);
        grafo.criarAresta(vert2, vert4, 7);

        grafo.criarAresta(vert3, vert6, 2);

        grafo.criarAresta(vert4, vert2, 7);
        grafo.criarAresta(vert4, vert5, 5);
        grafo.criarAresta(vert4, vert7, 9);
        grafo.criarAresta(vert4, vert6, 3);

        grafo.criarAresta(vert5, vert1, 2);
        grafo.criarAresta(vert5, vert4, 5);
        grafo.criarAresta(vert5, vert7, 4);

        grafo.criarAresta(vert6, vert3, 2);
        grafo.criarAresta(vert6, vert4, 3);
        grafo.criarAresta(vert6, vert7, 6);

        grafo.criarAresta(vert7, vert5, 4);
        grafo.criarAresta(vert7, vert4, 9);
        grafo.criarAresta(vert7, vert6, 6);
        grafo.criarAresta(vert7, vert9, 1);

        grafo.criarAresta(vert8, vert10, 7);

        grafo.criarAresta(vert9, vert7, 1);
        grafo.criarAresta(vert9, vert10, 3);

        grafo.criarAresta(vert10, vert8, 7);
        grafo.criarAresta(vert10, vert9, 3);
        grafo.criarAresta(vert10, vert11, 9);

        grafo.criarAresta(vert11, vert10, 9);

        dijkstra = new AlgoritimoDijkstra(grafo);

    }

    /**
     * Teste de unidade que verifica o caminho gerado pelo Algoritimo de
     * Dijkstra pelo caminho correto e esperado.
     *
     * @param caminhosEsperados caminhos corretos e esperados.
     * @param caminhos caminhos gerados pelo Algoritimo de Dikstra.
     */
    private void comparar(ArrayList<ArrayList<Vertice<String>>> caminhosEsperados, List<List<Vertice<String>>> caminhos) {
        assertEquals(caminhosEsperados.size(), caminhos.size());
        for (List<Vertice<String>> caminho : caminhos) {
            assertNotNull(caminho);
            assertTrue(caminho.size() > 0);
            assertEquals(caminhosEsperados.size(), caminhos.size());

            flag = false;
            for (ArrayList<Vertice<String>> camEsperado : caminhosEsperados) {
                if (!flag && camEsperado.equals(caminho)) {
                    flag = true;
                }
            }
            assertTrue(flag);
        }

    }

    /**
     * Teste de caminho 1.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho1_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert1);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert7);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert1);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 1.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho1_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert7);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert8);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert8);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 2.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho2_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert2);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert7);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert2);
        caminhoEsperado.add(vert1);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 2.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho2_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert7);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert3);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert3);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 3.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho3_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert4);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert7);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);

        caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);

        caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 3.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho3_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert7);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert2);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert1);
        caminhoEsperado.add(vert2);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 4.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho4_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert11);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert6);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert11);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert6);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 4.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho4_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert6);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert1);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert1);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 5.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho5_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert3);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert11);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert3);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert11);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 5.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho5_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert11);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert8);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert11);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert8);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 6.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho6_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert8);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert9);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert8);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert9);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 6.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho6_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert9);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert11);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert11);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 7.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho7_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert6);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert5);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert5);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 7.2 Cria um novo caminho aleatório e também gera um novo
     * pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho7_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert5);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert9);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert9);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 8.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho8_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert9);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert3);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert3);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 8.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho8_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert3);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert2);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert3);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert2);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 9.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho9_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert4);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert2);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert2);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 9.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho9_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert2);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert1);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert2);
        caminhoEsperado.add(vert1);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 10.1: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho10_1Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert5);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert3);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert3);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }

    /**
     * Teste de caminho 10.2: Cria um novo caminho aleatório e também gera um
     * novo pelo Dijkstra, e os envia para a comparação.
     *
     * @throws Exception exceções descritas na classe do Algoritimo de Dijkstra.
     */
    @Test
    public void Caminho10_2Test() throws Exception {
        dijkstra.calcularMenoresCaminhos(vert3);
        List<List<Vertice<String>>> caminhos = dijkstra.getDijkstra(null, vert8);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert3);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert8);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
}
