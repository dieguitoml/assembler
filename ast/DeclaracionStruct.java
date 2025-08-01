package ast;

import java.util.HashMap;
import java.util.List;

public class DeclaracionStruct extends Declaracion {
    private Identificador nombre;
    private List<Declaracion> argumentos;
    private Tipo t;
    private HashMap<String, Declaracion> mapa = new HashMap<String, Declaracion>();
    
    public DeclaracionStruct(Identificador id, List<Declaracion> argumentos) {
        this.nombre = id;
        this.argumentos = argumentos;
        for(Declaracion d: argumentos) {
        	mapa.put(d.getIdd().name(), d);
        }
        this.direccionable = false;
        this.t =  new TipoStruct(this.nombre, this);
    }

    public Identificador getNombre() {
        return nombre;
    }
    
    public Declaracion getIden(String nombreCampo) {
    	return mapa.get(nombreCampo);
    }

    public List<Declaracion> getArgumentos() {
        return argumentos;
    }
    
    public void generate_code_atri(StringBuilder code_builder, int identado, int desplazamientoPadre) {
    	for(Declaracion d: argumentos) {
    		d.desplazamiento +=desplazamientoPadre;
    		d.generate_code_instr(code_builder, identado);
    		d.desplazamiento -=desplazamientoPadre;
    	}
    }

    @Override
    public String toString() {
        return "DeclaracionStruct (" +  nombre.toString() + ", " + argumentos.toString() + "]";
    }

	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return t;
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
		for(Declaracion d:argumentos) {
			d.asignarTamanosMemoriaTipos();
		}
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tam = 0;
		for(Declaracion d: argumentos) {
			tam += d.calcularMemoria();
		}
		this.tam_memoria = tam;
		return 0;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {//Cuando nos metemos en un struct la direccion se reinicia
		// TODO Auto-generated method stub
		int dirLocal = 0;
		for(int i = 0; i<argumentos.size();i++) {
			Declaracion d = argumentos.get(argumentos.size()-1-i);
			dirLocal = d.asingarDesplazamiento(dirLocal);
		}
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		b.insert(nombre.name(), this);
		b.openScope();
		for(Declaracion d: argumentos) {
			d.bind();
		}
		b.closeScope();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		for(Declaracion d:argumentos) {
			d.chequea();
		}
	}

	@Override
	protected Identificador getIdd() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	
	public Declaracion valid_argument(E id) {
		if(id instanceof Identificador) {
			for(Declaracion dd: argumentos) {
				if(dd.getIdd().name().equals(((Identificador) id).name())) {
					return dd;
				}
			}
		}else {
			
		}
		return null;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		for(Declaracion dd: argumentos) {
			dd.simplifica();
		}
		return null;
	}
}
