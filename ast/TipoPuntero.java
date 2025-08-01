package ast;

import java.util.List;

public class TipoPuntero extends Tipo{
    private Tipo tipo;
    private Tipo tipoapuntado;
    private int numPunt = 1;
    public TipoPuntero(Tipo tipo) {
        this.tipo = tipo;
        this.t = TipoKind.PUNTERO;
    }

    public Tipo getTipo() {
        return this.tipo;
    }
    
    public Tipo getType() {
    	return this;
    
    }
    
    @Override
    public List<ASTNode> getVinculo(){
    	return tipo.getVinculo();
    }
    
    @Override
    public boolean equals(Tipo t) {
    	return (t instanceof TipoPuntero) && (tipo.equals(t.getTipo()));
    }
    
    @Override
    public String toString() {
        return "TipoPuntero (" + tipo.toString() + ")";
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
	
	public int getNum() {
		return numPunt;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tipoapuntado = tipo.simplifica();
		while(tipoapuntado instanceof TipoPuntero) {
			tipoapuntado = tipoapuntado.simplifica();
			
			numPunt++;
		}
		tipo.simplifica();
		return tipoapuntado;
	}
    
}
