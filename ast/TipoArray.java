package ast;

import java.util.ArrayList;
import java.util.List;

public class TipoArray extends Tipo{

    private ASTNode tipoElemento; 
    private List<Dimensiones> dimensiones; 
   
    // Constructor
    public TipoArray(ASTNode tipoElemento, List<Dimensiones> dimensiones) {
        this.tipoElemento = tipoElemento;
        this.dimensiones = dimensiones;
        this.t = TipoKind.ARRAY;
    }
    
    @Override 
    public boolean equals(Tipo t) {
    	if(t.get() == TipoKind.ARRAY) {
    		Tipo tt = ((TipoArray) t).getTipoElemento().getType();
    		int num = ((TipoArray) t).getDimension().size();
    		return tipoElemento.getType().equals(tt) && (num == dimensiones.size());
    	}
    	return false;
    }
    
    public Tipo getTipo() {
    	return this;
    }
    
    public Tipo getType() {
    	return tipoElemento.getType();
    }

    public ASTNode getTipoElemento() {
        return tipoElemento;
    }

    public List<Dimensiones> getDimension() {
        return dimensiones;
    }
    
    public int getNumDimensiones() {
    	int tamDimensiones = 1;
		for(Dimensiones d : dimensiones) {
			tamDimensiones = tamDimensiones * d.getTamDimension().getV();
		}
		return tamDimensiones;
    }
    
    @Override
    public  List<ASTNode> getVinculo() {
		return tipoElemento.getVinculo();
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TipoArray(");
        sb.append(tipoElemento.toString());
        sb.append(",");
        if (dimensiones != null && !dimensiones.isEmpty()) {
            for (int i = 0; i < dimensiones.size(); i++) {
                sb.append(dimensiones.get(i).toString());
                if (i < dimensiones.size() - 1) {
                    sb.append(",");
                }
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        return sb.toString();
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
		this.tipoElemento.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		
		int tamDimensiones = getNumDimensiones();
		int tam = tipoElemento.calcularMemoria()*tamDimensiones;
		return tam;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return dirPadre + this.calcularMemoria();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		tipoElemento.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		return (tipoElemento).simplifica();
	}

}
