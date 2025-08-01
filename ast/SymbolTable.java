package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {
    private HashMap<String, List<ASTNode>> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<>();
    }
    
    public void insertFuncion(String id, ASTNode node) {
    	List<ASTNode> a = symbolTable.get(id);
    	for(ASTNode ast: a) {
    		if(ast.equals(node)) {
    			//Errpr
    		}
    	}
    	symbolTable.get(id).add(node);
    }

    public void insert(String id, ASTNode node) {
    	List<ASTNode> l= new ArrayList<ASTNode>();
    	l.add(node);
        symbolTable.put(id, l);
    }

    public List<ASTNode> lookup(String id) {
        return symbolTable.get(id);  
    }
}
