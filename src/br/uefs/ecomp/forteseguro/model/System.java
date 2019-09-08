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
package br.uefs.ecomp.forteseguro.model;

import br.uefs.ecomp.forteseguro.exception.ArestaDuplicadaException;
import br.uefs.ecomp.forteseguro.exception.VerticeDuplicadoException;
import br.uefs.ecomp.forteseguro.util.Aresta;
import br.uefs.ecomp.forteseguro.util.Grafo;
import br.uefs.ecomp.forteseguro.util.Vertice;
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
        grafo = new Grafo<>();
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
                try{
                    this.grafo.inserir(identificador, tipo);
                    contador++;
                }
                catch(VerticeDuplicadoException v){
                    v.toString();
                }
            }
            /*Lê a parte do arquivo que estão as arestas*/
            while((leitura = br.readLine()) != null){
                String[] subString = leitura.split(" ");
                int distancia = Integer.parseInt(subString[2]);
                
                try{
                    this.grafo.inserirAresta(this.grafo.buscarVertice(subString[0]), 
                            this.grafo.buscarVertice(subString[1]), distancia);
                    this.grafo.inserirAresta(this.grafo.buscarVertice(subString[1]), 
                            this.grafo.buscarVertice(subString[0]), distancia);
                }
                catch(ArestaDuplicadaException a){
                    a.toString();
                }
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
    
    /** Método que adiciona um novo vértice ao grafo.
     * 
     * @param cruzamento String - Identificador do vértice que se deseja adicionar.
     * @param tipo int - 0 - Vértice comum | 1 - Banco | 2 - Coleta | 3 - Estacionamento
     * @return Cruzamento adicionado com sucesso! - Caso o vértice seja adicionado
     * com sucesso | Vértice já está adicionado no grafo! - caso ocorra 
     * algum problema ao tentar inserir determinado vértice.
     */
    public String adicionarCruzamento(String cruzamento, int tipo){
        try{
            this.grafo.inserir(cruzamento, tipo);
            return "Cruzamento adicionado com sucesso!";
        }
        catch(VerticeDuplicadoException v){
            return "Cruzamento já está adicionado no grafo!";
        }
    }
    
    /** Método que adiciona uma nova ligação ao grafo.
     * 
     * @param v1 String - Um dos vértices que faz parte da ligação.
     * @param v2 String - O outro vértice que faz parte da ligação.
     * @param distancia int - Distância entre os dois vértices formadoes.
     * @return Ligação adicionada com sucesso! - Caso a aresta seja adicionada 
     * com sucesso | Aresta já está adicionada no sistema! - Caso ocoorra algum
     * problema ao tentar inserir determinada aresta.
     */
    
    /*Por enquanto está com a distância definida pelo usuário*/
    public String adicionarLigacao(String v1, String v2, int distancia){
        Vertice<String> vertice1 = this.grafo.buscarVertice(v1);
        Vertice<String> vertice2 = this.grafo.buscarVertice(v2);
        
        try{
            this.grafo.inserirAresta(vertice1, vertice2, distancia);
            return "Ligação adicionada com sucesso!";
        }
        catch(ArestaDuplicadaException a){
            return "Ligação já está adicionada no sistema!";
        }
        
    }
    
    /** Método que remove uma determinada aresta do grafo.
     * 
     * @param aresta Aresta - Aresta que se deseja remover.
     * @return Ligação não existe! - Caso não seja possível remover a ligação
     * Ligação removida com sucesso! - Caso a remoção ocorra com sucesso. 
     */
    public String removerLigacao(Aresta aresta){
        if(this.getGrafo().buscarAresta(aresta.getVertice1(), aresta.getVertice2()) == null)
            return "Ligação não existe!";
        this.getGrafo().removerAresta(aresta);
        return "Ligação removida com sucesso!";
    }
    
    /** Método que remove um vértice e suas ligações do grafo.
     * 
     * @param cruzamento String - Identificador do vértice que se deseja remover.
     * @return Cruzamento não existe! - Caso não seja possível remover o cruzamento
     * Cruzamento removido com sucesso! - Caso a remoção ocorra com sucesso.
     */
    public String removerCruzamento(String cruzamento){
         if(this.getGrafo().buscarVertice(cruzamento) == null)
            return "Cruzamento não existe!";
        this.getGrafo().removerVertice(cruzamento);
        return "Cruzamento removido com sucesso!";
    }

    /** Método que altera o tipo do cruzamento
     * 
     * @param cruzamento String - Identificador do vértice que se deseja alterar
     * o tipo.
     * @param tipo int - 0 - Vértice comum | 1 - Banco | 2 - Coleta | 
     * 3 - Estacionamento.
     */
    public void alterarTipoCruzamento(String cruzamento, int tipo){
        Vertice<String> vertice = this.getGrafo().buscarVertice(cruzamento);
        
        if(vertice != null){
            vertice.setTipo(tipo);
        }
    }
    
    /** Método que verifica se existe algum vértice do tipo estacionamento.
     * 
     * @return true - Caso exista | false - Caso contrário.
     */
    public boolean existeEstacionamento(){
        for(Vertice<String> vertice: this.grafo.getVertices()){
            if(vertice.getTipo() == 3)
                return true;
        }
        return false;
    }
    
    /** Método que verifica se o vértice passado como parâmetro existe no grafo.
     * 
     * @param cruzamento String - Identificador do vértice que se deseja procurar
     * no grafo.
     * @return true - Caso exista | false - Caso contrário.
     */
    public boolean existeVertice(String cruzamento){
        Vertice<String> vertice = this.getGrafo().buscarVertice(cruzamento);
        
        return vertice != null;
    }
}
