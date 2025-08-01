package ast;

import java.util.List;

public class BloqueInteriorFuncion extends ASTNode{
    private List<Parametro> parametros;
    private BloqueFuncion bloque; 
    
    private int tamParametros;

    public BloqueInteriorFuncion(List<Parametro> parametros, BloqueFuncion bloque) {
        this.parametros = parametros;
        this.bloque = bloque;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public BloqueFuncion getBloque() {
        return bloque;
    }
    

    @Override
    public String toString() {
        return "BloqueInteriorFuncion (" + parametros.toString() + ", " + bloque.toString() + ")";
    }
    
    public List<Parametro> getLista(){
    	return this.parametros;
    }
    
    @Override
    public int asignarTamanoMemoriaMarcos() {
		int tam_mem = 12;
		for(Parametro p: parametros) {
			if(p.getReference()) {//Es por referencia
				tam_mem+=4;//Ocupa 4 bytes
			}else {
				tam_mem+=p.calcularMemoria();
			}
		}
		tam_mem +=bloque.asignarTamanoMemoriaMarcos();
		return tam_mem;
	}
	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.bloque.asignarTamanosMemoriaTipos();
		for(Parametro p: parametros) {
			p.asignarTamanosMemoriaTipos();
		}
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tama = 0;
		tama+= this.bloque.calcularMemoria();
		for(Parametro p: parametros) {
			tama += p.calcularMemoria();
		}
		return tama;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		desplazamiento = dirPadre;
		int dirLocal = dirPadre;
		for(Parametro p: parametros) {
			dirLocal = p.asingarDesplazamiento(dirLocal);
		}
		bloque.asingarDesplazamiento(dirLocal);
		return dirPadre;
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
	public void setType(Tipo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeKind nodeKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
    	for(Parametro p: parametros) {//Insertamos los parametros en la tabla
    		p.isGlobal = false;
    		b.insert(p.getNombre(), p);
    		p.bind();
    		p.set_ambito();
    	}
    	b.bindInterior(bloque);
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}
	public Tipo simplifica() {
		for(Parametro pp: parametros) {
			pp.simplifica();
		}
		bloque.simplifica();
		return null;
	}

}
