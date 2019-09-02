/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.HashMap;

/**
 *
 * @author jonathan
 */
public class Entorno {

    public HashMap<String, Simbolo> tabla;
    Entorno anterior;

    public Entorno(Entorno anterior) {
        this.anterior = anterior;
        this.tabla = new HashMap<>();
    }

    public void insertar(String nombre, Simbolo sim, int linea, int columna) {

        if (tabla.containsKey(nombre)) {
            System.err.println("La variable '" + nombre + "'ya existe. Fila:  " + linea + " Columna: " + columna);
            return;
        }
        tabla.put(nombre, sim);

    }

    public Simbolo Buscar(String nombre, int linea, int columna) {

        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.tabla.containsKey(nombre)) {
                Simbolo sim = e.tabla.get(nombre);
                return sim;
            }
        }
        System.err.println("La variable '" + nombre + "' No existe. Fila:  " + linea + " Columna: " + columna);
        return null;
    }

}
