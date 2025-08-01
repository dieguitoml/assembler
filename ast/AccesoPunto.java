package ast;

import java.util.ArrayList;
import java.util.List;

public class AccesoPunto extends E{
	    private final E objeto;  
	    private final E propiedad; 
	    private Tipo t;
	    private ASTNode vinculo;

	    public AccesoPunto(E objeto, E propiedad) {
	        this.objeto = objeto;
	        this.propiedad = propiedad;
	    }
	    
	    public E getStruct() {
	    	return this.objeto;
	    }
	    
	    public E getCampos() {
	    	return this.propiedad;
	    }
	    
	    @Override
	    public String toString() {
	        return "AccesoPunto (" + objeto.toString() + "," + propiedad.toString() + ")";
	    }

	    public Tipo getType() {
	    	return this.t;
	    }
	    
	    @Override
	    public List<ASTNode> getVinculo() {
	    	List<ASTNode> l = new ArrayList<>();
	    	l.add(vinculo);
	    	return l;
	    }
	    
		@Override
		public KindE kind() {
			// TODO Auto-generated method stub
			return KindE.STRUCT;
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
			String indent = StringUtils.generate_identation(identado);
			if (objeto instanceof Identificador && objeto.getType() instanceof TipoClase) {
			      propiedad.generate_code_instr(code_builder, identado);
			}else {
			objeto.generate_code_expr_mem(code_builder, identado); 
			Tipo tipo = objeto.getType().simplifica();
			int offset = 0;
			if (tipo instanceof TipoStruct) {
				DeclaracionStruct ds = (DeclaracionStruct) ((Identificador) objeto).getVinculo().get(0).getTipo().getVinculo().get(0);
				Declaracion campo = ds.valid_argument(propiedad);
				offset = campo.desplazamiento;
				code_builder.append(indent + "i32.const " + offset + "\n");
				code_builder.append(indent + "i32.add\n");
				code_builder.append(indent).append("i32.load\n");
			  } 
			}
		}

		@Override
		public void generate_code_expr_mem(StringBuilder code_builder, int identado) {
			String indent = StringUtils.generate_identation(identado);
			if (objeto instanceof Identificador && objeto.getType() instanceof TipoClase) {
			      propiedad.generate_code_instr(code_builder, identado); 
			}else {
			objeto.generate_code_expr_mem(code_builder, identado); 
			Tipo tipo = objeto.getType().simplifica();
			int offset = 0;
			if (tipo instanceof TipoStruct) {
				DeclaracionStruct ds = (DeclaracionStruct) ((Identificador) objeto).getVinculo().get(0).getTipo().getVinculo().get(0);
				Declaracion campo = ds.valid_argument(propiedad);
				offset = campo.desplazamiento;
				
				//Sumar el desplazamiento
				code_builder.append(indent + "i32.const " + offset + "\n");
				code_builder.append(indent + "i32.add\n");
			  } 
			}
		}

		@Override
		public void asignarTamanosMemoriaTipos() {
			// TODO Auto-generated method stub
			propiedad.asignarTamanosMemoriaTipos();
		}

		@Override
		public int calcularMemoria() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int asingarDesplazamiento(int dirPadre) {
			if(objeto.getType() instanceof TipoClase) {
				propiedad.asingarDesplazamiento(dirPadre);
			}
		    return desplazamiento;
		}

		@Override
		public void bind() {
			objeto.bind();
			//objeto.bind();
			if(propiedad instanceof InstruccionLlamadaFuncion) {
				for (E p : ((InstruccionLlamadaFuncion) propiedad).getParametros()) {
					p.bind();
				}
			}
		}

		@Override
		public void chequea() {
			// TODO Auto-generated method stub
			objeto.chequea();
			if(!(objeto.getType() instanceof TipoStruct) && !(objeto.getType() instanceof TipoClase)) {
				System.out.print("Error, el operador . solo es aplicable a structs y clases\n");
				errorTipado = true;
			}
			if(objeto.getType() instanceof TipoStruct) {
				ASTNode id = (objeto).getVinculo().get(0).getTipo().getVinculo().get(0);
				if(id instanceof DeclaracionStruct) {
					DeclaracionStruct  ds = (DeclaracionStruct) id;
							if(propiedad instanceof Identificador) {
								if((ds.valid_argument(propiedad))!=null) {
									this.t= ds.valid_argument(propiedad).getType();
								}
							}else {
								Identificador idd;
								if(propiedad instanceof EBin) {
									 idd = (Identificador) ((EBin)propiedad).getLeft();
								}else {
									idd = (Identificador) ((AccesoArray) propiedad).getArrayBase();
								}
								Declaracion d = ds.valid_argument(idd);
								List<ASTNode> l = new ArrayList<>();
								l.add(d);
								propiedad.vinculo(l);
								propiedad.chequea();
								this.vinculo = d;
								this.t = propiedad.getType();
							}
				}
				
			}else if(objeto instanceof Identificador || objeto.getType() instanceof TipoClase) {
				DeclaracionClase dc = (DeclaracionClase) ((DeclaracionVariable)((Identificador) objeto).getVinculo().get(0)).getTipo().getVinculo().get(0);
				if(propiedad instanceof InstruccionLlamadaFuncion) {
					InstruccionLlamadaFuncion id = ((InstruccionLlamadaFuncion) propiedad);
					if((dc.valid_argument(id))!=null) {
						this.vinculo = dc.valid_argument(id);
						this.t = vinculo.getType();
					}else {
						System.out.print("Error,la funcion no tipa con ninguna de la clase\n");
					}
					 ((InstruccionLlamadaFuncion) propiedad).setDeclaracion(objeto.getVinculo().get(0));
					 ((InstruccionLlamadaFuncion) propiedad).vinculo(((DeclaracionClase) objeto.getTipo().getVinculo().get(0)).valid_argument(propiedad));
				}
				else {
					System.out.print("Error, solo se pueden acceder a metodos de clases\n");
				}
			}

		}
		@Override
		public void bind_externo() {
			// TODO Auto-generated method stub
			this.objeto.bind_externo();
			if(propiedad instanceof InstruccionLlamadaFuncion) {
				for (E p : ((InstruccionLlamadaFuncion) propiedad).getParametros()) {
					p.bind();
				}
			}
		}

		@Override
		public void generate_code_expr_param(StringBuilder code_builder, int identado) {
			// TODO Auto-generated method stub
			String indent = StringUtils.generate_identation(identado);

			objeto.generate_code_expr_param(code_builder,identado);
			if(objeto.getType() instanceof TipoStruct) {
				ASTNode id = (objeto).getVinculo().get(0).getTipo().getVinculo().get(0);
				ASTNode id2 = (objeto).getVinculo().get(0);
				DeclaracionStruct ds = (DeclaracionStruct) id;
				Identificador idd;
				if(propiedad instanceof EBin) {
					 idd = (Identificador) ((EBin)propiedad).getLeft();
				}else if (propiedad instanceof Identificador){
					idd = (Identificador) propiedad;
				}else {
					idd = (Identificador) ((AccesoArray) propiedad).getArrayBase();
				}
				Declaracion d = ds.valid_argument(idd);
				if(objeto.isGlobal) {
					code_builder.append(indent + "local.get $dirinstancia\n");
					code_builder.append(indent + "i32.const " + id2.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.const " + idd.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					if (propiedad instanceof Identificador){
						code_builder.append(indent + "i32.load\n");
						code_builder.append(indent + "i32.load\n");
					}
				}else {
					code_builder.append(indent + "local.get $temp\n");
					code_builder.append(indent + "i32.const " + id2.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.const " + idd.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					if (propiedad instanceof Identificador){
						code_builder.append(indent + "i32.load\n");
						code_builder.append(indent + "i32.load\n");
					}
				}
			}else {//Llamada a funcion de clase
			    ((InstruccionLlamadaFuncion) propiedad).setDeclaracion(objeto.getVinculo().get(0));
			      propiedad.generate_code_instr(code_builder, identado); 
			      code_builder.append(indent + "i32.load\n");
			}
		}
		@Override
		public void generate_code_expr_mem_param(StringBuilder code_builder, int identado) {
			// TODO Auto-generated method stub
			String indent = StringUtils.generate_identation(identado);

			objeto.generate_code_expr_param(code_builder,identado);
			if(objeto.getType() instanceof TipoStruct) {
				ASTNode id = (objeto).getVinculo().get(0).getTipo().getVinculo().get(0);
				ASTNode id2 = (objeto).getVinculo().get(0);
				DeclaracionStruct ds = (DeclaracionStruct) id;
				Identificador idd;
				if(propiedad instanceof EBin) {
					 idd = (Identificador) ((EBin)propiedad).getLeft();
				}else if (propiedad instanceof Identificador){
					idd = (Identificador) propiedad;
				}else {
					idd = (Identificador) ((AccesoArray) propiedad).getArrayBase();
				}
				Declaracion d = ds.valid_argument(idd);
				if(objeto.isGlobal) {
					code_builder.append(indent + "local.get $dirinstancia\n");
					code_builder.append(indent + "i32.const " + id2.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.const " + idd.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					if (propiedad instanceof Identificador){
						code_builder.append(indent + "i32.load\n");
						}
				}else {
					code_builder.append(indent + "local.get $temp\n");
					code_builder.append(indent + "i32.const " + id2.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.const " + idd.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					if (propiedad instanceof Identificador){
						code_builder.append(indent + "i32.load\n");
					}
				}
			}else {
			    ((InstruccionLlamadaFuncion) propiedad).setDeclaracion(objeto.getVinculo().get(0));
			      propiedad.generate_code_instr(code_builder, identado);
			}
		}
}
