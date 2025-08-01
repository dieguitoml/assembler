package ast;

import java.util.ArrayList;
import java.util.List;

public abstract class Tipo extends ASTNode {
    public abstract String toString();
    public abstract Tipo simplifica();
    public TipoKind t;
    @Override
    public void setType(Tipo tipo) {
    }
    
    @Override
    public Tipo getType() {
        return this;
    }
    
    @Override
    public Tipo getTipo() {
    	return this;
    }

    @Override
    public NodeKind nodeKind(){
        return NodeKind.TIPO;
    }
    
    public TipoKind get() {
    	return this.t;
    }
    
    public boolean equals(Tipo t) {
    	if(this.t.getClass() == t.get().getClass()) {
    		return true;
    	}
    	return false;
    }
	
	public  List<ASTNode> getVinculo() {
		List<ASTNode> l = new ArrayList<>();
    	l.add(this);
    	return l;
	}


}
