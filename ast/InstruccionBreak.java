package ast;

public class InstruccionBreak extends Instruccion{

    public InstruccionBreak(){
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "InstuccionBreak()";
    }

    public void generate_code_instr(StringBuilder code_builder, int identado) {
		 code_builder.append("br 1\n");
		
	}
	
	public void generate_code_instr(StringBuilder code_builder, int identado, boolean soyBucle) {
		int a = 0;
		if(soyBucle) a = 1;
		 code_builder.append("br " + a + "\n");
		
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
		//Nothing to bind
	}

	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}
    
}
