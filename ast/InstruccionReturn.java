package ast;

public class InstruccionReturn extends Instruccion {
    private E operacion;
    private Tipo t;
    
    public InstruccionReturn(E op){
        this.operacion = op;
    }

    public E getOperacion() {
        return this.operacion;
    }

    public Tipo getType() {
    	return t;
    }

    @Override
    public String toString() {
    	if(operacion == null) {
    		return "return void"; 
    	}
        return "InstruccionReturn(" + operacion.toString() + ")";
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);//Genera la identaci�n
		if(!(t instanceof TipoVoid)) {
			operacion.generate_code_expr(code_builder, identado);
		}
		code_builder.append(indent + "call $freeStack\n");
		code_builder.append(indent + "return\n");
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
        if(operacion !=null) {
        	operacion.bind();
        }	
	}

	@Override
	public void chequea() {
		if(operacion != null) {
			operacion.chequea();
			this.t = operacion.getType();
		}
		else {
			this.t= new TipoVoid();
		}
		
	}
    
}
