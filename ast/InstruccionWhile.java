package ast;

public class InstruccionWhile extends Instruccion {

    private E condicion;
    private BloqueFuncion bloqueInterior;

    public InstruccionWhile(E cond, BloqueFuncion bi){
        this.condicion = cond;
        this.bloqueInterior = bi;
        this.setSoyBucle(true);
    }

    public E getCondicion() {
        return condicion;
    }

    public BloqueFuncion getBloqueInterior() {
        return bloqueInterior;
    }

    @Override
    public String toString() {
        return "while(" + condicion.toString() +"," + bloqueInterior.toString() +")";
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		 String indent = StringUtils.generate_identation(identado); 
		 String indent1 = StringUtils.generate_identation(identado+1);
		 code_builder.append(indent + "block\n");
		 code_builder.append(indent1 + "loop\n");
		 String indent2 = StringUtils.generate_identation(identado+2);
		 condicion.generate_code_expr(code_builder, identado+2);
		 code_builder.append(indent2 + "i32.eqz\n");
		 code_builder.append(indent2 + "br_if 1\n");
		 bloqueInterior.generate_code_instr(code_builder, identado + 2);
		 code_builder.append(indent2 + "br 0\n");
		 code_builder.append(indent1 + "end\n");
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
		this.bloqueInterior.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return bloqueInterior.calcularMemoria();
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		int dirLocal = 0;
		dirLocal = condicion.asingarDesplazamiento(dirLocal);
		dirLocal = bloqueInterior.asingarDesplazamiento(dirLocal);
		return dirPadre;
		
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
    	b.openScope();
    	this.condicion.bind();
    	b.bindInterior(bloqueInterior);
    	b.closeScope();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		this.condicion.chequea();
		this.bloqueInterior.chequea();
		if(!(condicion.getType() instanceof TipoBool)) {
			System.out.print("El while tiene que tener como condicion un tipo booleano.\n");
			ASTNode.errorTipado = true;
		}
	}
	
	@Override
	public Tipo simplifica() {
		bloqueInterior.simplifica();
		return null;
	}
    
}
