package ast;

public class Dimensiones extends E {
    Num tamDimension;
    int tamOcupado;
    Tipo t;
    public Dimensiones(Num tam){
        this.tamDimension = tam;
    }

    @Override
    public KindE kind() {
        return KindE.NUM;
    }
    @Override
    public String toString() {
        return "Dimension (" + tamDimension.toString() + ")";
    }

	public Num getTamDimension() {
		return tamDimension;
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
		code_builder.append(indent + "i32.const " + tamDimension + "\n");
	}

	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		this.tamOcupado = 4; //(En bytes)
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
		
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		t = new TipoInt();
	}
}

   