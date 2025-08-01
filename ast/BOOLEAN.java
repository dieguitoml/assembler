package ast;

public class BOOLEAN extends E {
    private boolean value;
    private int tam;
    private Tipo t;

    public BOOLEAN(boolean value) {
        this.value = value;
        this.setDireccionable(false);
    }

    public KindE kind() {
        return KindE.BOOLEAN;  // o el valor que representes para booleanos
    }
    
    public Tipo getType() {
    	return t;
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }

    public boolean getValue() {
        return value;
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

	    int value = this.getValue() ? 1 : 0; 

	    code_builder.append(indent + ";; BOOLEAN" + toString() + "\n");
	    code_builder.append(indent + "i32.const " + value + "\n");
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.tam = 2;
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return this.tam;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		desplazamiento = dirPadre;
		return dirPadre + this.calcularMemoria();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		this.t = new TipoBool();
	}
}
