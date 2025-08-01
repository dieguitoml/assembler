package ast;

import java.util.List;

public class InstruccionAsignacion extends Instruccion {
    private E izq;
    private E dch;

    public InstruccionAsignacion(E izq, E dch) {
        this.izq = izq;
        this.dch = dch;
    }

    public E getIzq() {
        return izq;
    }

    public E getDch() {
        return dch;
    }
    
    @Override
    public String toString() {
        return "InstruccionAsignacion(" + izq.toString() + "," + dch.toString() + ")";
    }

    @Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		String indent = StringUtils.generate_identation(identado);
		
		code_builder.append(indent + ";; " + toString() +"\n");
		
		int desplazamientoIzq = izq.getDesplazamiento();
		int desplazamientoDch = dch.getDesplazamiento();
		
		if(dch.getVinculo() != null) {
			Tipo tipoDch = ((ASTNode) dch.getVinculo().get(0)).getTipo();
			if(tipoDch instanceof TipoArray) {
				int tamDimensiones = ((TipoArray) tipoDch).getNumDimensiones();
				int tamElemento = ((TipoArray) tipoDch).getTipoElemento().calcularMemoria();
				for(int i = 0; i<tamDimensiones;i++) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (desplazamientoIzq + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");
				
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (desplazamientoDch + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.load\n");
				
					code_builder.append(indent + "i32.store\n");
				}
			}
			else if(tipoDch instanceof TipoStruct) {
				DeclaracionStruct ds = (DeclaracionStruct) ((TipoStruct) tipoDch).getVinculo().get(0);
				List<Declaracion> campos = ds.getArgumentos();
				for (int i = 0; i < campos.size(); i++) {
					int desplazamientoCampo = campos.get(i).getDesplazamiento();

					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (desplazamientoIzq) + "\n");
					code_builder.append(indent + "i32.const " + (desplazamientoCampo) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (desplazamientoDch) + "\n");
					code_builder.append(indent + "i32.const " + (desplazamientoCampo) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.load\n");
					
					code_builder.append(indent + "i32.store\n");
				}		
			}else {
				((E)izq).generate_code_expr_mem(code_builder, identado);
				dch.generate_code_expr(code_builder, identado);
				code_builder.append(indent).append("i32.store\n");
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
		dch.asingarDesplazamiento(dirPadre);
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		izq.bind();
		dch.bind();
	}

	@Override
	public void chequea() {
		if(izq.getIsDireccionable()) {
			izq.chequea();
			dch.chequea();
			if(!izq.getType().equals(dch.getType())) {
				ASTNode.errorTipado=true;
				System.out.print("La asignacion debe ser para expresiones de igual tipo.\n");
			}
			}
			else {
				ASTNode.errorTipado=true;
				System.out.print("La parte izquierda de la asignacion debe ser direccionable.\n");
			}
	}

    
}
