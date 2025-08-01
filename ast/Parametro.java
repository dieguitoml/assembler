package ast;

import java.util.List;

public class Parametro extends ASTNode {
	    private Tipo tipo;
	    private String nombre;
	    private ASTNode aux;
	    private boolean isReference = false;
		
	    
	    public Parametro(ParametroAux pa, List<Dimensiones> d) {
	        this.tipo = new TipoArray(pa.getTipo(), d);
	        this.nombre = pa.getNombre();
	    }
	    
	    public void setReference() {
	    	this.isReference = true;
	    }
	   
	    public boolean getReference() {
	    	return this.isReference;
	    }

	    public Parametro(ParametroAux pa) {
	    	this.tipo = pa.getTipo();
	    	this.nombre = pa.getNombre();
	    	aux = pa;
	    }
	    
	    public Tipo getTipo() {
	        return tipo;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    @Override
	    public String toString() {
	    	return "Parametro(" + tipo.toString() + ", " + nombre + ")";

	        
	    }

		@Override
		public NodeKind nodeKind() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setType(Tipo t) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'setType'");
		}

		@Override
		public Tipo getType() {
			return tipo;
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
			this.tipo.asignarTamanosMemoriaTipos();
		}

		@Override
		public int calcularMemoria() {
			// TODO Auto-generated method stub
			return tipo.calcularMemoria();
		}

		@Override
		public int asingarDesplazamiento(int dirPadre) {
			// TODO Auto-generated method stub
			this.desplazamiento = dirPadre;
			return dirPadre + this.calcularMemoria();
		}

		@Override
		public void bind() {
			// TODO Auto-generated method stub
			tipo.bind();
		}

		@Override
		public void chequea() {
			// TODO Auto-generated method stub
			if(tipo instanceof TipoReference) {
				this.isReference = true;
				tipo = this.tipo.getType();
			}
		}
		public Tipo simplifica() {
			this.tipo.simplifica();
			return null;
		}

}
