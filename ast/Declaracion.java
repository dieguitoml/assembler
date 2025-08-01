package ast;

public abstract class Declaracion extends ASTNode{
    public abstract Tipo getType();
    public void setType(Tipo tipo) {}
    public abstract String toString();
    public NodeKind nodeKind() {return NodeKind.DECLARACION;}
	protected abstract Identificador getIdd();
	public abstract Tipo simplifica();
    
}
