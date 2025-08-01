package ast;

public class TipoReference extends Tipo{

    private Tipo tipo;

    public TipoReference(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public Tipo getTipo() {
        return this.tipo;
    }
    
    public Tipo getType() {
    	return this.tipo.getType();
    
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tipo.simplifica();
		return null;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
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
