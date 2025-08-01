package ast;

public class InstruccionIf extends Instruccion{

    private E condicionIf;
    private BloqueFuncion bloqueInteriorIf;
    private boolean existsElse;
    private BloqueFuncion bloqueInteriorElse;

    public InstruccionIf(E condIf, BloqueFuncion biIf){
        this.condicionIf = condIf;
        this.bloqueInteriorIf = biIf;
    }

    public InstruccionIf(E condIf, BloqueFuncion biIf, boolean exElse, BloqueFuncion biElse){
        this.condicionIf = condIf;
        this.bloqueInteriorIf = biIf;
        this.existsElse = exElse;
        this.bloqueInteriorElse = biElse;
    }

    public E getCondicionIf() {
        return condicionIf;
    }

    public BloqueFuncion getBloqueInteriorIf() {
        return bloqueInteriorIf;
    }

    public boolean isExistsElse() {
        return existsElse;
    }

    public BloqueFuncion getBloqueInteriorElse() {
        return bloqueInteriorElse;
    }

    @Override
    public String toString() {
        String salida = "InstruccionIf(" + condicionIf.toString() + "," +bloqueInteriorIf.toString() + ",";
        if(existsElse) salida += "else ," + bloqueInteriorElse.toString();
        salida+=")";
        return salida;
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		String indent = StringUtils.generate_identation(identado);//Genera la identaci�n 
		this.condicionIf.generate_code_expr(code_builder,identado);
		code_builder.append(indent + "if\n");
		bloqueInteriorIf.generate_code_instr(code_builder, identado+1, desplazamiento);
		if(this.existsElse) {
			code_builder.append(indent + "else \n");
			this.bloqueInteriorElse.generate_code_instr(code_builder, identado+1);
			
		}
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
		this.bloqueInteriorIf.asignarTamanosMemoriaTipos();
		if(this.existsElse)this.bloqueInteriorElse.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tamIf = this.bloqueInteriorIf.calcularMemoria();
		if(this.existsElse) {
			 int tam= this.bloqueInteriorElse.calcularMemoria();
			 tamIf = Math.max(tamIf,tam);
		}
		return tamIf;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		int dirLocal = dirPadre;
		dirLocal = bloqueInteriorIf.asingarDesplazamiento(dirLocal);
		if(existsElse) {
			dirLocal = bloqueInteriorElse.asingarDesplazamiento(dirLocal);
		}
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		condicionIf.bind();
    	b.openScope();
    	b.bindInterior(bloqueInteriorIf);
    	b.closeScope();
    	if(existsElse) {
    		b.openScope();
    		b.bindInterior(bloqueInteriorElse);
    		b.closeScope();
    	}
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		this.condicionIf.chequea();
		if(!(condicionIf.getType() instanceof TipoBool)){
			System.out.print("La condicion tiene que ser de tipo booleano.\n");
			ASTNode.errorTipado = true;
		}
		this.bloqueInteriorIf.chequea();
		if(this.isExistsElse()) {
			this.getBloqueInteriorElse().chequea();
		}
	}
	
	@Override
	public Tipo simplifica() {
		bloqueInteriorIf.simplifica();
		if(this.existsElse) {
			bloqueInteriorElse.simplifica();
		}
		return null;
	}
    
}
