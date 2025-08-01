package ast;

public class Caso extends Instruccion {
    private E valor; 
    private BloqueFuncion bloque;

    // Constructor
    public Caso(E valor, BloqueFuncion bloque) {
        this.valor = valor;
        this.bloque = bloque;
    }

    public E getValor() {
        return valor;
    }

    public BloqueFuncion getBloque() {
        return bloque;
    }

    @Override
    public String toString() {
        return "case(" + valor.toString() + "," + bloque.toString() + ")";
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
		desplazamiento = dirPadre;
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		valor.bind();
		b.bindInterior(bloque);
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		valor.chequea();
		if(!(valor.getType() instanceof TipoInt)) {
			System.out.print("El switch solo admite casos enteros.\n");
		}
		bloque.chequea();
	}
	
	@Override
	public Tipo simplifica() {
		bloque.simplifica();
		return null;
	}
}
