package ast;

public class InstruccionFor extends Instruccion{

    DeclaracionVariableConAsign variableIteracion;
    E condicionTerminacion;
    ASTNode incremento;
    BloqueFuncion interiorFor;

    public InstruccionFor(DeclaracionVariableConAsign v, E cond, ASTNode i, BloqueFuncion interior){
        this.variableIteracion=v;
        this.condicionTerminacion = cond;
        this.incremento = i;
        this.interiorFor=interior;
        this.setSoyBucle(true);
    }

    public DeclaracionVariableConAsign getVariableIteracion() {
        return variableIteracion;
    }

    public E getCondicionTerminacion() {
        return condicionTerminacion;
    }

    public ASTNode getIncremento() {
        return incremento;
    }

    public BloqueFuncion getInteriorFor() {
        return interiorFor;
    }

    @Override
    public String toString() {
        return "InstruccionFor(" + variableIteracion.toString() + "," 
                + condicionTerminacion.toString() + "," 
                + incremento.toString() + "," 
                + interiorFor.toString() + ")";
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		String indent = StringUtils.generate_identation(identado);

		variableIteracion.generate_code_instr(code_builder, identado);

		code_builder.append(indent + "block\n");
		String indent1 = StringUtils.generate_identation(identado + 1);
		code_builder.append(indent1 + "loop\n");
		String indent2 = StringUtils.generate_identation(identado + 2);

		condicionTerminacion.generate_code_expr(code_builder, identado + 2);
    	code_builder.append(indent2 + "i32.eqz\n");
    	code_builder.append(indent2 + "br_if 1\n"); 

		interiorFor.generate_code_instr(code_builder, identado + 2);

		incremento.generate_code_instr(code_builder, identado + 2);
		code_builder.append(indent2 + "br 0\n");

		code_builder.append(indent1 +"end\n");
    	code_builder.append(indent + "end\n");
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
		this.condicionTerminacion.asignarTamanosMemoriaTipos();
		this.incremento.asignarTamanosMemoriaTipos();
		this.interiorFor.asignarTamanosMemoriaTipos();
		this.variableIteracion.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tam = 0;
		tam += variableIteracion.calcularMemoria();
		tam += interiorFor.calcularMemoria();
		return tam;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		int dirLocal = dirPadre;
		dirLocal = variableIteracion.asingarDesplazamiento(dirLocal);
		dirLocal = interiorFor.asingarDesplazamiento(dirLocal);
		return dirLocal;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		b.openScope();
    	variableIteracion.bind();
    	variableIteracion.isGlobal = false;
    	condicionTerminacion.bind();
    	incremento.bind();
    	b.bindInterior(interiorFor);
    	b.closeScope();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		variableIteracion.chequea();
		condicionTerminacion.chequea();
		incremento.chequea();
		if(!(variableIteracion.getType() instanceof TipoInt)){
			System.out.print("La variable de iteracion del for tiene que ser de tipo entero.\n");
			ASTNode.errorTipado = true;
		}
		interiorFor.chequea();
	}
	
	@Override
	public Tipo simplifica() {
		variableIteracion.simplifica();
		interiorFor.simplifica();
		return null;
	}

    
}
