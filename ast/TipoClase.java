package ast;

import java.util.ArrayList;
import java.util.List;

public class TipoClase extends Tipo {
    private Identificador nombre;
    private List<E> parametros;
    private List<Parametro> param;
    private ASTNode vinculo;

    public TipoClase(Identificador nombre, List<Parametro> list, ASTNode vinculo) {
        this.nombre = nombre;
        this.param = list;
        this.t = TipoKind.CLASE;
        this.vinculo = vinculo;
    }
    
    public TipoClase(Identificador nombre, List<E> parametros) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.t = TipoKind.CLASE;
    }
    
    public List<ASTNode> getVinculo() {
    	List<ASTNode> l = new ArrayList<>();
    	l.add(vinculo);
    	return l;
    }
    
    public TipoClase() {
		// TODO Auto-generated constructor stub
	}
    
    public TipoClase(Identificador nombre2) {
		// TODO Auto-generated constructor stub
    	this.nombre= nombre2;
	}

	public String getIden() {
    	return this.nombre.name();
    }

	@Override 
    public boolean equals(Tipo t) {
    	return (t.get() == TipoKind.CLASE) && ((TipoClase) t).getIden().equals(nombre.name());
    }

    @Override
    public String toString() {
        return "TipoClase (" + nombre + "," + parametros + ")";
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generate_code_desig(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generate_code_expr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tam = 0;
		for(E p: parametros) {
			tam += p.calcularMemoria();
		}
		return tam;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		
		return dirPadre+calcularMemoria();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		return this;
	}

    
}
