package ast;

public class TipoInt extends Tipo {
	private int tam  = 4;
    public TipoInt (){
    	this.t = TipoKind.INT;
    }
    @Override
	    public String toString() {
	        return "TipoInt()";
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
		return this.calcularMemoria() + dirPadre;
	}
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Tipo simplifica() {
		// TODO Auto-generated method stub
		return this;
	}
}