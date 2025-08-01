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
 ;; DeclaracionFuncion (TipoVoid(),Identificador(incrementar),BloqueInteriorFuncion ([Parametro(TipoInt(), a)], BloqueFuncion (DeclaracionVariableConAsign(TipoPuntero (TipoInt()),Identificador(b),NEW (TipoPuntero (TipoInt()))),InstruccionAsignacion(AccesoPuntero(Identificador(b)),Identificador(a)),InstruccionCOUT(AccesoPuntero(Identificador(b))),return void)))
(func $incrementar	(param $dirinstancia i32)	(param $temp i32)	
    ;; DeclaracionVariableConAsign(TipoPuntero (TipoInt()),Identificador(b),NEW (TipoPuntero (TipoInt())))
    global.get $MP
    i32.const 16
    i32.add
     ;; NEW EXPRESION INICIO
    i32.const 4
    call $reserveHeap
     ;;NEW EXPRESION FIN
    i32.store
    ;; InstruccionAsignacion(AccesoPuntero(Identificador(b)),Identificador(a))
    i32.const 16
    global.get $MP
    i32.add
    i32.load
    i32.const 12
    global.get $MP
    i32.add
    i32.load
    i32.load
    i32.store
    i32.const 16
    global.get $MP
    i32.add
    i32.load
    i32.load
    call $print
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoVoid(),Identificador(main),BloqueInteriorFuncion ([], BloqueFuncion (DeclaracionVariableConAsign(TipoInt(),Identificador(a),Num(1)),InstruccionLlamadaFuncion (Identificador(incrementar),[Identificador(a)]),return void)))
(func $main	(param $dirinstancia i32)	(param $temp i32)	
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(a),Num(1))
    global.get $MP
    i32.const 12
    i32.add
    ;; Num(1)
    i32.const 1
    i32.store
    i32.const 20 ;; DL + dirinstancia + parametros + instrucciones
    call $reserveStack  ;; returns old MP (dynamic link)
    local.set $temp
    global.get $MP
    local.get $temp
    i32.store
    ;;parametro
    global.get $MP
    i32.const 12
    i32.add
    i32.const 12
    local.get $temp
    i32.add
    i32.store
    i32.const 0 ;; el ambito de la funcion
    i32.const 0 		;;Initialize temp
    call $incrementar
    call $freeStack
    return
)
(func $init (type $_sig_void)
    (local $temp i32)
    i32.const 0;; let this be the stack size needed (params+locals+2)*4
    global.set $SP ;;ACTUALIZAMOS EL SP
    i32.const 16
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
