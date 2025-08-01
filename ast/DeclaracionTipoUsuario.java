package ast;

public  class DeclaracionTipoUsuario extends Declaracion{
    Tipo tipo;
    TipoIdentificador id;
    

    public DeclaracionTipoUsuario(Tipo tipo, TipoIdentificador id) {
        this.tipo = tipo;
        this.id = id;
        this.direccionable = false;
    }


    public Tipo getTipo() {
        return tipo;
    }


    public TipoIdentificador getId() {
        return id;
    }


    @Override
    public String toString() {
        return "DeclaracionTipoUsuario (tipo=" + tipo.toString() + ", id=" + id.toString() + ")";
    }


	@Override
	public Tipo getType() {
		// TODO Auto-generated method stub
		return this.tipo;
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
		
	}


	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.tipo.asignarTamanosMemoriaTipos();
	}


	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return tipo.calcularMemoria();
		
	}


	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return dirPadre + this.calcularMemoria();
	}


	@Override
	public void bind() {
		// TODO Auto-generated method stub
		tipo.bind();
		b.insert(id.getId(), this);
		
	}


	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		tipo.chequea();
	}


	@Override
	protected Identificador getIdd() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		this.tipo.simplifica();
		return null;
	}	
}
