/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data: <06/09/2019>
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
package br.uefs.ecomp.forteseguro.exception;

/** Classe de exceção responsável pelo erro de tentar inserir uma aresta que 
 * possui os mesmos atributos de uma aresta que já está adicionada no grafo.
 *
 * @author Kevin e Allan
 */
public class ArestaDuplicadaException extends Exception{

    /**
     * Construtor da classe de exceção. Chama a função "super()", que chama o 
     * construtor da classe pai dessa.
     */
    public ArestaDuplicadaException() {
        super();
    }

    /**
     * Metódo que retorna o erro da exceção.
     * @return String - "Aresta já adicionada no sistema!" caso aconteça a exceção.
     */
    @Override
    public String toString() {
        return "Aresta já adicionada no sistema!";
    }
    
}
