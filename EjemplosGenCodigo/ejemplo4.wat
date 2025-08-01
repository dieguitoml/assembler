(module
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32ri32 (func (param i32) (result i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $init)
(func $reserveStack (param $size i32)
    (result i32)
    global.get $MP
    global.get $SP
    global.set $MP
    global.get $SP
    local.get $size
    i32.add
    global.set $SP
    global.get $SP
    global.get $NP
    i32.gt_u
    if
    i32.const 3
    call $exception
    end
)
(func $freeStack (type $_sig_void)
    global.get $MP
    global.set $SP
    global.get $MP
    i32.load
    global.set $MP
)
(func $reserveHeap (param $size i32) (result i32)
    (local $reserva i32)
    global.get $NP
    local.get $size
    i32.sub
    local.tee $reserva      ;; guardamos la nueva dirección
    global.set $NP          ;; actualizamos el NP
    local.get $reserva      ;; devolvemos dirección reservada
)
 ;; DeclaracionFuncion (TipoArray(TipoInt(),Dimension (Num(2)),Dimension (Num(3))),Identificador(function),BloqueInteriorFuncion ([], BloqueFuncion (DeclaracionVariable(TipoInt() Identificador(x)),DeclaracionVariable(TipoArray(TipoInt(),Dimension (Num(10))) Identificador(hola)),InstruccionIf(IGUAL(Identificador(a),Num(1)),BloqueFuncion (InstruccionAsignacion(Identificador(x),SUMA(AccesoArray(Identificador(hola)Num(2)),Num(2)))),else ,BloqueFuncion (DeclaracionVariableConAsign(TipoInt(),Identificador(r),MULTIPLICACION(SUMA(Num(2),Num(3)),Num(5))),InstruccionSwitch(Identificador(x), case(Num(1),BloqueFuncion (InstuccionBreak()))))),DeclaracionVariable(TipoArray(TipoInt(),Dimension (Num(2)),Dimension (Num(3))) Identificador(basado)),InstruccionReturn(Identificador(basado)))))
(func $function	(param $dirinstancia i32)	(param $temp i32)	(result i32)
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoInt() Identificador(x))
    global.get $MP
    i32.const 12
    i32.add
    i32.const 0
    i32.store
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoArray(TipoInt(),Dimension (Num(10))) Identificador(hola))
    global.get $MP
    i32.const 16
    i32.add
    i32.const 0
    i32.store
    i32.const 0
    local.get $dirinstancia
    i32.add
    i32.load
    ;; Num(1)
    i32.const 1
    i32.eq
    if
        ;; InstruccionAsignacion(Identificador(x),SUMA(AccesoArray(Identificador(hola)Num(2)),Num(2)))
    else 
        ;; DeclaracionVariableConAsign(TipoInt(),Identificador(r),MULTIPLICACION(SUMA(Num(2),Num(3)),Num(5)))
        global.get $MP
        i32.const 56
        i32.add
        ;; Num(2)
        i32.const 2
        ;; Num(3)
        i32.const 3
        i32.add
        ;; Num(5)
        i32.const 5
        i32.mul
        i32.store
        block
        block
        block
        i32.const 12
        global.get $MP
        i32.add
        i32.load
        br_table  0 1        
        end
        br 1
        end
br 1
        br 0
        end
    end
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoArray(TipoInt(),Dimension (Num(2)),Dimension (Num(3))) Identificador(basado))
    global.get $MP
    i32.const 56
    i32.add
    i32.const 0
    i32.store
    global.get $MP
    i32.const 56
    i32.add
    i32.load
    global.get $MP
    i32.const 60
    i32.add
    i32.load
    global.get $MP
    i32.const 64
    i32.add
    i32.load
    global.get $MP
    i32.const 68
    i32.add
    i32.load
    global.get $MP
    i32.const 72
    i32.add
    i32.load
    global.get $MP
    i32.const 76
    i32.add
    i32.load
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoVoid(),Identificador(main),BloqueInteriorFuncion ([], BloqueFuncion (return void)))
(func $main	(param $dirinstancia i32)	(param $temp i32)	
    call $freeStack
    return
)
(func $init (type $_sig_void)
    (local $temp i32)
    i32.const 4;; let this be the stack size needed (params+locals+2)*4
    global.set $SP ;;ACTUALIZAMOS EL SP
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(a),SUMA(Num(2),Num(2)))
    global.get $MP
    i32.const 0
    i32.add
    ;; Num(2)
    i32.const 2
    ;; Num(2)
    i32.const 2
    i32.add
    i32.store
    i32.const 12
    call $reserveStack  ;; returns old MP (dynamic link)
    local.set $temp
    global.get $MP
    local.get $temp
    i32.store
    i32.const 0 		;;The link is to 0
    i32.const 0 		;;Initialize temp
    call $main
    i32.const 0
    global.set $SP
)
)
