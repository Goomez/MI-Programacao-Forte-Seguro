package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
	Boolean flag;
	Grafo grafo;
	AlgoritmoDijkstra operador;
	Vertice a, b, c, d, e, f, g, h, i, j, k, l;
        
	@Before
	public void setUp() throws Exception {
		grafo = new Grafo();
		a = grafo.inserir("A");
		b = grafo.inserir("B");
		c = grafo.inserir("C");
		d = grafo.inserir("D");
		e = grafo.inserir("E");
		f = grafo.inserir("F");
		g = grafo.inserir("G");
		h = grafo.inserir("H");
		i = grafo.inserir("I");
		j = grafo.inserir("J");
		k = grafo.inserir("K");
		l = grafo.inserir("L");

		grafo.inserirAresta(a, b, 3);
		grafo.inserirAresta(a, e, 2);
		
		grafo.inserirAresta(b, a, 3);
		grafo.inserirAresta(b, d, 7);
		
		grafo.inserirAresta(c, f, 2);
		
		grafo.inserirAresta(d, b, 7);
		grafo.inserirAresta(d, e, 5);
		grafo.inserirAresta(d, g, 9);
		grafo.inserirAresta(d, f, 3);
		
		grafo.inserirAresta(e, a, 2);
		grafo.inserirAresta(e, d, 5);
		grafo.inserirAresta(e, g, 4);
		
		grafo.inserirAresta(f, c, 2);
		grafo.inserirAresta(f, d, 3);
		grafo.inserirAresta(f, g, 6);
		
		grafo.inserirAresta(g, e, 4);
		grafo.inserirAresta(g, d, 9);
		grafo.inserirAresta(g, f, 6);
		grafo.inserirAresta(g, i, 1);
		
		grafo.inserirAresta(h, j, 7);
		
		grafo.inserirAresta(i, g, 1);
		grafo.inserirAresta(i, j, 3);
		
		grafo.inserirAresta(j, h, 7);
		grafo.inserirAresta(j, i, 3);
		grafo.inserirAresta(j, k, 9);
		
		grafo.inserirAresta(k, j, 9);

		operador = new AlgoritmoDijkstra(grafo);

	}

	private void comparar(ArrayList<ArrayList<Vertice>> caminhosEsperados, List<List<Vertice>> caminhos) {
		assertEquals(caminhosEsperados.size(), caminhos.size());
		for(List<Vertice> caminho : caminhos){
			assertNotNull(caminho);
			assertTrue(caminho.size() > 0);
			assertEquals(caminhosEsperados.size(), caminhos.size());

			flag = false;
			for(List<Vertice> camEsperado : caminhosEsperados){
				if(!flag && camEsperado.equals(caminho))flag = true;
			}
			assertTrue(flag);

		}

	}
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho1_1Test() throws Exception {

		operador.executar(a);
		List<List<Vertice>> caminhos = operador.getCaminho(null, g);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(a);
		caminhoEsperado.add(e);
		caminhoEsperado.add(g);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho1_2Test() throws Exception {

		operador.executar(g);
		List<List<Vertice>> caminhos = operador.getCaminho(null, h);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(g);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		caminhoEsperado.add(h);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho2_1Test() throws Exception {

		operador.executar(b);
		List<List<Vertice>> caminhos = operador.getCaminho(null, g);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(b);
		caminhoEsperado.add(a);
		caminhoEsperado.add(e);
		caminhoEsperado.add(g);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho2_2Test() throws Exception {

		operador.executar(g);
		List<List<Vertice>> caminhos = operador.getCaminho(null, c);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(c);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho3_1Test() throws Exception {

		operador.executar(d);
		List<List<Vertice>> caminhos = operador.getCaminho(null, g);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(d);
		caminhoEsperado.add(g);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(d);
		caminhoEsperado.add(e);
		caminhoEsperado.add(g);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(d);
		caminhoEsperado.add(f);
		caminhoEsperado.add(g);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho3_2Test() throws Exception {

		operador.executar(g);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(g);
		caminhoEsperado.add(e);
		caminhoEsperado.add(a);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho4_1Test() throws Exception {

		operador.executar(k);
		List<List<Vertice>> caminhos = operador.getCaminho(null, f);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(k);
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho4_2Test() throws Exception {

		operador.executar(f);
		List<List<Vertice>> caminhos = operador.getCaminho(null, a);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(f);
		caminhoEsperado.add(d);
		caminhoEsperado.add(e);
		caminhoEsperado.add(a);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho5_1Test() throws Exception {

		operador.executar(c);
		List<List<Vertice>> caminhos = operador.getCaminho(null, k);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(f);
		caminhoEsperado.add(g);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		caminhoEsperado.add(k);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho5_2Test() throws Exception {

		operador.executar(k);
		List<List<Vertice>> caminhos = operador.getCaminho(null, h);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(k);
		caminhoEsperado.add(j);
		caminhoEsperado.add(h);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho6_1Test() throws Exception {

		operador.executar(h);
		List<List<Vertice>> caminhos = operador.getCaminho(null, i);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(h);
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho6_2Test() throws Exception {

		operador.executar(i);
		List<List<Vertice>> caminhos = operador.getCaminho(null, k);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		caminhoEsperado.add(k);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho7_1Test() throws Exception {

		operador.executar(f);
		List<List<Vertice>> caminhos = operador.getCaminho(null, e);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(f);
		caminhoEsperado.add(d);
		caminhoEsperado.add(e);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho7_2Test() throws Exception {

		operador.executar(e);
		List<List<Vertice>> caminhos = operador.getCaminho(null, i);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(e);
		caminhoEsperado.add(g);
		caminhoEsperado.add(i);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho8_1Test() throws Exception {

		operador.executar(i);
		List<List<Vertice>> caminhos = operador.getCaminho(null, c);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(i);
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(c);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho8_2Test() throws Exception {

		operador.executar(c);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(f);
		caminhoEsperado.add(d);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho9_1Test() throws Exception {

		operador.executar(d);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(d);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho9_2Test() throws Exception {

		operador.executar(b);
		List<List<Vertice>> caminhos = operador.getCaminho(null, a);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(b);
		caminhoEsperado.add(a);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho10_1Test() throws Exception {

		operador.executar(e);
		List<List<Vertice>> caminhos = operador.getCaminho(null, c);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(e);
		caminhoEsperado.add(d);
		caminhoEsperado.add(f);
		caminhoEsperado.add(c);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho10_2Test() throws Exception {

		operador.executar(c);
		List<List<Vertice>> caminhos = operador.getCaminho(null, h);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(f);
		caminhoEsperado.add(g);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		caminhoEsperado.add(h);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	
}
