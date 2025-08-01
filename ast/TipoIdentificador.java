package ast;

import java.util.List;

public class TipoIdentificador extends Tipo {
    private String nombre;
    private Tipo tt;
    private List<ASTNode> vinculo;
    
    public TipoIdentificador(String nombre) {
        this.nombre = nombre;
        t = TipoKind.IDEN;
    }
    
    @Override 
    public boolean equals(Tipo t) {
    	boolean eq =  tt.equals(t.getType());
    	if(t instanceof TipoIdentificador) {
    		eq = eq && (((TipoIdentificador)t).getId().equals(nombre));
    	}
    	return eq;
    }
    
    public void setType(Tipo t) {
    	this.tt = t;
    }
    @Override
    public Tipo getType() {
        return this.tt;
    }
    
    public void vinculo(List<ASTNode> list) {
    	this.vinculo = list;
    }
    
    @Override
    public List<ASTNode> getVinculo() {
    	return vinculo;
    }
    
    public String getId() {
    	return this.nombre;
    }

    @Override
    public String toString() {
        return "TipoIdentificador(" +  nombre + ")";
    }

	@Override
	public NodeKind nodeKind() {
		// TODO Auto-generated method stub
		return NodeKind.IDENTIFICADOR;
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
		this.tam_memoria = vinculo.get(0).tam_memoria;
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return this.tam_memoria;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return this.calcularMemoria() + dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		this.vinculo(b.lookup(nombre));
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		this.tt = vinculo.get(0).getType();
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tt = vinculo.get(0).getType();
		while(this.tt instanceof TipoIdentificador) {
			this.tt = vinculo.get(0).getType();
		}
		return tt;
	}

}

