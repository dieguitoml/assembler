package ast;

public class InstruccionCOUT extends Instruccion{

    E salida;

    public InstruccionCOUT(E salida){
        this.salida=salida;
    }

    public E getSalida() {
        return salida;
    }

    @Override
    public String toString() {
        return "InstruccionCOUT(" + salida.toString() + ")"; 
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);//Genera la identación
		salida.generate_code_expr(code_builder, identado);
		code_builder.append(indent + "call $print\n");
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
    	salida.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		salida.chequea();
		if(!(salida.getType() instanceof TipoInt)) {
			System.out.print("Solo se pueden mostrar enteros\n");
			ASTNode.errorTipado = true;
		}
	}

    
}