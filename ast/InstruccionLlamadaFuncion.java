package ast;

import java.util.ArrayList;
import java.util.List;

public class InstruccionLlamadaFuncion extends Instruccion{
    private Identificador funcion;
    private List<E> parametros;
    private ASTNode vinculo;
    private ASTNode declaracion = null;
    
    public InstruccionLlamadaFuncion(Identificador funcion, List<E> parametros) {
        this.funcion = funcion;
        this.parametros = parametros;
        setDireccionable(false);
    }
    
    public void vinculo(ASTNode as) {
    	this.vinculo = as;
    }
    
    public Identificador getName() {
    	return this.funcion;
    }
    
    public void setDeclaracion(ASTNode objeto) {
    	this.declaracion = objeto;
    }

    public Identificador getFuncion() {
        return funcion;
    }

    public List<E> getParametros() {
        return parametros;
    }

    @Override
    public String toString() {
        return "InstruccionLlamadaFuncion (" + funcion + "," + parametros + ")";
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		int tamReservar = 0;
		tamReservar += vinculo.asignarTamanoMemoriaMarcos(); 

		code_builder.append(indent + "i32.const " + tamReservar +  " ;; DL + dirinstancia + parametros + instrucciones\n");
		code_builder.append(indent+ "call $reserveStack  ;; returns old MP (dynamic link)\n");
		code_builder.append(indent+ "local.set $temp\n");
		code_builder.append(indent+ "global.get $MP\n");
		code_builder.append(indent+ "local.get $temp\n");
		code_builder.append(indent+ "i32.store\n");
		DeclaracionFuncion df = (DeclaracionFuncion) vinculo;
		List<Parametro> declaracionParam = df.getBloque().getParametros();

		for (int i = 0; i < parametros.size(); i++) {
			code_builder.append(indent + ";;parametro\n");
			E paramReal = parametros.get(i);
			Parametro paramD = declaracionParam.get(i);
			if (paramD.getReference()) {
				if(paramReal instanceof AccesoPunto || paramReal instanceof EBin|| paramReal instanceof Num) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + paramD.desplazamiento + "\n");	
					code_builder.append(indent + "i32.add\n");	
					paramReal.generate_code_expr_mem_param(code_builder, identado);
				}else if (paramReal instanceof Identificador) {
					paramReal.generate_code_expr_mem_param(code_builder, identado,true);
				}else {
					paramReal.generate_code_expr_mem_param(code_builder, identado);

				}
			} else {
				if(paramReal instanceof AccesoPunto|| paramReal instanceof EBin || paramReal instanceof Num) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + paramD.desplazamiento + "\n");	
					code_builder.append(indent + "i32.add\n");	
					paramReal.generate_code_expr_param(code_builder, identado);
				}else if (paramReal instanceof Identificador) {
					paramReal.generate_code_expr_param(code_builder, identado,true);
				}else {
					paramReal.generate_code_expr_param(code_builder, identado);
				}
					
			}
			
		}
		if(this.declaracion != null) {
			if(declaracion.isGlobal) {
				code_builder.append(indent + "local.get $dirinstancia\n");
			}else {
				code_builder.append(indent + "local.get $temp\n");
			}
			code_builder.append(indent + "i32.const " + declaracion.getDesplazamiento() + "\n");
			code_builder.append(indent + "i32.add "  + ";;el ambito de la funcion\n" );
		}else {
			code_builder.append(indent + "i32.const 0"  + " ;; el ambito de la funcion\n" );
		}
		code_builder.append(indent+ "i32.const 0 		;;Initialize temp\n");

		code_builder.append(indent + "call $" + funcion.name() + "\n");

	}

	@Override
	public void generate_code_desig(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generate_code_expr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		int tamReservar = 0;
		tamReservar += vinculo.asignarTamanoMemoriaMarcos(); 

		code_builder.append(indent + "i32.const " + tamReservar +  " ;; DL + dirinstancia + parametros + instrucciones\n");
		code_builder.append(indent+ "call $reserveStack  ;; returns old MP (dynamic link)\n");
		code_builder.append(indent+ "local.set $temp\n");
		code_builder.append(indent+ "global.get $MP\n");
		code_builder.append(indent+ "local.get $temp\n");
		code_builder.append(indent+ "i32.store\n");
		DeclaracionFuncion df = (DeclaracionFuncion) vinculo;
		List<Parametro> declaracionParam = df.getBloque().getParametros();

		for (int i = 0; i < parametros.size(); i++) {
			code_builder.append(indent + ";;parametro\n");
			E paramReal = parametros.get(i);
			Parametro paramD = declaracionParam.get(i);
			if (paramD.getReference()) {
				if(paramReal instanceof AccesoPunto || paramReal instanceof EBin|| paramReal instanceof Num) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + paramD.desplazamiento + "\n");	
					code_builder.append(indent + "i32.add\n");	
					paramReal.generate_code_expr_mem_param(code_builder, identado);
				}else if (paramReal instanceof Identificador) {
					paramReal.generate_code_expr_param(code_builder, identado,true);
			     }	
			} else {
				if(paramReal instanceof AccesoPunto|| paramReal instanceof EBin || paramReal instanceof Num) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + paramD.desplazamiento + "\n");	
					code_builder.append(indent + "i32.add\n");	
					paramReal.generate_code_expr_param(code_builder, identado);
				}else if (paramReal instanceof Identificador)
					paramReal.generate_code_expr_param(code_builder, identado,true);
			}
			
		}
		if(this.declaracion != null) {
			if(declaracion.isGlobal) {
				code_builder.append(indent + "local.get $dirinstancia\n");
			}else {
				code_builder.append(indent + "local.get $temp\n");
			}
			code_builder.append(indent + "i32.const " + declaracion.getDesplazamiento() + "\n");
			code_builder.append(indent + "i32.add "  + ";;el ambito de la funcion\n" );
		}else {
			code_builder.append(indent + "i32.const 0"  + " ;; el ambito de la funcion\n" );
		}
		code_builder.append(indent+ "i32.const 0 		;;Initialize temp\n");

		code_builder.append(indent + "call $" + funcion.name() + "\n");

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
		DeclaracionFuncion df = (DeclaracionFuncion) vinculo;
		List<Parametro> declaracionParam = df.getBloque().getParametros();
		for(int i=0;i<parametros.size();i++) {
			parametros.get(i).desplazamiento = declaracionParam.get(i).desplazamiento;
		}
		return dirPadre;
	}

	@Override
	public void bind() {
		funcion.vinculo(b.lookup(funcion.name()));
		for(E exp: parametros) {
			exp.bind();
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		for(E e :parametros) {
			e.chequea();
		}
		 List<Tipo> tipoParametros = new ArrayList<>();
		for(E e:parametros) {
			tipoParametros.add(e.getTipo());
		}
		List<ASTNode> vinculo = funcion.getVinculo();
		int j = 0;
		int nVinculos = 0;
		DeclaracionFuncion vinculoFinal = null;
        while(j<vinculo.size()) {
        	DeclaracionFuncion df = (DeclaracionFuncion) vinculo.get(j);
        	List<Parametro> l =  df.getBloque().getParametros();
        	int i=0;
        	if(l.size() == tipoParametros.size()) {
        		for(Parametro p: l) {
        			if(!p.getType().equals(tipoParametros.get(i))) {
        				break;
        			}
            		i++;
        		}
        	}
        	if(i==tipoParametros.size()) {
        		this.setType(df.getType());
        		vinculoFinal = (DeclaracionFuncion) vinculo.get(j);
        		nVinculos++;
        	}
        	j++;
        }
        if(nVinculos != 1) {
        	System.out.print("La llamada a funcion no se puede vincular con ninguna declaracion."); 
        	errorTipado = true;
        }else {
        	this.vinculo = vinculoFinal;
        }
        if (vinculoFinal instanceof DeclaracionFuncion) {
            Tipo tipoRetorno = ((DeclaracionFuncion) vinculoFinal).getTipoRetorno();
            this.setType(tipoRetorno);
        } else if (vinculo instanceof DeclaracionClase) {
        	
        } else {
            
        }
	}

}
