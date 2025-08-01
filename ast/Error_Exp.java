package ast;

public class Error_Exp extends E {
    
    public Error_Exp() {
        
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        // Utils.appendIndent(str, indentation);
        str.append("ERROR EXP\n");
        return str.toString();
    }

    @Override
    public void bind() {
        // Nothing to do
    }

	@Override
	public KindE kind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chequea() {
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
		return 0;
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
}
