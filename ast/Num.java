package ast;

public class Num extends E {
  private String v;
  private Tipo t;
  public Num(String v) {
   this.v = v;   
   this.setDireccionable(false);
  }
  public int getV() {
	return Integer.parseInt(v);
}

  public String num() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return "Num(" + v + ")";}
@Override
public void generate_code_instr(StringBuilder code_builder, int identado) {
	// TODO Auto-generated method stub
	
}

@Override
public Tipo getType() {
	return this.t;
}

@Override
public Tipo getTipo() {
	return this.t;
}
@Override
public void generate_code_desig(StringBuilder code_builder, int identado) {
	// TODO Auto-generated method stub
	
}
@Override
public void generate_code_expr(StringBuilder code_builder, int identado) {
	// TODO Auto-generated method stub
	String indent = StringUtils.generate_identation(identado); 
	code_builder.append(indent + ";; " + toString() + "\n");
	code_builder.append(indent + "i32.const " + getV() + "\n");
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
	
}
@Override
public void chequea() {
	// TODO Auto-generated method stub
	this.t = new TipoInt();
}  

public void generate_code_expr_mem_param(StringBuilder code_builder, int identado) {

}

public void generate_code_expr_param(StringBuilder code_builder, int identado) {
	String indent = StringUtils.generate_identation(identado); 
	code_builder.append(indent + ";; " + toString() + "\n");
	code_builder.append(indent + "i32.const " + getV() + "\n");
	code_builder.append(indent + "i32.store\n");
}
}
