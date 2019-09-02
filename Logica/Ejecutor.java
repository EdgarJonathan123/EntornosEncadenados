/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author jonathan
 */
public class Ejecutor {

    public void Ejecutar(Nodo raiz) {
        recorrer(raiz, null);
    }

    private void recorrer(Nodo raiz, Entorno ent) {

        switch (raiz.etiqueta) {
            case "S":
                recorrer(raiz.hijos.get(0), ent);
                break;
            case "BLOQUE":
                if (raiz.hijos.size() > 0) {
                    Entorno nuevoEnt = new Entorno(ent);
                    recorrer(raiz.hijos.get(0), nuevoEnt);
                }
                break;
            case "LINSTRUCCIONES":
                for (int i = 0; i < raiz.hijos.size(); i++) {
                    recorrer(raiz.hijos.get(i), ent);
                }
                break;
                
            case "DECLARACION":
                ejecutarDecla(raiz, ent);
                break;
            case "IMPRIMIR":
                ejecutarImprimir(raiz, ent);
                break;

        }
    }

    public void ejecutarDecla(Nodo raiz, Entorno ent) {

        Expresion result = solveExpresion(raiz.hijos.get(2), ent);

        Tipo tipoVariable = getTipo(raiz.hijos.get(0).valor);
        String id = raiz.hijos.get(1).valor;

        if (tipoVariable != result.tipo) {
            System.err.println("EL tipo que se le quiere asignar a la variable '" + id + "' no es permitido. '" + tipoVariable + "' = '" + result.tipo + "'. Fila: " + raiz.linea + " Columna: " + raiz.columna);
            return;
        }

        Simbolo nuevo = new Simbolo(tipoVariable, result.valor);
        ent.insertar(id, nuevo, raiz.linea, raiz.columna);

    }

    public void ejecutarImprimir(Nodo raiz, Entorno ent) {

        Expresion result = solveExpresion(raiz.hijos.get(0), ent);
        
        if (result.tipo != Tipo.error) {
            System.out.println(result.valor);
        }
    }

    public Expresion solveExpresion(Nodo raiz, Entorno ent) {

        switch (raiz.etiqueta) {

            case "ENTERO":
                return new Expresion(Tipo.entero, raiz.valor);
            case "DOBLE":
                return new Expresion(Tipo.doble, raiz.valor);
            case "CADENA":
                return new Expresion(Tipo.cadena, raiz.valor);
            case "ID":
                //tengo que ver si la variable existe
                Simbolo sim = ent.Buscar(raiz.valor, raiz.linea, raiz.columna);
                if (sim != null) {
                    return new Expresion(sim.tipo, sim.valor);
                }
                break;
        }

        return new Expresion(Tipo.error, "@error@");
    }

    public Tipo getTipo(String tipo) {

        switch (tipo.toLowerCase()) {

            case "int":
                return Tipo.entero;
            case "double":
                return Tipo.doble;
            case "string":
                return Tipo.cadena;
            default:
                return Tipo.error;

        }
    }
}
