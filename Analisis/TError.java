/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis;

/**
 *
 * @author Jonathan
 */
public class TError {

    public String lexema, tipo, descripcion;
    public int linea, columna;

    public TError(String lexema, int linea, int columna, String tipo, String descripcion) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public void imprimir() {
        System.out.print("[Lexema: {" + lexema + "} Fila: {" + linea + "} Columna: {" + columna + "}\n");
        System.out.print(" Tipo de Dato: {" + tipo + "} Descripcion: {" + descripcion + "} ]");
    }

    @Override
    public String toString() {
        String result = "";

        result = "        <tr>\n"
                + "          <td>"+tipo+"</td>\n"
                + "          <td>"+lexema+"</td>\n"
                + "          <td>"+descripcion+"</td>\n"
                + "          <td>"+linea+"</td>\n"
                + "          <td>"+columna+"</td>\n"
                + "        </tr>";

        return result;
    }

}
