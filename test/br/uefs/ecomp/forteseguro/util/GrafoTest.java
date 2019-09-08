/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data: <31/08/2019>
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

import br.uefs.ecomp.forteseguro.util.Grafo;
import br.uefs.ecomp.forteseguro.util.AlgoritmoDijkstra;
import br.uefs.ecomp.forteseguro.util.Vertice;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes de unidade para a classe ({@link Grafo}).
 * @author Kevin Cerqueira
 * @author Allan Capistrano
 */
public class GrafoTest {
    Boolean flag;
    Grafo<String> grafo;
    AlgoritmoDijkstra dijkstra;
    Vertice<String> vert1, vert2, vert3, vert4, vert5, vert6, vert7, vert8, vert9, vert10, vert11, vert12;

    @Before
    public void setUp() throws Exception {
        grafo = new Grafo();
        vert1 = grafo.inserir("Feira_de_Santana", 0, 10, 10); // Nome : Posicao X : Posicao Y
        vert2 = grafo.inserir("Amelia_Rodrigues", 3, 10, 10);
        vert3 = grafo.inserir("Irara", 1, 10, 10);
        vert4 = grafo.inserir("Serrinha", 1, 10, 10);
        vert5 = grafo.inserir("Banco_do_Brasil", 0, 10, 10);
        vert6 = grafo.inserir("Santander", 2, 10, 10);
        vert7 = grafo.inserir("Nubank", 0, 10, 10);
        vert8 = grafo.inserir("Maria_Quiteria", 2, 10, 10);
        vert9 = grafo.inserir("Banco_do_Nordeste", 0, 10, 10);
        vert10 = grafo.inserir("Bradesco", 1, 10, 10);
        vert11 = grafo.inserir("Salvador", 0, 10, 10);
        vert12 = grafo.inserir("Igreja_Universal", 0, 10, 10);

        grafo.inserirAresta(vert1, vert2, 3);
        grafo.inserirAresta(vert1, vert5, 2);

        grafo.inserirAresta(vert2, vert1, 3);
        grafo.inserirAresta(vert2, vert4, 7);

        grafo.inserirAresta(vert3, vert6, 2);

        grafo.inserirAresta(vert4, vert2, 7);
        grafo.inserirAresta(vert4, vert5, 5);
        grafo.inserirAresta(vert4, vert7, 9);
        grafo.inserirAresta(vert4, vert6, 3);

        grafo.inserirAresta(vert5, vert1, 2);
        grafo.inserirAresta(vert5, vert4, 5);
        grafo.inserirAresta(vert5, vert7, 4);

        grafo.inserirAresta(vert6, vert3, 2);
        grafo.inserirAresta(vert6, vert4, 3);
        grafo.inserirAresta(vert6, vert7, 6);

        grafo.inserirAresta(vert7, vert5, 4);
        grafo.inserirAresta(vert7, vert4, 9);
        grafo.inserirAresta(vert7, vert6, 6);
        grafo.inserirAresta(vert7, vert9, 1);

        grafo.inserirAresta(vert8, vert10, 7);

        grafo.inserirAresta(vert9, vert7, 1);
        grafo.inserirAresta(vert9, vert10, 3);

        grafo.inserirAresta(vert10, vert8, 7);
        grafo.inserirAresta(vert10, vert9, 3);
        grafo.inserirAresta(vert10, vert11, 9);

        grafo.inserirAresta(vert11, vert10, 9);

        dijkstra = new AlgoritmoDijkstra(grafo);

    }

    private void comparar(ArrayList<ArrayList<Vertice<String>>> caminhosEsperados, List<List<Vertice<String>>> caminhos) {
        assertEquals(caminhosEsperados.size(), caminhos.size());
        for(List<Vertice<String>> caminho : caminhos){
            assertNotNull(caminho);
            assertTrue(caminho.size() > 0);
            assertEquals(caminhosEsperados.size(), caminhos.size());

            flag = false;
            for(ArrayList<Vertice<String>> camEsperado : caminhosEsperados){
                    if(!flag && camEsperado.equals(caminho))flag = true;
            }
            assertTrue(flag);
        }

    }
    
    @Test
    public void Caminho1_1Test() throws Exception {
        dijkstra.executar(vert1);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert7);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert1);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho1_2Test() throws Exception {
        dijkstra.executar(vert7);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert8);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert8);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho2_1Test() throws Exception {
        dijkstra.executar(vert2);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert7);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert2);
        caminhoEsperado.add(vert1);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho2_2Test() throws Exception {
        dijkstra.executar(vert7);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert3);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert3);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho3_1Test() throws Exception {
        dijkstra.executar(vert4);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert7);
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
    
    @Test
    public void Caminho3_2Test() throws Exception {
        dijkstra.executar(vert7);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert2);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert1);
        caminhoEsperado.add(vert2);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho4_1Test() throws Exception {
        dijkstra.executar(vert11);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert6);
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
    
    @Test
    public void Caminho4_2Test() throws Exception {
        dijkstra.executar(vert6);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert1);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert1);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho5_1Test() throws Exception {
        dijkstra.executar(vert3);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert11);
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
    
    @Test
    public void Caminho5_2Test() throws Exception {
        dijkstra.executar(vert11);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert8);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert11);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert8);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho6_1Test() throws Exception {
        dijkstra.executar(vert8);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert9);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert8);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert9);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho6_2Test() throws Exception {
        dijkstra.executar(vert9);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert11);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert10);
        caminhoEsperado.add(vert11);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho7_1Test() throws Exception {
        dijkstra.executar(vert6);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert5);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert5);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho7_2Test() throws Exception {
        dijkstra.executar(vert5);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert9);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert9);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho8_1Test() throws Exception {
        dijkstra.executar(vert9);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert3);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert9);
        caminhoEsperado.add(vert7);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert3);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho8_2Test() throws Exception {
        dijkstra.executar(vert3);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert2);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert3);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert2);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho9_1Test() throws Exception {
        dijkstra.executar(vert4);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert2);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert2);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho9_2Test() throws Exception {
        dijkstra.executar(vert2);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert1);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert2);
        caminhoEsperado.add(vert1);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho10_1Test() throws Exception {
        dijkstra.executar(vert5);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert3);
        ArrayList<ArrayList<Vertice<String>>> caminhosEsperados = new ArrayList<>();

        ArrayList<Vertice<String>> caminhoEsperado = new ArrayList<>();
        caminhoEsperado.add(vert5);
        caminhoEsperado.add(vert4);
        caminhoEsperado.add(vert6);
        caminhoEsperado.add(vert3);

        caminhosEsperados.add(caminhoEsperado);
        comparar(caminhosEsperados, caminhos);
    }
    
    @Test
    public void Caminho10_2Test() throws Exception {
        dijkstra.executar(vert3);
        List<List<Vertice<String>>> caminhos = dijkstra.getCaminho(null, vert8);
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
