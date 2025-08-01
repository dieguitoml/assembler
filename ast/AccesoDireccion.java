package ast;

public class AccesoDireccion extends E{
	private final ASTNode objeto;  // El objeto; // La propiedad

    public AccesoDireccion(ASTNode puntero) {
        this.objeto = puntero;
        if(puntero instanceof Parametro) {
            ((Parametro) puntero).setReference();//Si es parametro por referencia, se lo indicamos
        }
    }

    @Override
    public String toString() {
        return "AccesoDireccion(" + objeto.toString() + ")";
    }

	@Override
	public KindE kind() {
		// TODO Auto-generated method stub
		return KindE.ACCESO_DIRECCION;
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
    	// Genera el valor de la direccion de memoria de la variable
    	((E)objeto).generate_code_expr_mem(code_builder, identado);		
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return this.objeto.calcularMemoria();
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		desplazamiento = dirPadre;
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		objeto.bind();
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		objeto.chequea();
		if(!(objeto.direccionable)) {
			System.out.print("No se puede acceder a la posicion de memoria de un objeto no direccionable\n");
			ASTNode.errorTipado=true;
		}else {
			direccionable = true;
		}
		this.setType(new TipoPuntero(objeto.getType()));
		
	}
}
