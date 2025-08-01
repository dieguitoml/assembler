package ast;

import java.util.List;

public class Identificador extends E {
    private String nombre; 
    private Tipo t;
    private List<ASTNode> referencia;
    
    public Identificador(String nombre) {
        this.nombre = nombre;
    }
    
    public void vinculo(List<ASTNode> node) {
    	this.referencia= node; 
    }    
    public String name() {
    	return this.nombre;
    }
    
    public List<ASTNode> getVinculo(){
    	return this.referencia;
    }
    
    @Override
    public Tipo getType() {
    	return this.t.getType();
    }

    @Override
    public Tipo getTipo() {
    	return this.t.getTipo();
    }
    
    @Override
    public String toString() {
        return "Identificador(" + nombre + ")";
    }

	@Override
	public NodeKind nodeKind() {
		// TODO Auto-generated method stub
		return NodeKind.IDENTIFICADOR;
	}

    @Override
    public KindE kind() {
        // TODO Auto-generated method stub
        return KindE.IDENTIFIER;
    }
    
    public String getNombre() {
        return nombre;
    }

    public List<ASTNode> getReferencia() {
        return referencia;
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
	public void generate_code_expr_param(StringBuilder code_builder, int identado, boolean store) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		ASTNode v = this.getVinculo().get(0);
		Tipo tipoVinculo = v.getTipo();
		if(v.isGlobal) {
			if(tipoVinculo instanceof TipoArray) {
				int tamDimensiones = ((TipoArray) tipoVinculo).getNumDimensiones();
				int tamElemento = ((TipoArray) tipoVinculo).getTipoElemento().calcularMemoria();
				for(int i = 0; i<tamDimensiones;i++) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (this.desplazamiento + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "local.get $dirinstancia\n");
					code_builder.append(indent + "i32.const " + (v.getDesplazamiento() + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.load\n");
					code_builder.append(indent + "i32.store\n");
				}
			}
			else if(tipoVinculo instanceof TipoStruct) {
				DeclaracionStruct tamStruct = (DeclaracionStruct) ((TipoStruct) tipoVinculo).getVinculo().get(0).getType().getVinculo().get(0);
				ASTNode dd = ((TipoStruct) tipoVinculo).getVinculo().get(0);
				List<Declaracion>  d = tamStruct.getArgumentos();
				for(int i=0;i<dd.desplazamiento;i = i+4) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + this.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent  + "i32.const " + i + "\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent + "local.get $dirinstancia\n");
					code_builder.append(indent + "i32.const " +dd.desplazamiento + "\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent  + "i32.const " + i + "\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent + "i32.load\n");
					code_builder.append(indent + "i32.load\n");
					code_builder.append(indent + "i32.store\n");
				}
			}
			else {
				code_builder.append(indent + "global.get $MP\n");
				code_builder.append(indent + "i32.const " + (this.desplazamiento) + "\n");
				code_builder.append(indent + "i32.add\n");
				code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");//Dir de mem de la variable
				code_builder.append(indent + "local.get $dirinstancia\n");
				code_builder.append(indent + "i32.add\n");
				code_builder.append(indent + "i32.load\n");
				if(store)code_builder.append(indent + "i32.store\n");
			}
		}else {
			if(tipoVinculo instanceof TipoArray) {
				ASTNode dd = referencia.get(0);
				for(int i = 0; i<dd.calcularMemoria();i = i + 4) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (this.desplazamiento + i) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "local.get $temp\n");
					code_builder.append(indent + "i32.const " + (v.getDesplazamiento() + i) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.load\n");
					code_builder.append(indent + "i32.store\n");
				}
			}
			else if(tipoVinculo instanceof TipoStruct) {
				DeclaracionStruct tamStruct = (DeclaracionStruct) ((TipoStruct) tipoVinculo).getVinculo().get(0).getType().getVinculo().get(0);
				ASTNode dd = referencia.get(0);
				List<Declaracion>  d = tamStruct.getArgumentos();
				for(int i=0;i<dd.calcularMemoria();i = i + 4) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + this.getDesplazamiento() + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent  + "i32.const " + i + "\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent + "local.get $dirinstancia\n");
					code_builder.append(indent + "i32.const " +dd.desplazamiento + "\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent  + "i32.const " + i + "\n");
					code_builder.append(indent + "i32.add\n");
					
					code_builder.append(indent + "i32.load\n");		
					code_builder.append(indent + "i32.store\n");
				}
			}
			else{
				code_builder.append(indent + "global.get $MP\n");
				code_builder.append(indent + "i32.const " + (this.desplazamiento) + "\n");
				code_builder.append(indent + "i32.add\n");
				code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");//Dir de mem de la variable
				code_builder.append(indent + "local.get $temp\n");
				code_builder.append(indent + "i32.add\n");
			    code_builder.append(indent + "i32.load\n");
			    if(store)code_builder.append(indent + "i32.store\n");
				if((referencia.get(0) instanceof Parametro) && ((Parametro) referencia.get(0)).getReference()) {
					code_builder.append(indent + "i32.load\n");
					if(store)code_builder.append(indent + "i32.store\n");
				}	
			}
		}
	}
	@Override
	public void generate_code_expr_mem_param(StringBuilder code_builder, int identado, boolean store) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		ASTNode v = this.getVinculo().get(0);
		if(v.isGlobal) {
			code_builder.append(indent + "global.get $MP\n");
			code_builder.append(indent + "i32.const " + (this.desplazamiento) + "\n");
			code_builder.append(indent + "i32.add\n");
			code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
			code_builder.append(indent + "local.get $dirinstancia\n");
			code_builder.append(indent + "i32.add\n");
			if(store)code_builder.append(indent + "i32.store\n");
		}else {
			code_builder.append(indent + "global.get $MP\n");
			code_builder.append(indent + "i32.const " + (this.desplazamiento) + "\n");
			code_builder.append(indent + "i32.add\n");
			code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
			code_builder.append(indent + "local.get $temp\n");
			code_builder.append(indent + "i32.add\n");
			if((referencia.get(0) instanceof Parametro) && ((Parametro) referencia.get(0)).getReference()) {
				code_builder.append(indent + "i32.load\n");
			}	
			if(store)code_builder.append(indent + "i32.store\n");
		}
	}

	@Override
	public void generate_code_expr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		ASTNode v = this.getVinculo().get(0);
		Tipo tipoVinculo = v.getTipo();
		if(v.isGlobal) {
			if(tipoVinculo instanceof TipoArray) {
				int tamDimensiones = ((TipoArray) tipoVinculo).getNumDimensiones();
				int tamElemento = ((TipoArray) tipoVinculo).getTipoElemento().calcularMemoria();
				for(int i = 0; i<tamDimensiones;i++) {
					code_builder.append(indent + "logal.get $dirinstancia\n");
					code_builder.append(indent + "i32.const " + (v.getDesplazamiento() + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.load\n");
				}
			}
			else if(tipoVinculo instanceof TipoStruct) {
				code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
				code_builder.append(indent + "local.get $dirinstancia\n");
				code_builder.append(indent + "i32.add\n");
			    code_builder.append(indent + "i32.load\n");
			}
			else {
				code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
				code_builder.append(indent + "local.get $dirinstancia\n");
				code_builder.append(indent + "i32.add\n");
			    code_builder.append(indent + "i32.load\n");
			}
		}else {
			if(tipoVinculo instanceof TipoArray) {
				int tamDimensiones = ((TipoArray) tipoVinculo).getNumDimensiones();
				int tamElemento = ((TipoArray) tipoVinculo).getTipoElemento().calcularMemoria();
				for(int i = 0; i<tamDimensiones;i++) {
					code_builder.append(indent + "global.get $MP\n");
					code_builder.append(indent + "i32.const " + (v.getDesplazamiento() + i * tamElemento) + "\n");
					code_builder.append(indent + "i32.add\n");
					code_builder.append(indent + "i32.load\n");
				}
			}
			else if(tipoVinculo instanceof TipoStruct) {
				code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
				code_builder.append(indent + "global.get $MP\n");
				code_builder.append(indent + "i32.add\n");
			    code_builder.append(indent + "i32.load\n");
			}
			else{
				code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
				code_builder.append(indent + "global.get $MP\n");
				code_builder.append(indent + "i32.add\n");
			    code_builder.append(indent + "i32.load\n");
				if((referencia.get(0) instanceof Parametro) && ((Parametro) referencia.get(0)).getReference()) {
					code_builder.append(indent + "i32.load\n");
				}	
			}
		}
	}
	@Override
	public void generate_code_expr_mem(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		String indent = StringUtils.generate_identation(identado);
		ASTNode v = this.getVinculo().get(0);
		if(v.isGlobal) {
			code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
			code_builder.append(indent + "local.get $dirinstancia\n");
			code_builder.append(indent + "i32.add\n");
		}else {
			code_builder.append(indent + "i32.const " + v.getDesplazamiento() + "\n");
			code_builder.append(indent + "global.get $MP\n");
			code_builder.append(indent + "i32.add\n");
			if((referencia.get(0) instanceof Parametro) && ((Parametro) referencia.get(0)).getReference()) {
				code_builder.append(indent + "i32.load\n");
			}	
		}
	}
	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		t.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		if(referencia instanceof DeclaracionTipoUsuario) {
			return t.calcularMemoria();
		}
		return 0;
		
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return this.calcularMemoria() + dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		vinculo(b.lookup(nombre));
		if(!b.errorBinding && referencia.get(0).ambito == 0) isGlobal = true;
	}
	
	@Override
	public void bind_externo() {
		vinculo(b.lookup_externo(nombre));
		if( !b.errorBinding  && referencia.get(0).ambito == 0) isGlobal = true;
 	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		if(referencia.size() == 1) {
			this.t = referencia.get(0).getTipo();
		}
	}
	@Override
	public Tipo simplifica() {
		this.t = referencia.get(0).getType();
		return this.referencia.get(0).getType();
	}
    
}
