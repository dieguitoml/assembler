package ast;

public class TipoConstante extends Tipo{
    private Tipo tipo;
    
    public TipoConstante(Tipo tipo) {
        this.tipo = tipo;
        this.t = TipoKind.CONST;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public Tipo getType() {
        return tipo;
    }
    
    @Override
    public boolean equals(Tipo t) {
    	return (t instanceof TipoConstante) && (tipo.equals(t.getType()));
    }

    @Override
    public String toString() {
        return "CONST(" + tipo.toString() + ")";
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

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.tipo.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return tipo.calcularMemoria();
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return this.calcularMemoria() + dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		tipo.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tipo= tipo.simplifica();
		return tipo;
	}
    
}