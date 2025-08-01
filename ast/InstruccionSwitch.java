package ast;
import java.util.List;


public class InstruccionSwitch extends Instruccion{

    private Identificador condicion;
    private List<Caso> casos;
    private boolean esDefault;
    private Caso df;

    
    public InstruccionSwitch(Identificador condicion, List<Caso> casos, boolean esDefault, Caso df){
        this.condicion = condicion;
        this.casos = casos;
        this.esDefault = esDefault;
        this.df = df;
    }

    
    public Identificador getCondicion() {
        return condicion;
    }


    public List<Caso> getCasos() {
        return casos;
    }


    public boolean isEsDefault() {
        return esDefault;
    }


    public Caso getDf() {
        return df;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InstruccionSwitch(");
        sb.append(condicion != null ? condicion.toString() : "null");
    
        // Añadir casos
        if (casos != null && !casos.isEmpty()) {
            sb.append(", ");
            for (int i = 0; i < casos.size(); i++) {
                sb.append(casos.get(i).toString());
                if (i < casos.size() - 1) {
                    sb.append(", ");
                }
            }
        }
    
        if (esDefault && df != null) {
            sb.append(", ").append(df.toString());
        }
    
        sb.append(")");
        return sb.toString();
    }



	@Override
	public void generate_code_instr(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		 String indent = StringUtils.generate_identation(identado); 
		 
		 int maxCase = Integer.parseInt(casos.get(0).getValor().num());
		 for(int i = 1;i<casos.size();i++) {
			 int casoAct = Integer.parseInt(casos.get(i).getValor().num());
			 if(casoAct>maxCase)maxCase = casoAct;
		 }
		 
		 
		 code_builder.append(indent + "block\n");//Bloque para salirnos del switch
		 for(int i=0;i<maxCase+1;i++) {//Bloques del 0 al maxCase
			 code_builder.append(indent + "block\n");
			 
		 }
		 
		 int identadoAux = identado;
		 int extra = 0;
		 if(this.isEsDefault()) {
			 condicion.generate_code_expr(code_builder, identado);
			 code_builder.append(indent + "i32.const " + maxCase +"\n");
			 code_builder.append(indent + "i32.le_s\n");
			 code_builder.append(indent + "if\n");
			 identadoAux++;
			 extra = 1;
		 }
		 
		 String indent1 =  StringUtils.generate_identation(identadoAux); 

		 condicion.generate_code_expr(code_builder, identadoAux);
		 
		 code_builder.append(indent1 + "br_table ");
		 for(int i=0;i<maxCase+1;i++) {
			 code_builder.append(" " + (i+extra));

		 }
		 code_builder.append(indent1 + "\n");
		 if(this.esDefault) {
			 code_builder.append(indent + "else\n");
			 df.generate_code_instr(code_builder, identadoAux);
			 code_builder.append(indent1 + "br " + (maxCase + 2) + "\n");
			 code_builder.append(indent + "end\n");
		 }
		 
		 for(int i=0;i<maxCase+1;i++) {
			 boolean hayCaso = false;
			 code_builder.append(indent + "end\n");
			 for(Caso c: casos) {
				 if(Integer.parseInt(c.getValor().num())==i) {
					 c.generate_code_instr(code_builder, identado);
					 hayCaso = true;
				 }
			 }
			 if(!hayCaso && this.esDefault) {
				 df.generate_code_instr(code_builder, identado);
			 }
			 code_builder.append(indent + "br " + (maxCase - i) + "\n");
		 }
		 code_builder.append(indent + "end\n");
		 
	}


	@Override
	public void generate_code_desig(StringBuilder code_builder, int identado) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void generate_code_expr(StringBuilder code_builder, int identado) {

	}


	@Override
	public void asignarTamanosMemoriaTipos() {
		// TODO Auto-generated method stub
		for(Caso c:casos) {
			c.asignarTamanosMemoriaTipos();
		}
		if(this.esDefault)df.asignarTamanosMemoriaTipos();
	} 


	@Override
	public int calcularMemoria() {
		// TODO Auto-generated method stub
		int tam = 0;
		for(Caso c:casos) {
			tam += c.calcularMemoria();
		}
		if(this.esDefault)tam+= df.calcularMemoria();
		return tam;
	}


	@Override
	public int asingarDesplazamiento(int dirPadre) {
		// TODO Auto-generated method stub
		int dirLocal = 0;
		dirLocal = condicion.asingarDesplazamiento(dirLocal);
		for(Caso c: casos) {
			c.asingarDesplazamiento(dirLocal);
		}
		return dirPadre;
	}


	@Override
	public void bind() {
		// TODO Auto-generated method stub
		condicion.bind();
    	for(Caso c:casos) {
    		b.openScope();
    		c.bind();
    		b.closeScope();
    	}
    	if(this.isEsDefault()) {
    		b.openScope();
    		b.bindInterior(df.getBloque());
    		b.closeScope();
    	}
	}


	@Override
	public void chequea() {
		// TODO Auto-generated method stub
		this.getCondicion().chequea();
		for(Caso caso:casos) {
			caso.chequea();
		}
	}
	
	@Override
	public Tipo simplifica() {
		for(Caso c:casos) {
    		c.simplifica();
    	}
		if(this.isEsDefault()) {
			df.simplifica();
		}
		return null;
	}
    
    
}
