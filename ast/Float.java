package ast;

public class Float extends E {
    private float value;
    private int tam;

    public Float(float value) {
        this.value = value;
        this.setDireccionable(false);
    }

    public KindE kind() {
        return KindE.FLOAT;  // o el valor que representes para flotantes
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public float getValue() {
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
		
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.tam = 4;
	}

	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		return tam;
	}

	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		return dirPadre;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}
}

