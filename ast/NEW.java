package ast;

public class NEW extends E{

    Tipo tipo;
    
    
    public NEW(TipoIdentificador iden) {
    	this.tipo = iden;
    }

    public NEW(Tipo tipo){
        this.tipo = tipo;
    }

    @Override
    public KindE kind() {
        // TODO Auto-generated method stub
        return KindE.NEW;
    }

    @Override
    public String toString() {
        return "NEW (" + tipo.toString() + ")";
    }
    
    @Override
    public Tipo getType() {
    	return this.tipo;
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
		code_builder.append(indent + " ;; NEW EXPRESION INICIO\n");
		code_builder.append(indent + "i32.const " + tipo.calcularMemoria() + "\n");
		code_builder.append(indent + "call $reserveHeap\n");
		code_builder.append(indent + " ;;NEW EXPRESION FIN\n");
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
		tipo.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		tipo.chequea();
		this.tipo= new TipoPuntero(tipo);
	}
	
	@Override
	public Tipo simplifica() {
		tipo.simplifica();
		return null;
	}
    
}
