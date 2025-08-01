package ast;

public class AccesoFlecha extends E{

    private final ASTNode objeto;  
    private final ASTNode propiedad; 
    private Tipo t;
    public AccesoFlecha(ASTNode objeto, ASTNode propiedad) {
        this.objeto = objeto;
        this.propiedad = propiedad;
    }

    @Override
    public String toString() {
        return "AccesoFlecha(" +  objeto.toString() + "," + propiedad.toString() + ")";
    }

	@Override
	public KindE kind() {
		// TODO Auto-generated method stub
		return KindE.FLECHA;
	}
	
	public  Tipo getType() {
		return this.t;
	}
    public ASTNode getObjeto() {
        return objeto;
    }

    public ASTNode getPropiedad() {
        return propiedad;
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
		objeto.generate_code_expr(code_builder, identado);
		code_builder.append(indent + "i32.const " + propiedad.desplazamiento + "\n");
		code_builder.append(indent + "i32.add\n");
		code_builder.append(indent + "i32.load\n");
	}
	
	@Override
	public void generate_code_expr_mem(StringBuilder code_builder, int identado) {
		String indent = StringUtils.generate_identation(identado);
		objeto.generate_code_expr(code_builder, identado);
		code_builder.append(indent + "i32.const " + propiedad.desplazamiento + "\n");
		code_builder.append(indent + "i32.add\n");
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
		return propiedad.asingarDesplazamiento(dirPadre);
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		if(!(objeto instanceof AccesoThis)) {
		objeto.bind_externo();
		propiedad.isGlobal = true;
		}else {
			objeto.isGlobal = false;
			objeto.bind();
			propiedad.bind_externo();
		}
	}
	
	@Override
	public void bind_externo() {
		objeto.bind_externo();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		if (!(objeto instanceof E)) {
			ASTNode.errorTipado = true;
			System.out.print("El objeto de AccesoFlecha no es una expresion valida.");
			return;
		}
		objeto.chequea();
		if(!(objeto instanceof AccesoThis)) {//Si no es un this->
			if(!(objeto.getType() instanceof TipoPuntero)||!(objeto.getType().simplifica() instanceof TipoStruct)) {
				System.out.print("El operador '->' requiere un puntero a struct.\n");
				ASTNode.errorTipado = true;
				return;
			}else {
				if(objeto instanceof Identificador) {
				ASTNode id = ((Identificador) objeto).getVinculo().get(0).getTipo().getVinculo().get(0);
				DeclaracionStruct a = (DeclaracionStruct) id;
 				for(Declaracion d: a.getArgumentos()) {
					Declaracion dd = a.getIden(((Identificador)propiedad).getNombre());
					if(dd!=null) {
						this.t = dd.getType();	
					}
				}
			  }else {
				  Tipo t = objeto.getType().simplifica();
				  ASTNode id = t.getVinculo().get(0);
					DeclaracionStruct a = (DeclaracionStruct) id;
	 				for(Declaracion d: a.getArgumentos()) {
						Declaracion dd = a.getIden(((Identificador)propiedad).getNombre());
						if(dd!=null) {
							this.t = dd.getType();	
						}
					}
			  }
			}
		}
		else {
			this.t = propiedad.getVinculo().get(0).getType();
		}
	}

    

}
