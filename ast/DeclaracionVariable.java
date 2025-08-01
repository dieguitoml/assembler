package ast;

import java.util.List;

public class DeclaracionVariable extends Declaracion {
    private Tipo tipo;       // El tipo de la variable (ejemplo: int, bool, etc.)
    private Identificador id; // El identificador (nombre de la variable)

    public DeclaracionVariable(Tipo tipo, Identificador id) {
        this.tipo = tipo;
        this.id = id;
        this.direccionable = false;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public Identificador getId() {
    	return this.id;
    }
    
    @Override
    public String toString() {
        return "DeclaracionVariable(" + tipo.toString() + " " + id.toString() + ")";
    }

	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return this.tipo.getType();
	}
	
	
	

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method 
		String indent = StringUtils.generate_identation(identado);
		if(tipo instanceof TipoStruct) {
			((DeclaracionStruct)tipo.getVinculo().get(0)).generate_code_atri(code_builder, identado, this.desplazamiento);
		}else {
		code_builder.append(indent + ";; DEC VAR SIN ASIGN" + toString() + "\n");
		code_builder.append(indent+"global.get $MP\n");
		code_builder.append(indent + "i32.const " + desplazamiento + "\n");
		code_builder.append(indent + "i32.add\n");
		code_builder.append(indent + "i32.const 0\n");
		code_builder.append(indent + "i32.store\n");
		}
		
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
		desplazamiento = dirPadre;
		return dirPadre + this.calcularMemoria();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		b.insert(id.name(),this);
		tipo.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		tipo.chequea();
	}

	@Override
	protected Identificador getIdd() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tipo.simplifica();
		return null;
	}

}
