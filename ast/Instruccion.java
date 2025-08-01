package ast;

public abstract class Instruccion extends E{

	@Override
	public NodeKind nodeKind() {
		// TODO Auto-generated method stub
		return NodeKind.INSTRUCCION;
	}

		
	@Override
	public KindE kind() {
		return KindE.INSTRUCCION;
	}
	
	public abstract String toString();
}
