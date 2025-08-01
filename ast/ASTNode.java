package ast;

import java.util.List;

public abstract class ASTNode {
  
	public boolean isGlobal = true;//Lo usamos para ver si algo de tipo variable es o no global
	public static Binding b;
	public int ambito = 0;
	protected boolean direccionable = true;
	public static boolean errorTipado = false;//Comprobamos si hay error en tipado
	public abstract void bind();
    public int tam_memoria;
    private boolean soyBucle = false;
    public int desplazamiento;
    public abstract void chequea();
	public abstract void  asignarTamanosMemoriaTipos();
	public abstract int calcularMemoria();
	public abstract int asingarDesplazamiento(int dirPadre);
	public int asignarTamanoMemoriaMarcos() {
		return 0;
	}
    public abstract void generate_code_instr(StringBuilder code_builder,int identado); 
    public abstract void generate_code_desig(StringBuilder code_builder,int identado);
    public abstract void generate_code_expr(StringBuilder code_builder,int identado);
    public abstract void setType(Tipo t);
    public abstract Tipo getType();
    public abstract NodeKind nodeKind();
    public abstract String toString();
	public void bind_externo() {
		// TODO Auto-generated method stub
		
	}
	public Tipo simplifica() {
		return null;
	}
	
	public boolean getSoyBucle() {
		return this.soyBucle;
	}
	public void setSoyBucle(boolean b) {
		this.soyBucle = b;
	}
	
	public boolean getIsDireccionable() {
		return direccionable;
	}
	
	public void setDireccionable(boolean t) {
		this.direccionable = t;
	}
	
	public void set_ambito() {
		ambito = 1;
	}
	
	public List<ASTNode> getVinculo(){
		return null;
	}
	
	public int getDesplazamiento() {
		return this.desplazamiento;
	}
	
	public void vinculo(List<ASTNode> vinculo) {
		
	}
	public Tipo getTipo() {
		// TODO Auto-generated method stub
		return null;
	}
}
