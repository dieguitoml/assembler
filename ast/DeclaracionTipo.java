package ast;

import java.util.List;

public class DeclaracionTipo extends Declaracion{
	private String nombreTipo; // Nombre del tipo (ejemplo: Persona)
    private List<DeclaracionVariable> variables; // Variables dentro de este tipo (solo en el caso de struct o clase)
    
    public DeclaracionTipo(String nombreTipo, List<DeclaracionVariable> variables) {
        this.nombreTipo = nombreTipo;
        this.variables = variables;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("DeclaracionTipo (");
        sb.append(nombreTipo).append(",");
        for (DeclaracionVariable var : variables) {
            sb.append(var.toString()).append(",");
        }
        sb.append(")");
        return sb.toString();
    }

}
