/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 *
 * @author jonathan
 */
public class TablaError {

    public static TablaError instancia;

    private LinkedList<TError> tabla;

    private TablaError() {
    }

    public static TablaError getInstance() {

        if (instancia == null) {
            instancia = new TablaError();
        }

        return instancia;
    }

    public void setError(String lexema, int linea, int columna, String tipo, String descripcion) {

        if (tabla != null) {
            TError datos = new TError(lexema, linea, columna, tipo, descripcion);
            tabla.add(datos);

        } else {
            tabla = new LinkedList<>();
            TError datos = new TError(lexema, linea, columna, tipo, descripcion);
            tabla.add(datos);
        }

    }

    /**
     * @return the tabla
     */
    public LinkedList<TError> getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(LinkedList<TError> tabla) {
        this.tabla = tabla;
    }

    public void imprimir() {

        System.out.println("----------------------------Inicio Errores-------------------------");
        if (instancia != null) {

            if (tabla != null) {
                for (TError tError : tabla) {
                    tError.imprimir();
                }

            }

        }
        System.out.println("\n----------------------------Fin Errores----------------------------");
    }

    public String encabazado() {
        String result = "<!DOCTYPE html>\n"
                + "<html >\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <title>Fixed table header</title>\n"
                + "  \n"
                + "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css\">\n"
                + "\n"
                + "  \n"
                + "      <link rel=\"stylesheet\" href=\"css/style.css\">\n"
                + "\n"
                + "  \n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "  <section>\n"
                + "  <!--for demo wrap-->\n"
                + "  <h1>Reporte de Errores </h1>\n"
                + "  <div class=\"tbl-header\">\n"
                + "    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "      <thead>\n"
                + "        <tr>\n"
                + "          <th>Tipo</th>\n"
                + "          <th>Lexema</th>\n"
                + "          <th>Descripcion</th>\n"
                + "          <th>Fila</th>\n"
                + "          <th>Columna</th>\n"
                + "        </tr>\n"
                + "      </thead>\n"
                + "    </table>\n"
                + "  </div>\n"
                + "  <div class=\"tbl-content\">\n"
                + "    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "      <tbody>\n"
                + "\n"
                + "        <!--Empieza el contenido-->";

        return result;

    }

    public String pie() {

        String result = "";

        result = " <!--Termina el contenido-->\n"
                + "\n"
                + "      </tbody>\n"
                + "    </table>\n"
                + "  </div>\n"
                + "</section>\n"
                + "\n"
                + "  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>\n"
                + "\n"
                + "    <script src=\"js/index.js\"></script>\n"
                + "\n"
                + "</body>\n"
                + "</html>";

        return result;
    }

    @Override
    public String toString() {

        String result = "";

        result += encabazado();
        if (tabla != null) {
            for (TError tError : tabla) {
                result += tError.toString();
            }
        }

        result += pie();
        return result;
    }

    public void escribirReporte() {

        try {

            String local = System.getProperty("user.dir");
            String ruta = local + "/ReportesHtml/index.html";

            File archivo = new File(ruta);
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(toString());
                System.out.println("se sobreescribio el archivo");
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(toString());
                System.out.println("se creo el archivo");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("error al escribir reportes");
            System.out.println("Causa: " + e.getCause());
        }

    }

    public String getPath() {

        String local = System.getProperty("user.dir");
        String ruta = local + "/ReportesHtml/index.html";

        return ruta;
    }
}
