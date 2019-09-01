/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <//2019>
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
package model;

import util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsável por gerenciar todo o sistema (classes e objetos) do 
 * programa.
 * @author Kevin e Allan
 */
public class System {
    private Grafo<String> grafo;

    public System() {
        grafo = new Grafo<String>();
    }
    
    public Grafo<String> getGrafo() {
        return grafo;
    }
    
    /** Método que cria o grafo com base nos vértices e nas arestas que estão 
     * contidos dentro do arquivo de texto.
     * 
     * @param nomeArq String - Nome do arquivo de texto que estão localizados os
     * dados para a criação do grafo.
     * @return Grafo criado com sucesso - Caso tudo ocorra corretamente | 
     * Arquivo de texto não encontrado! - Caso haja algum problema em encontrar 
     * o arquivo de texto | Dados inválidos e/ou incompreensíveis no arquivo! - 
     * Caso os dados inseridos não estejam corretos.
     */
    public String criarGrafo(String nomeArq){
        File file = new File(nomeArq);
        
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String leitura = null;
            
            /*Lê a parte do arquivo que estão os vértices*/
            while(!"[Arestas]".equals(leitura = br.readLine())){
                if(leitura.equals("[Vertices]"))
                    continue;
                int contador = 0;
                String[] subString = leitura.split(" ");
                String identificador = subString[0];
                 //Tipo: 0 - Vértice comum | 1 - Banco | 2 - Coleta | 3 - Estacionamento
                int tipo = Integer.parseInt(subString[1]);
                //int x = Integer.parseInt(subString[2]);
                //int y = Integer.parseInt(subString[3]);
                
                grafo.inserir(identificador, tipo);
                contador++;
            }
            /*Lê a parte do arquivo que estão as arestas*/
            while((leitura = br.readLine()) != null){
                String[] subString = leitura.split(" ");
                int distancia = Integer.parseInt(subString[2]);
                
                grafo.inserirAresta(grafo.buscarVertice(subString[0]), grafo.buscarVertice(subString[1]), distancia);
                grafo.inserirAresta(grafo.buscarVertice(subString[1]), grafo.buscarVertice(subString[0]), distancia);
            }
        }
        catch(FileNotFoundException e){
            return "Arquivo de texto não encontrado!";
        }
        catch(IOException e){
            return "Dados inválidos e/ou incompreensíveis no arquivo!";
        }
        return "Grafo criado com sucesso";
    }

}
