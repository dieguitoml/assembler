package ast;

public class S extends ASTNode{
	    private S2 s2; 
	    private DeclaracionFuncion funcionMain;  


	    public S(S2 s2, DeclaracionFuncion funcionMain) {
	    	this.b = new Binding();
	        this.s2 = s2;
	        this.funcionMain = funcionMain;
	    }
	    
	    
	    public DeclaracionFuncion getMain() {
	    	return this.funcionMain;
	    }
	    
	    public S2 getChild() {
	    	return this.s2;
	    }

	    @Override
	    public String toString() {
	        return "Programa(" + s2.toString() + "," + funcionMain.toString() + ")";
	    }

		@Override
		public NodeKind nodeKind() {
			// TODO Auto-generated method stub
			return NodeKind.PROGRAM;
		}

		@Override
		public void setType(Tipo t) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'setType'");
		}

		@Override
		public Tipo getType() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'getType'");
		}

		@Override
		public void generate_code_instr(StringBuilder code_builder, int identado) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generate_code_desig(StringBuilder code_builder, int identado) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generate_code_expr(StringBuilder code_builder, int identado) {
			// TODO Auto-generated method stub
			 
			String indent = StringUtils.generate_identation(identado); 
			
			code_builder.append(indent + "(module\n");
			code_builder.append(indent + "(type $_sig_i32i32i32 (func (param i32 i32 i32) ))\n");
			code_builder.append(indent + "(type $_sig_i32ri32 (func (param i32) (result i32)))\n");
			code_builder.append(indent + "(type $_sig_i32 (func (param i32)))\n");
			code_builder.append(indent + "(type $_sig_ri32 (func (result i32)))\n");
			code_builder.append(indent + "(type $_sig_void (func ))");
			code_builder.append(indent + "(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\n");
			code_builder.append(indent + "(import \"runtime\" \"print\" (func $print (type $_sig_i32)))\n");
			code_builder.append(indent + "(import \"runtime\" \"read\" (func $read (type $_sig_ri32)))\n");
			code_builder.append(indent + "(memory 2000)\n");
			code_builder.append(indent + "(global $SP (mut i32) (i32.const 0)) ;; start of stack\n");
			code_builder.append(indent +  "(global $MP (mut i32) (i32.const 0)) ;; mark pointer\n");
			code_builder.append(indent + "(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\n");
			code_builder.append(indent + "(start $init)\n");
			
			generate_code_reserveStack(code_builder,identado);
			
			generate_code_freeStack(code_builder,identado);
			
			generate_code_reserveHeap(code_builder,identado);
			
			s2.generate_code_instr(code_builder, identado);//Generamos el cï¿½digo de funciones y clases 
			funcionMain.generate_code_instr(code_builder, identado);
			
			generate_code_init(code_builder, identado);
			code_builder.append(indent + ")\n");
			
		}
		
		public static void generate_code_reserveStack(StringBuilder code_builder, int identado) {
			String indent = StringUtils.generate_identation(identado); 
			code_builder.append(indent + "(func $reserveStack (param $size i32)\n");
			String indent1 = StringUtils.generate_identation(identado + 1); 
			code_builder.append(indent1+ "(result i32)\n");
			code_builder.append(indent1+ "global.get $MP\n");
			code_builder.append(indent1+ "global.get $SP\n");
			code_builder.append(indent1+ "global.set $MP\n");
			code_builder.append(indent1+ "global.get $SP\n");
			code_builder.append(indent1+ "local.get $size\n");
			code_builder.append(indent1+ "i32.add\n");
			code_builder.append(indent1+ "global.set $SP\n");
			code_builder.append(indent1+ "global.get $SP\n");
			code_builder.append(indent1+ "global.get $NP\n");
			code_builder.append(indent1+ "i32.gt_u\n");
			code_builder.append(indent1+ "if\n");
			code_builder.append(indent1+ "i32.const 3\n");
			code_builder.append(indent1+ "call $exception\n");
			code_builder.append(indent1+ "end\n");
			code_builder.append(indent+ ")\n");
		}
		
		public static void generate_code_freeStack(StringBuilder code_builder, int identado) {
			String indent = StringUtils.generate_identation(identado); 
			code_builder.append(indent + "(func $freeStack (type $_sig_void)\n");
			String indent1 = StringUtils.generate_identation(identado + 1); 
			code_builder.append(indent1+ "global.get $MP\n");
			code_builder.append(indent1+ "global.set $SP\n");
			code_builder.append(indent1+ "global.get $MP\n");
			code_builder.append(indent1+ "i32.load\n");
			code_builder.append(indent1+ "global.set $MP\n");
			code_builder.append(indent+ ")\n");
		}
		
		public static void generate_code_reserveHeap(StringBuilder code_builder, int identado) {
			String indent = StringUtils.generate_identation(identado); 

		    code_builder.append(indent + "(func $reserveHeap (param $size i32) (result i32)\n");
		  
			String indent1 = StringUtils.generate_identation(identado + 1); 
			code_builder.append(indent1 + "(local $reserva i32)\n"); 
			code_builder.append(indent1+ "global.get $NP\n");
			code_builder.append(indent1+ "local.get $size\n");
			code_builder.append(indent1+ "i32.sub\n");
			code_builder.append(indent1+ "local.tee $reserva      ;; guardamos la nueva direccion\n");
			code_builder.append(indent1+ "global.set $NP          ;; actualizamos el NP\n");
			code_builder.append(indent1 + "local.get $reserva      ;; devolvemos dirección reservada\n");
			code_builder.append(indent+ ")\n");
		}
		private void generate_code_init(StringBuilder code_builder, int identado) {
			
			String indent = StringUtils.generate_identation(identado); 
			code_builder.append(indent + "(func $init (type $_sig_void)\n");
			String indent1 = StringUtils.generate_identation(identado + 1); 
			code_builder.append(indent1+ "(local $temp i32)\n");
			code_builder.append(indent1+ "i32.const " + this.tam_memoria + ";; let this be the stack size needed (params+locals+2)*4\n");
			code_builder.append(indent1 + "global.set $SP ;;ACTUALIZAMOS EL SP\n");
			s2.generate_code_expr(code_builder, identado);
			code_builder.append(indent1 + "i32.const " + funcionMain.asignarTamanoMemoriaMarcos() + "\n");
			code_builder.append(indent1+ "call $reserveStack  ;; returns old MP (dynamic link)\n");
			code_builder.append(indent1+ "local.set $temp\n");
			code_builder.append(indent1+ "global.get $MP\n");
			code_builder.append(indent1+ "local.get $temp\n");
			code_builder.append(indent1+ "i32.store\n");
			code_builder.append(indent1+ "i32.const 0 		;;The link is to 0\n");
			code_builder.append(indent1+ "i32.const 0 		;;Initialize temp\n");
			code_builder.append(indent1+ "call $main\n");
			code_builder.append(indent1+ "i32.const 0\n");
			code_builder.append(indent1 + "global.set $SP\n");
			code_builder.append(indent+ ")\n");
		}

		@Override
		public void asignarTamanosMemoriaTipos() {
			// TODO Auto-generated method stub
			this.s2.asignarTamanosMemoriaTipos();
			this.funcionMain.asignarTamanosMemoriaTipos();
		}

		@Override
		public int calcularMemoria() {
			// TODO Auto-generated method stub
			int tam = 0;
			tam+=this.s2.calcularMemoria();
			tam+=this.funcionMain.calcularMemoria();
			this.tam_memoria = tam;//Tamaï¿½o total para el bloque de declaraciones
			return tam;
		}

		@Override
		public int asingarDesplazamiento(int dirPadre) {
			// TODO Auto-generated method stub
			int dirLocal = 0;
			dirLocal = s2.asingarDesplazamiento(dirLocal);
			dirLocal = funcionMain.asingarDesplazamiento(dirLocal);
			return dirPadre;
		}

		@Override
		public void bind() {
			// TODO Auto-generated method stub
			s2.bind();
			funcionMain.bind();
			b.closeScope();
		}

		@Override
		public void chequea() {
			// TODO Auto-generated method stub
			s2.chequea();
			funcionMain.chequea();
		}
		
		public Tipo simplifica() {
			s2.simplifica();
			funcionMain.simplifica();
			return null;
		}
}
