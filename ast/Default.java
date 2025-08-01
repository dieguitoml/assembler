package ast;

public class Default extends Caso{

    private BloqueFuncion bloque;
    public Default(BloqueFuncion bloque) {
        //TODO Auto-generated constructor stub
        super(null,bloque);
        this.bloque = bloque;
    }

    @Override
    public String toString() {
        return "Default ("  +  bloque.toString() + ")";
    }

	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		bloque.generate_code_instr(code_builder, identado);
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
		bloque.asignarTamanosMemoriaTipos();
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return bloque.calcularMemoria();
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		int dirLocal = 0;
		dirLocal = bloque.asingarDesplazamiento(dirLocal);
		return dirPadre;
	}
}
