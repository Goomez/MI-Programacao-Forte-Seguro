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
package br.uefs.ecomp.forteseguro.util;

/**
 *
 * @author Kevin
 */

import java.io.Serializable;
import java.util.Objects;

public class Edge<T> implements Serializable {

    private T origin;
    private T destiny;
    private double weight;

    public Edge(T origin, T destiny, double weight) {
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;
    }

    public T getOrigin() {
        return origin;
    }

    public void setOrigin(T origin) {
        this.origin = origin;
    }

    public T getDestiny() {
        return destiny;
    }

    public void setDestiny(T destiny) {
        this.destiny = destiny;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge = (Edge) obj;
            if (this.origin.equals(edge.getOrigin()) && this.destiny.equals(edge.getDestiny())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.origin);
        hash = 11 * hash + Objects.hashCode(this.destiny);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return origin.toString() + destiny.toString();
    }
}
