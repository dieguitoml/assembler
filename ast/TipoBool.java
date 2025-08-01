package ast;

public class TipoBool extends Tipo {
	int tam;
    public TipoBool (){
    	this.t = TipoKind.BOOL;
    }
    @Override
	    public String toString() {
	        return "TipoBool()";
	        
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
		return dirPadre + this.calcularMemoria();
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