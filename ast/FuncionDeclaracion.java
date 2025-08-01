package ast;

import java.util.List;

public class FuncionDeclaracion extends Declaracion{
	  	private Tipo tipoRetorno; // El tipo de retorno de la función
	    private String nombre; // El nombre de la función
	    private List<Parametro> parametros; // Lista de parámetros
	    private BloqueFuncion bloque; // Bloque de código de la función
	    //Ojo los hijos de la funcion serán los parámetros + el bloque de la función 
	    // Constructor
	    public FuncionDeclaracion(Tipo tipoRetorno, String nombre, List<Parametro> parametros, BloqueFuncion bloque) {
	        this.tipoRetorno = tipoRetorno;
	        this.nombre = nombre;
	        this.parametros = parametros;
	        this.bloque = bloque;
	    }

	    public Tipo getTipoRetorno() {
	        return tipoRetorno;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public BloqueFuncion getBloque() {
	        return bloque;
	    }

	    @Override
	    public String toString() {
	        return "FuncionDeclaracion(" + tipoRetorno.toString() + "," + nombre + "," + parametros.toString() + "," + bloque.toString();
	    }

}
