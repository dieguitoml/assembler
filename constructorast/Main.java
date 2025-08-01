package constructorast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;
import ast.Binding;
import ast.S;
import errors.GestionErroresExp;

public class Main {
   public static void main(String[] args) throws Exception {
	 Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 ConstructorASTExp constructorast = new ConstructorASTExp(alex);
     S raizAST = (S) constructorast.parse().value; 
	 if(!GestionErroresExp.HayErrores) {
		 System.out.println(raizAST);
		 raizAST.bind();
		 raizAST.simplifica();
		 if(!raizAST.errorTipado) {
			 //LLamar a tipado
			 raizAST.chequea();
		 }
		 
		 //LLamar a generacion de codigo **DESCOMENTAR**
		 /*raizAST.asignarTamanosMemoriaTipos();
		 raizAST.calcularMemoria();
		 raizAST.asingarDesplazamiento(0);
		 
		 StringBuilder code_builder = new StringBuilder();
		 raizAST.generate_code_expr(code_builder, 0);
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("salida.wat", false))) {
	         writer.write(code_builder.toString());
	         System.out.println("Archivo guardado correctamente.");
	     } catch (IOException e) {
	         e.printStackTrace();
	     }*/
		 
		 
		 }
	 }
	 
 }

   
