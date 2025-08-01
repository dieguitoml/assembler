package ast;

import java.util.List;

public class BloqueFuncion extends ASTNode{
  
    private List<ASTNode> hijos;

    public BloqueFuncion(List<ASTNode> l) {
    	this.hijos = l;
    }

    public List<ASTNode> getHijos(){
    	return this.hijos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("BloqueFuncion (");
    
        if (hijos != null && !hijos.isEmpty()) {
            for (int i = 0; i < hijos.size(); i++) {
                sb.append(hijos.get(i).toString());
                if (i < hijos.size() - 1) {
                    sb.append(",");
                }
            }
        }
    
        sb.append(")");
        return sb.toString();
    }

    // Nodo del AST
    public NodeKind nodeKind() {
        return NodeKind.DECLARACION;
    }

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		for(ASTNode ast: hijos) {
			ast.asignarTamanosMemoriaTipos();
		}
		
	}
	
	@Override
	 public int asignarTamanoMemoriaMarcos() {
		return this.calcularMemoria();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tama = 0;
		for(ASTNode ast: hijos) {
			tama += ast.calcularMemoria();
		}
		return tama;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		desplazamiento = dirPadre;
		int dirLocal = dirPadre;
		for(ASTNode d:hijos) {
			dirLocal = d.asingarDesplazamiento(dirLocal);
		}
		return dirPadre;
	}

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		for(ASTNode a: hijos) {
			a.generate_code_instr(code_builder, identado);
		}
	}
	
	public void generate_code_instr(StringBuilder code_builder, int identado, int dirPadre) {
		// TODO Auto-generated method stub
		for(ASTNode a : hijos) {
			if(a instanceof DeclaracionVariable || a instanceof DeclaracionVariableConAsign) {
				a.desplazamiento +=dirPadre;
				a.generate_code_expr(code_builder, identado);
				a.desplazamiento -=dirPadre;
			}else {
				if(a instanceof InstruccionBreak) {
					((InstruccionBreak) a).generate_code_instr(code_builder, identado, this.getSoyBucle());
				}
				else {
					a.generate_code_instr(code_builder, identado);
				}
			}
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
	public void setType(Tipo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		for(ASTNode d:hijos) {
			d.chequea();
		}
	}
	public Tipo simplifica() {
		for(ASTNode a: hijos) {
			a.simplifica();
		}
		return null;
	}
}
