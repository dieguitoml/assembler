package ast;

import java.util.HashMap;
import java.util.List;

public class DeclaracionClase extends Declaracion {

	TipoClase t;
    Identificador nombre;
    DeclaracionFuncion constructor;
    BloqueFuncion bloque;

    public DeclaracionClase(Identificador id, DeclaracionFuncion construct, BloqueFuncion b) {
        this.nombre = id;
        this.constructor = construct;
        this.bloque = b;
        this.direccionable = false;
        this.t = new TipoClase(id, construct.getBloque().getParametros(), this);
    }

    public Identificador getId() {
    	return this.nombre;
    }

    public DeclaracionFuncion getConstructor() {
        return constructor;
    }

    public BloqueFuncion getDeclaraciones() {
        return bloque;
    }

    @Override
    public String toString() {
        return "DeclaracionClase (nombre=" + nombre + ", constructor=" + constructor + ", bloque=" + bloque + ")";
    }

	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return new TipoClase(nombre);
	}

	public Declaracion valid_argument(E id) {
		if(id instanceof Identificador) {
			for(ASTNode dd: bloque.getHijos()) {
				if(dd instanceof Declaracion) {
					Declaracion decl = (Declaracion) dd;
					if(decl.getIdd().name().equals(((Identificador) id).name())) {
						return decl;
					}
				}
			}
		}else if(id instanceof InstruccionLlamadaFuncion){//
			InstruccionLlamadaFuncion il = (InstruccionLlamadaFuncion) id;
			for(ASTNode dd: bloque.getHijos()) {
				if(dd instanceof DeclaracionFuncion) {
					DeclaracionFuncion decl = (DeclaracionFuncion) dd;
					if(decl.getIdd().name().equals(il.getFuncion().getNombre())) {
						boolean tipan = true;
						int i = 0;
						List<E> p = il.getParametros();
						for(Parametro pp: ((DeclaracionFuncion) dd).getBloque().getParametros()) {
							p.get(i).chequea();
							if(!pp.getType().equals(p.get(i).getType())) {
								break;
							}
							i++;
						}
						if(tipan)return decl;
					}
				}
			}
		}
		return null;
	}

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		constructor.generate_code_instr(code_builder, identado);
		bloque.generate_code_instr(code_builder, identado);
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
		this.constructor.asignarTamanosMemoriaTipos();
		this.bloque.asignarTamanosMemoriaTipos();
		
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tam = 0;
		List<ASTNode> l = bloque.getHijos();
		for(ASTNode a:l) {
			if(a instanceof DeclaracionVariable || a instanceof DeclaracionVariableConAsign) {
				tam+=a.calcularMemoria();
			}
		}
		this.tam_memoria = tam;
		return 0;
	}
	
	@Override
	public int asignarTamanoMemoriaMarcos() {
		constructor.asignarTamanoMemoriaMarcos();
		bloque.asignarTamanoMemoriaMarcos();
		return 0;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		int dirLocal = 0;
		List<ASTNode> l = bloque.getHijos();
		for(ASTNode a:l) {
			dirLocal = a.asingarDesplazamiento(dirLocal);
		}
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		b.insert(nombre.name(), this);
		b.openScope();
		b.bindInterior(bloque);
		constructor.bind();
		b.closeScope();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		constructor.chequea();
		bloque.chequea();
	}

	@Override
	protected Identificador getIdd() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		//Nothing to simplify
		return null;
	}

    

}
