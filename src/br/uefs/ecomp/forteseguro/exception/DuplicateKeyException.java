/**
 * Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Kevin Cerqueira Gomes e Allan Capistrano de Santana Santos>
 * Data:  <11/08/2019>
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

public class DuplicateKeyException extends Exception{
    private static final long serialVersionUID = 12342L;
    
    public DuplicateKeyException(String mensagem){
        //Acessa o contrutor de Exception (Classe Pai)
        super(mensagem);
    }
    
    public DuplicateKeyException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}   