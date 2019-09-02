/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.LinkedList;

/**
 *
 * @author jonathan
 */
public class Nodo {

    String etiqueta;
    String valor;
    LinkedList<Nodo> hijos;
    int linea;
    int columna;

    public Nodo(String etiqueta, String valor) {
        this.etiqueta = etiqueta;
        this.valor = valor;
        this.hijos = new LinkedList<>();
        this.linea = -1;
        this.columna = -1;

    }
    
    public Nodo(String etiqueta) {
        this.etiqueta = etiqueta;
        this.valor = "";
        this.hijos = new LinkedList<>();
        this.linea = -1;
        this.columna = -1;

    }
    
    public Nodo(String etiqueta, int linea, int columna) {
        this.etiqueta = etiqueta;
        this.valor = "";
        this.hijos = new LinkedList<>();
        this.linea = linea;
        this.columna = columna;
    }

    public Nodo(String etiqueta, String valor, int linea, int columna) {

        this.etiqueta = etiqueta;
        this.valor = valor;
        this.hijos = new LinkedList<>();
        this.linea = linea;
        this.columna = columna;
    }

    public void hijo(Nodo hijo) {
        hijos.add(hijo);
    }

}
