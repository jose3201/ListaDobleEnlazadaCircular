/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lde;

/**
 *
 * @author josed
 */
public class Nodo {
    private int dato;
    private Nodo siguiente;
    private Nodo Anterior;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
        this.Anterior = null;
    }
    public Nodo() {
        this.dato = 0;
        this.siguiente = null;
        this.Anterior = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return Anterior;
    }

    public void setAnterior(Nodo Anterior) {
        this.Anterior = Anterior;
    }
    
}
