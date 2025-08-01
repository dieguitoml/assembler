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
 ;; DeclaracionFuncion (TipoInt(),Identificador(mayor),BloqueInteriorFuncion ([Parametro(TipoInt(), x)], BloqueFuncion (InstruccionIf(MAYOR(Identificador(x),Identificador(y)),BloqueFuncion (InstruccionReturn(Identificador(x))),else ,BloqueFuncion (InstruccionReturn(Identificador(y)))),InstruccionReturn(Identificador(x)))))
(func $mayor	(param $dirinstancia i32)	(param $temp i32)	(result i32)
    i32.const 12
    global.get $MP
    i32.add
    i32.load
    i32.const 0
    local.get $dirinstancia
    i32.add
    i32.load
    i32.gt_s
    if
        i32.const 12
        global.get $MP
        i32.add
        i32.load
        call $freeStack
        return
    else 
        i32.const 0
        local.get $dirinstancia
        i32.add
        i32.load
        call $freeStack
        return
    end
    i32.const 12
    global.get $MP
    i32.add
    i32.load
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoVoid(),Identificador(main),BloqueInteriorFuncion ([], BloqueFuncion (DeclaracionVariableConAsign(TipoInt(),Identificador(a),Num(6)),DeclaracionVariableConAsign(TipoInt(),Identificador(resultado),InstruccionLlamadaFuncion (Identificador(mayor),[Identificador(a)])),InstruccionCOUT(Identificador(resultado)),return void)))
(func $main	(param $dirinstancia i32)	(param $temp i32)	
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(a),Num(6))
    global.get $MP
    i32.const 12
    i32.add
    ;; Num(6)
    i32.const 6
    i32.store
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(resultado),InstruccionLlamadaFuncion (Identificador(mayor),[Identificador(a)]))
    global.get $MP
    i32.const 16
    i32.add
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
    i32.load
    i32.store
    i32.const 0 ;; el ambito de la funcion
    i32.const 0 		;;Initialize temp
    call $mayor
    i32.store
    i32.const 16
    global.get $MP
    i32.add
    i32.load
    call $print
    call $freeStack
    return
)
(func $init (type $_sig_void)
    (local $temp i32)
    i32.const 4;; let this be the stack size needed (params+locals+2)*4
    global.set $SP ;;ACTUALIZAMOS EL SP
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(y),Num(3))
    global.get $MP
    i32.const 0
    i32.add
    ;; Num(3)
    i32.const 3
    i32.store
    i32.const 20
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
