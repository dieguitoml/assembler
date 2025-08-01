package ast;

import java.util.List;
import java.util.ArrayList;

public class S2 extends ASTNode {

    private List<Declaracion> declaraciones;  

    public S2(List<Declaracion> declaraciones) {
        this.declaraciones = declaraciones != null ? declaraciones : new ArrayList<>();
    }

    public S2() {
        this.declaraciones = new ArrayList<>();
    }

    public void addDeclaracion(Declaracion declaracion) {
        this.declaraciones.add(declaracion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("S2(");
        for (int i = 0; i < declaraciones.size(); i++) {
            sb.append(declaraciones.get(i).toString());
            if (i < declaraciones.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    

    @Override
    public NodeKind nodeKind() {
        return NodeKind.DECLARACION;  
    }

    public List<Declaracion> getDeclaraciones() {
        return declaraciones;
    }

    @Override
    public void setType(Tipo t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setType'");
    }

    @Override
    public Tipo getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		for (Declaracion d : declaraciones) {
			if(d instanceof DeclaracionFuncion || d instanceof DeclaracionClase) {
				d.generate_code_instr(code_builder, identado);
			}
	    }
	}

	@Override
	public void generate_code_desig(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generate_code_expr(StringBuilder code_builder, int identado) {
		for (Declaracion d : declaraciones) {
			if(!(d instanceof DeclaracionFuncion) && !(d instanceof DeclaracionClase)) {
				d.generate_code_instr(code_builder, identado+1);
			}
	    }
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		for(Declaracion d : declaraciones) {
			d.asignarTamanosMemoriaTipos();
		}
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tam = 0;
		for(Declaracion d : declaraciones) {
			tam+= d.calcularMemoria();
		}
		return tam;
	}

	@Override//REVISAR
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		int dirLocal = dirPadre;
		for(Declaracion d: declaraciones) {
			dirLocal = d.asingarDesplazamiento(dirLocal);
		}
		return dirLocal;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		for(Declaracion dd: declaraciones) {
			dd.bind();
		}
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		for(Declaracion dd: declaraciones) {
			dd.chequea();
		}
	}
	public Tipo simplifica() {
		for(Declaracion dd:declaraciones) {
			dd.simplifica();
		}
		return null;
	}
}

