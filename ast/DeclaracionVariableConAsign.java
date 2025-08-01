package ast;
import java.util.List;

public class DeclaracionVariableConAsign extends Declaracion {
	private Tipo tipo;       
	private Identificador id; 
	private List<E> asignacion;

	public DeclaracionVariableConAsign(Tipo tipo, Identificador id, List<E> asignacion) {
		this.tipo = tipo;
		this.id = id;
		this.asignacion = asignacion;
		this.direccionable = false;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public List<E> get() {
		return this.asignacion;
	}

	public Identificador getdId() {
		return this.id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("DeclaracionVariableConAsign(");
		sb.append(tipo.toString() + "," + id.toString() + ",");
		if (asignacion != null && !asignacion.isEmpty()) {
			for (int i = 0; i < asignacion.size(); i++) {
				sb.append(asignacion.get(i) != null ? asignacion.get(i).toString() : "null");
				if (i < asignacion.size() - 1) {
					sb.append(", ");
				}
			}
		} else {
			sb.append("null");
		}

		sb.append(")");
		return sb.toString();
	}

	@Override
	public Tipo getType() {
		return this.tipo.getType();
	}

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		if (asignacion != null) {
			String indent = StringUtils.generate_identation(identado);
			code_builder.append(indent + ";; " + toString() + "\n");
			if (asignacion.size() == 1) {
				code_builder.append(indent + "global.get $MP\n");
				code_builder.append(indent + "i32.const " + desplazamiento + "\n");
				code_builder.append(indent + "i32.add\n"); 
				asignacion.get(0).generate_code_expr(code_builder, identado);
				code_builder.append(indent + "i32.store\n");
			} else if (tipo instanceof TipoStruct) {
				DeclaracionStruct ds = (DeclaracionStruct) ((TipoStruct) tipo).getVinculo().get(0);
				List<Declaracion> campos = ds.getArgumentos();
				for (int i = 0; i < asignacion.size(); i++) {
					E expr = asignacion.get(asignacion.size()-i-1);
					int desplazamientoCampo = campos.get(asignacion.size()-i-1).getDesplazamiento();

					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (desplazamiento) + "\n");
					code_builder.append(indent + "i32.const " + (desplazamientoCampo) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.add\n");
					
					expr.generate_code_expr(code_builder, identado); // genera el valor a asignar
					code_builder.append(indent + "i32.store\n");
				}				
			} else if (tipo instanceof TipoArray) {
				String indent1 = StringUtils.generate_identation(identado+1);
				int tamElemento = ((TipoArray)tipo).getTipoElemento().getType().calcularMemoria(); 
				for (int i = 0; i < asignacion.size(); i++) {
					E expr = asignacion.get(asignacion.size()-i-1);

					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (desplazamiento + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");

					expr.generate_code_expr(code_builder, identado); // genera el valor a asignar

					code_builder.append(indent + "i32.store\n");
				}
			}
			else{
				System.out.println("DeclaracionVariableConAsign: No se ha implementado la generacion de codigo para este tipo de declaracion.");
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
		this.tipo.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		return tipo.calcularMemoria();
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		desplazamiento = dirPadre; // El desplazamiento dentro del marco actual
		for(E exp :asignacion) {
			exp.asingarDesplazamiento(dirPadre);
		}
		return dirPadre + this.calcularMemoria();
	}

	@Override
	public void bind() {
		tipo.bind();
		b.insert(id.name(), this);
		for(E a: asignacion) {
			a.bind();
		}
	}

	@Override
	public void chequea() {
		for (E a : asignacion) {
			a.chequea();
		}
		// No permitimos asignaciones parciales
		if (tipo instanceof TipoArray) {
			List<Dimensiones> dim = ((TipoArray) tipo).getDimension();
			int tamTotal = 1;
			for(Dimensiones dd :dim) {
				tamTotal =tamTotal*dd.getTamDimension().getV();
			}
			if (asignacion.size() !=tamTotal) {
				// ERROR
				ASTNode.errorTipado = true;
				System.out.print("Inicializacion incorrecta del array");
			}
			for (int i = 0; i < asignacion.size(); i++) {
				if (((TipoArray)tipo).getType().getClass() != asignacion.get(i).getType().getClass()) {
					// ERROR
					System.out.print("Inicializacion incorrecta del array");
					ASTNode.errorTipado = true;
				}
			}
		}
		else if (tipo instanceof TipoStruct) {
			DeclaracionStruct ds = (DeclaracionStruct) ((TipoStruct) tipo).getVinculo().get(0);
			if (asignacion.size() != ds.getArgumentos().size()) {
				System.out.print("Inicializacion incorrecta del struct");
				ASTNode.errorTipado = true;
			}
			for (int i = 0; i < asignacion.size(); i++) {
				Tipo t = asignacion.get(i).getType();
				Tipo tipo2 = ds.getArgumentos().get(i).getType();
				if (t.getClass() != tipo2.getClass()) {
					System.out.print("Inicializacion incorrecta del struct");
					ASTNode.errorTipado = true;
				}
			}
		} else { 
			E exp = asignacion.get(0);
			Tipo t2 = exp.getType();
			if (tipo.getType().getClass() != t2.getType().getClass()) {
				System.out.print("Declaracion incorrecta de variable");
				ASTNode.errorTipado = true;
			}
		}
	}

	@Override
	protected Identificador getIdd() {
		return this.id;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tipo.simplifica();
		return null;
	}
}
