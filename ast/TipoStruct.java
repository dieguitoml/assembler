package ast;

import java.util.ArrayList;
import java.util.List;

public class TipoStruct extends Tipo {

    Identificador struct;
    ASTNode vinculo;
    
    public TipoStruct(Identificador struct) {
        this.struct = struct;
        this.t = TipoKind.STRUCT;
    }
    
    public TipoStruct() {
		// TODO Auto-generated constructor stub
	}
    public TipoStruct(Identificador struct, ASTNode vinculo) {
    	this.struct = struct;
    	this.vinculo = vinculo;
    }
    
    public String getIden() {
    	return this.struct.name();
    }
    
    public boolean equals(Tipo t) {
    	return (t.getType() instanceof TipoStruct) && struct.name().equals(((TipoStruct)t.getType()).getIden());
    }


	public Identificador getStruct() {
        return struct;
    }
    
    public List<ASTNode> getVinculo(){
    	List<ASTNode> l = new ArrayList<>();
    	l.add(vinculo);
    	return l;
    }


    @Override
    public String toString() {
        return "TipoStruct (" + struct + ")";
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
		this.vinculo = b.lookup(struct.name()).get(0);
		
	}


	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		return this;
	}


    
}
