package ast;


public class Asignacion extends Instruccion {
    private Identificador variable;
    private E expresion;

    // Constructori
    public Asignacion(Identificador variable, E expresion) {
        this.variable = variable;
        this.expresion = expresion;
        this.direccionable = false;
    }
    
    public Identificador getId() {
    	return this.variable;
    }
    
    public E getAsign() {
    	return this.expresion;
    }

    @Override
    public String toString() {
        return "Asignacion (" + variable + " , " + expresion.toString() + ")";
    }

    @Override
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		String indent = StringUtils.generate_identation(identado);//Genera la identaci�n 
		
		variable.generate_code_expr_mem(code_builder, identado);
		expresion.generate_code_expr(code_builder, identado);
		code_builder.append(indent + "i32.load\n");
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
		return variable.calcularMemoria();
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return dirPadre + this.calcularMemoria();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		variable.bind();
		expresion.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		variable.chequea();
		expresion.chequea();
		if(!variable.getType().equals(expresion.getType())) {
			ASTNode.errorTipado=true;
			System.out.print("La asignacion debe ser para expresiones de igual tipo.\n");
		}
	}

}
