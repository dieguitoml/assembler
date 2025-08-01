package ast;

import java.util.List;

public class AccesoPuntero extends E {
		  
			private final ASTNode objeto;  // El objeto; // La propiedad
			private Tipo t;
		    public AccesoPuntero(ASTNode puntero) {
		        this.objeto = puntero;
		    }
		    
		    public Tipo getType() {
		    	return this.t;
		    }
		    
		    public Tipo getTipo() {
		    	return this.t.getTipo();
		    }

		    public List<ASTNode> getVinculo(){
		    	return objeto.getVinculo();
		    }
		    @Override
		    public String toString() {
		        return "AccesoPuntero(" + objeto.toString() + ")";
		    }

			@Override
			public KindE kind() {
				// TODO Auto-generated method stub
				return KindE.ACCESO_PUNT;
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
				String indent = StringUtils.generate_identation(identado);

				((E)objeto).generate_code_expr(code_builder, identado);
				
				code_builder.append(indent).append("i32.load\n");
				
			}
			
			@Override
			public void generate_code_expr_mem(StringBuilder code_builder, int identado) {
				String indent = StringUtils.generate_identation(identado);
				((E)objeto).generate_code_expr(code_builder, identado);
			}

			@Override
			public void asignarTamanosMemoriaTipos() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int calcularMemoria() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int asingarDesplazamiento(int dirPadre) {
				// TODO Auto-generated method stub
				return objeto.asingarDesplazamiento(dirPadre);
			}

			@Override
			public void bind() {
				// TODO Auto-generated method stub
				objeto.bind();
			}
			
			@Override
			public void bind_externo() {
				objeto.bind_externo();
			}
			
			

			@Override
			public void chequea() {
				// TODO Auto-generated method stub
				objeto.chequea();
				Tipo t = objeto.getTipo();

				while(t instanceof TipoIdentificador) {
					t = t.getType();
				}
				if(t == null) {
					System.out.print("Error en acceso a direccion.\n");
				}else {
					this.t = t;
				}
			}

			
}


