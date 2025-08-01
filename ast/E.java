package ast;

public abstract class E extends ASTNode {
    private Tipo tipo;

    @Override
    public void setType(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public Tipo getType() {
        return tipo;
    }
    @Override
    public Tipo getTipo() {
        return tipo;
    }

    public abstract KindE kind();
    public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public ASTNode opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public String num() {throw new UnsupportedOperationException("num");}
    public NodeKind nodeKind() {return NodeKind.EXPRESION;}
    public String toString() {return "";}
    public void generate_code_expr_mem(StringBuilder code_builder, int identado){ throw new UnsupportedOperationException(
        "generate_code_expr_mem no está soportado para esta expresión: " + this.getClass().getSimpleName()
    );}

	public void generate_code_expr_mem_param(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	public void generate_code_expr_param(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}

	public void generate_code_expr_mem_param(StringBuilder code_builder, int identado, boolean store) {
		// TODO Auto-generated method stub
		
	}

	public void generate_code_expr_param(StringBuilder code_builder, int identado, boolean store) {
		// TODO Auto-generated method stub
		
	}

}
    