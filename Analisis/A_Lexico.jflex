/*-----------1ra Area: Codigo de Usuario-------------------------------*/

//--------------> Paquetes, importaciones
package Analisis;
import java_cup.runtime.*;
import Analisis.TablaError;

/*-----------2da Area: Opciones y Declaraciones-------------------------------*/
%%  

%{
    //--------->Codigo de Usuario en sintaxis Java
    public TablaError tablaEL = TablaError.getInstance();

    Boolean bandera = false;

    private void imprimir(String contenido){

        if(bandera){
            System.out.println(contenido);
        }
    }

%}

//------------>Directivas de JFlex
%public
%class ALexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode
%8bit


//-----> Simbolos
//Simbolos

/* COMA   = ","
PTCOMA = ";" */

//------------------>Expresiones Regulares

SPACE   = [\ \r\t\f\t]
ENTER   = [\ \n]

letra    = [A-Za-zñÑ]

digito  = [0-9]+
entero = "-"? {digito}+

decimal = "-"? {digito}+ "." {digito}+

identificador = {letra}({letra}|{entero}|"_")*


//identificador =  [A-Za-zñÑ]

cadena = "\""~"\""
comentS = "//" ~(\n|\r)     //Comentario simple
comentM = "/*"~ "*/"        //Comentario Multilinea


//------------------> Estados
%%
/*-----------3ra Area: Reglas Lexicas-------------------------------*/




//------------------->Simbolos

<YYINITIAL>     ";" {imprimir("Simbolo: ["+yytext()+"] coma");
                    return new Symbol(Simbolos.ptoComa,yyline,yychar, yytext());}                                          

<YYINITIAL>     "=" {imprimir("Simbolo: ["+yytext()+"] igual"); 
                    return new Symbol(Simbolos.igual,yyline,yychar, yytext());}

<YYINITIAL>     "{" {imprimir("Simbolo: ["+yytext()+"] llave abierta"); 
                    return new Symbol(Simbolos.llaveA,yyline,yychar, yytext());}
                    
<YYINITIAL>     "}" {imprimir("Simbolo: ["+yytext()+"] llave Cerrada"); 
                    return new Symbol(Simbolos.llaveC,yyline,yychar, yytext());}  

<YYINITIAL>     "(" {imprimir("Simbolo: ["+yytext()+"] llave abierta"); 
                    return new Symbol(Simbolos.parA,yyline,yychar, yytext());}
                    
<YYINITIAL>     ")" {imprimir("Simbolo: ["+yytext()+"] llave Cerrada"); 
                    return new Symbol(Simbolos.parC,yyline,yychar, yytext());}                     


//------------------------> Palabras reservadas---------------------

<YYINITIAL> "print" {imprimir("Palabra Reservada: ["+yytext()+"]"); 
                    return new Symbol(Simbolos.PSprint,yyline,yychar, yytext());}
                    
<YYINITIAL> "int" {imprimir("Palabra Reservada: ["+yytext()+"]"); 
                    return new Symbol(Simbolos.PSint,yyline,yychar, yytext());}

<YYINITIAL> "double" {imprimir("Palabra Reservada: ["+yytext()+"]"); 
                    return new Symbol(Simbolos.PSdouble,yyline,yychar, yytext());}

<YYINITIAL> "string" {imprimir("Palabra Reservada: ["+yytext()+"]"); 
                    return new Symbol(Simbolos.PSstring,yyline,yychar, yytext());}


//----------------------->Simbolos Expresiones regulares




<YYINITIAL>     {cadena} {imprimir("Encontro: ["+yytext()+"] Cadena de Texto"); 
                         return new Symbol(Simbolos.cadena,yyline,yychar, yytext().replace("\"", "") );} 
                         
<YYINITIAL>     {entero} {imprimir("Encontro: ["+yytext()+"] Numero entero"); 
                         return new Symbol(Simbolos.entero,yyline,yychar, yytext());} 
                         
<YYINITIAL>     {decimal} {imprimir("Encontro: ["+yytext()+"] Numero decimal"); 
                         return new Symbol(Simbolos.decimal,yyline,yychar, yytext());}  

<YYINITIAL> {identificador} {imprimir("Econtro: ["+yytext()+"]"); 
                            return new Symbol(Simbolos.identificador,yyline,yychar, yytext());}                         




//---------------->Espacios
<YYINITIAL> {SPACE}     { /*Espacios en blanco, ignorados*/ }
<YYINITIAL> {ENTER}     {yychar =0;}
<YYINITIAL> {comentM} {}
<YYINITIAL> {comentS} {}




 //--------------->Errores Lexicos
<YYINITIAL> .   {
                    imprimir("Error Lexico: "+yytext()+", Linea: "+yyline+", Col"+yycolumn);
                    tablaEL.setError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe en el lenguaje");
                }





