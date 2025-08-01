package ast;

public class InstruccionCIN extends Instruccion{

    E entrada;

    public InstruccionCIN(E entrada){
        this.entrada=entrada;
    }

    public E getEntrada() {
        return entrada;
    }

    @Override
    public String toString() {
        return "InstruccionCIN(" + entrada.toString() + ")"; 
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		entrada.generate_code_expr(code_builder, identado);
		code_builder.append(indent + "call $read\n");
		code_builder.append(indent + "i32.store\n");
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
		entrada.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		entrada.chequea();
		if(entrada.getType() instanceof TipoConstante) {
			System.out.print("No se puede modificar una constante\n");
			ASTNode.errorTipado = true;
		}
	}

    
}
