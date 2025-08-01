package ast;

import java.util.ArrayList;
import java.util.List;

public class DeclaracionFuncion extends Declaracion {
	private Tipo tipoRetorno; 
	private Identificador nombre; 
	private BloqueInteriorFuncion bi;

	// Constructor
	public DeclaracionFuncion(Tipo tipoRetorno, Identificador nombre, BloqueInteriorFuncion b) {
		this.tipoRetorno = tipoRetorno;
		this.nombre = nombre;
		this.bi = b;
		this.direccionable = false;
	}
	

	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}

	public Identificador getNombre() {
		return nombre;
	}

	public BloqueInteriorFuncion getBloque() {
		return bi;
	}

	@Override
	public String toString() {
		return "DeclaracionFuncion (" + tipoRetorno.toString() + "," + nombre + "," + bi.toString() + ")";
	}

	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return this.tipoRetorno;
	}

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		String indent = StringUtils.generate_identation(identado);//Genera la identaci�n 
		code_builder.append(indent + " ;; " + toString() + "\n");
		code_builder.append(indent + "(func $" + nombre.name() + "\t");
		
		code_builder.append(indent + "(param $dirinstancia i32)\t");
		code_builder.append(indent + "(param $temp i32)\t");
		
		

		if (!(tipoRetorno instanceof TipoVoid)) {
			code_builder.append("(result i32)\n");
		}else {
			code_builder.append(indent + "\n");
		}
		String indent1 = StringUtils.generate_identation(identado + 1);//Genera la identaci�n 
		for(ASTNode a : bi.getBloque().getHijos()){
			if(a instanceof Declaracion){
				a.generate_code_instr(code_builder, identado+1);
			}
			else if(a instanceof Instruccion){
				a.generate_code_instr(code_builder, identado+1);
			}
		}
		code_builder.append(indent).append(")\n");

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
	public int asignarTamanoMemoriaMarcos() {
		if(!(tipoRetorno instanceof TipoVoid)){
			tam_memoria += tipoRetorno.calcularMemoria();
		}
		this.tam_memoria += bi.asignarTamanoMemoriaMarcos();
		return tam_memoria;
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.bi.asignarTamanosMemoriaTipos();
		this.tipoRetorno.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		int dirLocal = 12;
		bi.asingarDesplazamiento(dirLocal);
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		tipoRetorno.bind();
    	b.insert(nombre.name(), this);
    	b.openScope();
    	bi.bind();
    	b.closeScope();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		tipoRetorno.chequea();
		List<Parametro> parametros = bi.getParametros();
		for(Parametro p: parametros) {
			p.chequea();
		}
		bi.getBloque().chequea();
		List<InstruccionReturn> returns = getReturns(bi.getBloque().getHijos());
		for(InstruccionReturn r: returns) {
 			if(r.getType().getClass()!=tipoRetorno.getType().getClass()) {
				ASTNode.errorTipado = true;
				System.out.print("Los returns estan mal tipados");
			}
		}
		
	}
	
	private List<InstruccionReturn> getReturns(List<ASTNode> instrucciones) {
        List<InstruccionReturn> returns = new ArrayList<>();
        for (ASTNode i : instrucciones) {
            if (i instanceof InstruccionReturn) {
                returns.add((InstruccionReturn) i);
            }
        }
        return returns;
    }

	@Override
	protected Identificador getIdd() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.bi.simplifica();
		this.tipoRetorno.simplifica();
		return null;
	}

}
