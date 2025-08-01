package ast;

public class EUnario extends E {

	    private KindE kind;  
	    private ASTNode operand;  

	    public EUnario(KindE kind, ASTNode operand) {
	        this.kind = kind;
	        this.operand = operand;
	        this.direccionable = false;
	    }

		public ASTNode getOp() {
			return operand;  
		}

	    @Override
	    public KindE kind() {
	        return kind;
	    }

	    @Override
	    public String toString() {
	        return kind + "(" + operand.toString() + ")"; 
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
			  String indent = StringUtils.generate_identation(identado); 
			  if(kind == KindE.NEGATIVO) {
				    operand.generate_code_expr(code_builder, identado);
				    code_builder.append(indent + "i32.const 1\n"); 
				    code_builder.append(indent + "i32.sub\n");
				    code_builder.append(indent + "i32.store\n"); 
			  }
		}

		@Override
		public void asignarTamanosMemoriaTipos() {
			// TODO Auto-generated method stub
			this.operand.asignarTamanosMemoriaTipos();
		}

		@Override
		public int calcularMemoria() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int asingarDesplazamiento(int dirPadre) {
			// TODO Auto-generated method stub
			return dirPadre;
		}

		@Override
		public void bind() {
			// TODO Auto-generated method stub
			operand.bind();
		}

		@Override
		public void chequea() {
			// TODO Auto-generated method stub
			operand.chequea();
			Tipo t = operand.getType();
			  switch (this.kind()) {
	           case INCR:
	               if (!(t instanceof TipoInt)) {
	                    //ERROR
	                    this.setType(null);
	                    throw new RuntimeException("Tipo incompatible para la operacion INCR");
	                } else {
	                    this.setType(new TipoInt());
	                }
	                break;
	           case DECR:
	                if (!(t instanceof TipoInt)) {
	                    //ERROR
	                    this.setType(null);
	                    throw new RuntimeException("Tipo incompatible para la operacion DECR");
	                } else {
	                    this.setType(new TipoInt());
	                }
	                break;
	            case NEGATIVO:
	                if (!(t instanceof TipoInt)) {
	                    //ERROR
	                    this.setType(null);
	                    throw new RuntimeException("Tipo incompatible para la operacion NEGATIVO");
	                } else {
	                    this.setType(new TipoInt());
	                }
	                break;
	            case NOT:
	                if (!(t instanceof TipoBool)) {
	                    //ERROR
	                    this.setType(null);
	                    throw new RuntimeException("Tipo incompatible para la operacion NOT");
	                } else {
	                    this.setType(new TipoBool());
	                }
	                break;

	            default:
	                //ERROR
	                this.setType(null);
	        }
		}

}
