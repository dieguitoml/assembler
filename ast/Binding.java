package ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import ast.SymbolTable;

public class Binding {
	
    private Stack<SymbolTable> symbolStack;  
    
    public boolean errorBinding = false;
    
    public Binding() {
        symbolStack = new Stack<>();
        symbolStack.push(new SymbolTable()); 
    }

    public void openScope() {
        symbolStack.push(new SymbolTable());
    }

    public void closeScope() {
        if (!symbolStack.isEmpty()) {
            symbolStack.pop();
        } else {
            System.out.println("No se puede cerrar el ambito, pila vacia.");
            errorBinding = true;
        }
    }
    
    public void insert(String id, ASTNode node) {//Aqui miramos sobrecarga de funciones 
        if (node instanceof DeclaracionFuncion) {
            if (symbolStack.peek().lookup(id) != null) {
                // La función ya existe (sobre carga permitida)
            	symbolStack.peek().insertFuncion(id,node);
                return;  // No lanzamos error, porque las funciones pueden estar sobrecargadas
            } else {
                // Si no existe, se inserta normalmente
                symbolStack.peek().insert(id, node);
            }
        } else {
            // Para otros tipos de nodos (variables, tipos, etc.), no permitimos duplicados
            if (symbolStack.peek().lookup(id) != null) {
                System.out.println("El identificador " + id + " ya esta declarado en este ambito.");
                errorBinding = true;
            }
            symbolStack.peek().insert(id, node);
        }
    }

    // Busca un identificador desde el ámbito más cercano
    public List<ASTNode> lookup(String id) {
        for (int i = symbolStack.size() - 1; i >= 0; i--) {
        	List<ASTNode> node = symbolStack.get(i).lookup(id);
            if (node != null) {
                return node;  // Encuentra el identificador
            }
        }
        System.out.println("El identificador " + id + " no esta declarado previamente\n");
        errorBinding = true;
        return null;  
    }
    
    public List<ASTNode> lookup_externo(String id) {
        for (int i = symbolStack.size() - 1; i >= 0; i--) {
        	List<ASTNode> node = symbolStack.get(i).lookup(id);
            if (node != null) {
            	if(i>0) {
            		List<ASTNode> node2 = symbolStack.get(i-1).lookup(id);
            		if(node2!=null) return node2;
            	}
                return node;  // Encuentra el identificador
            }
        }
        System.out.println("El identificador " + id + " no esta declarado previamente\n");
        errorBinding = true;
        return null; 
    }
    

    void bindInterior(BloqueFuncion bf) {
    	List<ASTNode> d = bf.getHijos();
    	for(ASTNode as: d) {
    		as.bind();
    		as.isGlobal  = false;
    		as.set_ambito();
    	}
    }
    

    public boolean contains(String id) {
        return lookup(id) != null;
    }

    private boolean tiposCompatibles(Tipo t1, Tipo t2) {
        if (t1 == null || t2 == null) return false;
        return t1.getClass().equals(t2.getClass());
    }
}
