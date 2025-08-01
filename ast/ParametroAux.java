package ast;

public class ParametroAux extends ASTNode{
		private Tipo tipo;
		private String nombre;
		
		// Constructor
	    public ParametroAux(Tipo tipo, String nombre) {
	        this.tipo = tipo;
	        this.nombre = nombre;
	    }

	    public Tipo getTipo() {
	        return tipo;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    @Override
	    public String toString() {
	        return  "";
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
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'getType'");
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
			
		}

}
