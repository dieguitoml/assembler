package ast;

public class EBin extends E {
    private KindE kind;
    private ASTNode left;
    private ASTNode right;

    public EBin(KindE kind, ASTNode left, ASTNode right) {
        this.kind = kind;
        this.left = left;
        this.right = right;
        this.direccionable = false;
    }


    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
    
    @Override
    public KindE kind() {
        return kind;
    }

    @Override
    public String toString() {
        return kind + "(" + left.toString()  + "," + right.toString() + ")";
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
		// TODO Auto-generated method stub
	    String indent = StringUtils.generate_identation(identado); 
	    if(kind == KindE.SUMA) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.add\n");
	    }else if(kind == KindE.RESTA) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.sub\n");
	    }else if (kind == KindE.MULTIPLICACION) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.mul\n");
	    }else if(kind == KindE.MODULO) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.rem_u\n");
	    }else if(kind == KindE.DIVISION) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.div_s\n");
	    }else if(kind == KindE.AND) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.and\n");
	    }else if(kind == KindE.OR) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.or\n");
	    }else if(kind == KindE.ACCESO_ARRAY) {
	    	//Se implementa en su clase
	    }else if(kind == KindE.ACCESO_DIRECCION) {
	    	//Se implementa en su clase
	    }else if(kind == KindE.ACCESO_PUNT) {
	    	//Se implementa en suu clase
	    }else if(kind == KindE.DECR) {
	    	//Unario
	    }else if(kind == KindE.DIST) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.ne\n");
	    }else if(kind == KindE.FLECHA) {
	    	//Se implementa en su clase
	    }else if(kind == KindE.IGUAL) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.eq\n");
	    }else if(kind == KindE.INCR) {
	    	//Se implementa en su clase
	    }else if(kind==KindE.MAYORIGUAL) {
	    	left.generate_code_expr(code_builder, identado);
	        right.generate_code_expr(code_builder, identado);
	        code_builder.append(indent + "i32.ge_s\n"); // Para enteros con signo
	    }else if(kind == KindE.MENOR) {
	    	left.generate_code_expr(code_builder, identado);
	        right.generate_code_expr(code_builder, identado);
	        code_builder.append(indent + "i32.lt_s\n"); // Para enteros con signo
	    }else if(kind == KindE.MENORIGUAL) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.leq\n");
	    }else if(kind == KindE.MAYOR) {
	    	left.generate_code_expr(code_builder, identado);
	        right.generate_code_expr(code_builder, identado);
	        code_builder.append(indent + "i32.gt_s\n"); // Para enteros con signo
	    }
	}
	
	@Override
	public void generate_code_expr_param(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
	    String indent = StringUtils.generate_identation(identado); 
	    if(kind == KindE.SUMA) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.add\n");
	    }else if(kind == KindE.RESTA) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.sub\n");
	    }else if (kind == KindE.MULTIPLICACION) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.mul\n");
	    }else if(kind == KindE.MODULO) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.rem_u\n");
	    }else if(kind == KindE.DIVISION) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.div_s\n");
	    }else if(kind == KindE.AND) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.and\n");
	    }else if(kind == KindE.OR) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.or\n");
	    }else if(kind == KindE.ACCESO_ARRAY) {
	    	//Se implementa en su clase
	    }else if(kind == KindE.ACCESO_DIRECCION) {
	    	//Se implementa en su clase
	    }else if(kind == KindE.ACCESO_PUNT) {
	    	//Se implementa en suu clase
	    }else if(kind == KindE.DECR) {
	    	//Unario
	    }else if(kind == KindE.DIST) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.ne\n");
	    }else if(kind == KindE.FLECHA) {
	    	//Se implementa en su clase
	    }else if(kind == KindE.IGUAL) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.eq\n");
	    }else if(kind == KindE.INCR) {
	    	//Se implementa en su clase
	    }else if(kind==KindE.MAYORIGUAL) {
	    	left.generate_code_expr(code_builder, identado);
	        right.generate_code_expr(code_builder, identado);
	        code_builder.append(indent + "i32.ge_s\n"); // Para enteros con signo
	    }else if(kind == KindE.MENOR) {
	    	left.generate_code_expr(code_builder, identado);
	        right.generate_code_expr(code_builder, identado);
	        code_builder.append(indent + "i32.lt_s\n"); // Para enteros con signo
	    }else if(kind == KindE.MENORIGUAL) {
	    	left.generate_code_expr(code_builder, identado);
	    	right.generate_code_expr(code_builder, identado);
	    	code_builder.append(indent + "i32.leq\n");
	    }else if(kind == KindE.MAYOR) {
	    	left.generate_code_expr(code_builder, identado);
	        right.generate_code_expr(code_builder, identado);
	        code_builder.append(indent + "i32.gt_s\n"); // Para enteros con signo
	    }
	}


	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.left.asignarTamanosMemoriaTipos();
		this.right.asignarTamanosMemoriaTipos();
	}


	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return dirPadre;
	}


	@Override
	public void bind() {
		// TODO Auto-generated method stub
		left.bind();
		right.bind();
	}


	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		left.chequea();
		right.chequea();
		Tipo t1 = left.getType();
		Tipo t2 = right.getType();
		if(t1 instanceof TipoConst) {
			t1 = t1.getType();
		}
		if(t2 instanceof TipoConst) {
			t2 = t2.getType();
		}
		switch (this.kind()) {
        case SUMA:
            if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion SUMA");
            } else {
                this.setType(new TipoInt());
            }
            break;
        case RESTA:
            if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion RESTA");
            } else {
                this.setType(new TipoInt());
            }
            break;
        case MULTIPLICACION:
            if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion MULTIPLICACIÓN");
            } else {
                this.setType(new TipoInt());
            }
            break;
        case DIVISION:
            if (!(t1 instanceof TipoInt && t2 instanceof TipoInt)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion DIVISIÓN");
            } else {
                this.setType(new TipoInt());
            }
            break;
        case MODULO:
            if (!(t1 instanceof TipoInt && t2 instanceof TipoInt)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion MÓDULO");
            } else {
                this.setType(new TipoInt());
            }
            break;
        case IGUAL:
            if (!(t1 instanceof TipoInt && t2 instanceof TipoInt)){
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion IGUAL");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case DIST:
              if (!(t1 instanceof TipoInt && t2 instanceof TipoInt)){
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion DIST");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case MENOR:
            if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)){
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion MENOR");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case MAYOR:
            if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)){
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion MAYOR");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case MENORIGUAL:
            if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)){
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion MENORIGUAL");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case MAYORIGUAL:
             if(!(t1 instanceof TipoInt && t2 instanceof TipoInt)){
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion MAYORIGUAL");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case AND:
            if (!(t1 instanceof TipoBool && t2 instanceof TipoBool)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion AND");
            } else {
                this.setType(new TipoBool());
            }
            break;
        case OR:
            if (!(t1 instanceof TipoBool && t2 instanceof TipoBool)) {
                //ERROR
                this.setType(null);
                throw new RuntimeException("Tipos incompatibles para la operacion OR");
            } else {
                this.setType(new TipoBool());
            }
            break;
        default:
            //ERROR
            this.setType(null);
		}
	}
}
