package errors;

import alex.UnidadLexica;

public class GestionErroresExp {
   public static boolean HayErrores = false;
   public void errorLexico(int fila, int columna, String lexema) {
	 GestionErroresExp.HayErrores = true;
     System.out.println("ERROR fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
	 GestionErroresExp.HayErrores = true;
     if (unidadLexica.lexema() != null) {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado");
     }
   }
}
