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
 ;; DeclaracionFuncion (TipoVoid(),Identificador(Calculadora),BloqueInteriorFuncion ([], BloqueFuncion (return void)))
(func $Calculadora	(param $dirinstancia i32)	(param $temp i32)	
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoInt(),Identificador(multiplicar),BloqueInteriorFuncion ([Parametro(TipoInt(), b), Parametro(TipoInt(), a)], BloqueFuncion (InstruccionReturn(MULTIPLICACION(Identificador(a),Identificador(b))))))
(func $multiplicar	(param $dirinstancia i32)	(param $temp i32)	(result i32)
    i32.const 16
    global.get $MP
    i32.add
    i32.load
    i32.const 12
    global.get $MP
    i32.add
    i32.load
    i32.mul
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoVoid(),Identificador(mostrarResultado),BloqueInteriorFuncion ([Parametro(TipoInt(), valor)], BloqueFuncion (InstruccionCOUT(Identificador(valor)),return void)))
(func $mostrarResultado	(param $dirinstancia i32)	(param $temp i32)	
    i32.const 12
    global.get $MP
    i32.add
    i32.load
    call $print
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoInt(),Identificador(obtenerMaximo),BloqueInteriorFuncion ([Parametro(TipoArray(TipoInt(),Dimension (Num(5))), datos)], BloqueFuncion (DeclaracionVariableConAsign(TipoInt(),Identificador(max),AccesoArray(Identificador(datos)Num(0))),DeclaracionVariable(TipoInt() Identificador(i)),InstruccionFor(DeclaracionVariableConAsign(TipoInt(),Identificador(i),Num(1)),MENOR(Identificador(i),Num(5)),InstruccionAsignacion(Identificador(i),SUMA(Identificador(i),Num(1))),BloqueFuncion (InstruccionIf(MAYOR(AccesoArray(Identificador(datos)Identificador(i)),Identificador(max)),BloqueFuncion (InstruccionAsignacion(Identificador(max),AccesoArray(Identificador(datos)Identificador(i)))),))),InstruccionReturn(Identificador(max)))))
(func $obtenerMaximo	(param $dirinstancia i32)	(param $temp i32)	(result i32)
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(max),AccesoArray(Identificador(datos)Num(0)))
    global.get $MP
    i32.const 32
    i32.add
    i32.const 12
    global.get $MP
    i32.add
    i32.const 0
    ;; Num(0)
    i32.const 0
    i32.add
    i32.const 4
    i32.mul
    i32.add
    i32.load
    i32.store
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoInt() Identificador(i))
    global.get $MP
    i32.const 36
    i32.add
    i32.const 0
    i32.store
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(i),Num(1))
    global.get $MP
    i32.const 40
    i32.add
    ;; Num(1)
    i32.const 1
    i32.store
    block
        loop
            i32.const 40
            global.get $MP
            i32.add
            i32.load
            ;; Num(5)
            i32.const 5
            i32.lt_s
            i32.eqz
            br_if 1
            i32.const 12
            global.get $MP
            i32.add
            i32.const 0
            i32.const 40
            global.get $MP
            i32.add
            i32.load
            i32.add
            i32.const 4
            i32.mul
            i32.add
            i32.load
            i32.const 32
            global.get $MP
            i32.add
            i32.load
            i32.gt_s
            if
                ;; InstruccionAsignacion(Identificador(max),AccesoArray(Identificador(datos)Identificador(i)))
            end
            ;; InstruccionAsignacion(Identificador(i),SUMA(Identificador(i),Num(1)))
            br 0
        end
    end
    i32.const 32
    global.get $MP
    i32.add
    i32.load
    call $freeStack
    return
)
 ;; DeclaracionFuncion (TipoVoid(),Identificador(main),BloqueInteriorFuncion ([], BloqueFuncion (DeclaracionVariable(TipoInt() Identificador(i)),DeclaracionVariable(TipoIdentificador(Calculadora) Identificador(calc)),DeclaracionVariableConAsign(TipoArray(TipoInt(),Dimension (Num(5))),Identificador(entrada),Num(2), Num(9), Num(3), Num(4), Num(1)),InstruccionFor(DeclaracionVariableConAsign(TipoInt(),Identificador(i),Num(0)),MENOR(Identificador(i),Num(5)),InstruccionAsignacion(Identificador(i),SUMA(Identificador(i),Num(1))),BloqueFuncion (InstruccionAsignacion(AccesoArray(Identificador(resultados)Identificador(i)),AccesoPunto (Identificador(calc),InstruccionLlamadaFuncion (Identificador(multiplicar),[Num(2), AccesoArray(Identificador(entrada)Identificador(i))]))))),DeclaracionVariableConAsign(TipoInt(),Identificador(maximo),InstruccionLlamadaFuncion (Identificador(obtenerMaximo),[Identificador(resultados)])),DeclaracionVariableConAsign(TipoInt(),Identificador(hola),AccesoArray(Identificador(entrada)Num(4))),DeclaracionVariableConAsign(TipoInt(),Identificador(adios),AccesoArray(Identificador(entrada)Num(3))),InstruccionSwitch(Identificador(maximo), case(Num(0),BloqueFuncion (InstruccionCOUT(Identificador(hola)))), Default (BloqueFuncion (InstruccionCOUT(Identificador(adios))))),while(MENOR(Identificador(i),Num(5)),BloqueFuncion (InstruccionCOUT(AccesoArray(Identificador(resultados)Identificador(i))),InstruccionAsignacion(Identificador(i),SUMA(Identificador(i),Num(1))))),return void)))
(func $main	(param $dirinstancia i32)	(param $temp i32)	
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoInt() Identificador(i))
    global.get $MP
    i32.const 12
    i32.add
    i32.const 0
    i32.store
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoIdentificador(Calculadora) Identificador(calc))
    global.get $MP
    i32.const 16
    i32.add
    i32.const 0
    i32.store
    ;; DeclaracionVariableConAsign(TipoArray(TipoInt(),Dimension (Num(5))),Identificador(entrada),Num(2), Num(9), Num(3), Num(4), Num(1))
    global.get $MP
    i32.const 16
    i32.add
    ;; Num(1)
    i32.const 1
    i32.store
    global.get $MP
    i32.const 20
    i32.add
    ;; Num(4)
    i32.const 4
    i32.store
    global.get $MP
    i32.const 24
    i32.add
    ;; Num(3)
    i32.const 3
    i32.store
    global.get $MP
    i32.const 28
    i32.add
    ;; Num(9)
    i32.const 9
    i32.store
    global.get $MP
    i32.const 32
    i32.add
    ;; Num(2)
    i32.const 2
    i32.store
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(i),Num(0))
    global.get $MP
    i32.const 36
    i32.add
    ;; Num(0)
    i32.const 0
    i32.store
    block
        loop
            i32.const 36
            global.get $MP
            i32.add
            i32.load
            ;; Num(5)
            i32.const 5
            i32.lt_s
            i32.eqz
            br_if 1
            ;; InstruccionAsignacion(AccesoArray(Identificador(resultados)Identificador(i)),AccesoPunto (Identificador(calc),InstruccionLlamadaFuncion (Identificador(multiplicar),[Num(2), AccesoArray(Identificador(entrada)Identificador(i))])))
            i32.const 0
            local.get $dirinstancia
            i32.add
            i32.const 0
            i32.const 36
            global.get $MP
            i32.add
            i32.load
            i32.add
            i32.const 4
            i32.mul
            i32.add
            i32.const 24 ;; DL + dirinstancia + parametros + instrucciones
            call $reserveStack  ;; returns old MP (dynamic link)
            local.set $temp
            global.get $MP
            local.get $temp
            i32.store
            ;;parametro
            global.get $MP
            i32.const 12
            i32.add
            ;; Num(2)
            i32.const 2
            i32.store
            ;;parametro
            global.get $MP
            i32.const 16
            i32.add
            global.get $MP
            i32.const 0
            i32.add
            i32.const 16
            local.get $temp
            i32.add
            i32.const 0
            global.get $MP
            i32.const 0
            i32.add
            i32.const 36
            local.get $temp
            i32.add
            i32.load
            i32.add
            i32.const 4
            i32.mul
            i32.add
            i32.load
            i32.store
            local.get $temp
            i32.const 16
            i32.add ;;el ambito de la funcion
            i32.const 0 		;;Initialize temp
            call $multiplicar
            i32.store
            ;; InstruccionAsignacion(Identificador(i),SUMA(Identificador(i),Num(1)))
            br 0
        end
    end
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(maximo),InstruccionLlamadaFuncion (Identificador(obtenerMaximo),[Identificador(resultados)]))
    global.get $MP
    i32.const 40
    i32.add
    i32.const 48 ;; DL + dirinstancia + parametros + instrucciones
    call $reserveStack  ;; returns old MP (dynamic link)
    local.set $temp
    global.get $MP
    local.get $temp
    i32.store
    ;;parametro
    global.get $MP
    i32.const 12
    i32.add
    local.get $dirinstancia
    i32.const 0
    i32.add
    i32.load
    i32.store
    global.get $MP
    i32.const 16
    i32.add
    local.get $dirinstancia
    i32.const 4
    i32.add
    i32.load
    i32.store
    global.get $MP
    i32.const 20
    i32.add
    local.get $dirinstancia
    i32.const 8
    i32.add
    i32.load
    i32.store
    global.get $MP
    i32.const 24
    i32.add
    local.get $dirinstancia
    i32.const 12
    i32.add
    i32.load
    i32.store
    global.get $MP
    i32.const 28
    i32.add
    local.get $dirinstancia
    i32.const 16
    i32.add
    i32.load
    i32.store
    i32.const 0 ;; el ambito de la funcion
    i32.const 0 		;;Initialize temp
    call $obtenerMaximo
    i32.store
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(hola),AccesoArray(Identificador(entrada)Num(4)))
    global.get $MP
    i32.const 44
    i32.add
    i32.const 16
    global.get $MP
    i32.add
    i32.const 0
    ;; Num(4)
    i32.const 4
    i32.add
    i32.const 4
    i32.mul
    i32.add
    i32.load
    i32.store
    ;; DeclaracionVariableConAsign(TipoInt(),Identificador(adios),AccesoArray(Identificador(entrada)Num(3)))
    global.get $MP
    i32.const 48
    i32.add
    i32.const 16
    global.get $MP
    i32.add
    i32.const 0
    ;; Num(3)
    i32.const 3
    i32.add
    i32.const 4
    i32.mul
    i32.add
    i32.load
    i32.store
    block
    block
    i32.const 40
    global.get $MP
    i32.add
    i32.load
    i32.const 0
    i32.le_s
    if
        i32.const 40
        global.get $MP
        i32.add
        i32.load
        br_table  1        
    else
        i32.const 48
        global.get $MP
        i32.add
        i32.load
        call $print
        br 2
    end
    end
    i32.const 44
    global.get $MP
    i32.add
    i32.load
    call $print
    br 0
    end
    block
        loop
            i32.const 12
            global.get $MP
            i32.add
            i32.load
            ;; Num(5)
            i32.const 5
            i32.lt_s
            i32.eqz
            br_if 1
            i32.const 0
            local.get $dirinstancia
            i32.add
            i32.const 0
            i32.const 12
            global.get $MP
            i32.add
            i32.load
            i32.add
            i32.const 4
            i32.mul
            i32.add
            i32.load
            call $print
            ;; InstruccionAsignacion(Identificador(i),SUMA(Identificador(i),Num(1)))
            br 0
        end
    end
    call $freeStack
    return
)
(func $init (type $_sig_void)
    (local $temp i32)
    i32.const 44;; let this be the stack size needed (params+locals+2)*4
    global.set $SP ;;ACTUALIZAMOS EL SP
    ;; DEC VAR SIN ASIGNDeclaracionVariable(TipoArray(TipoInt(),Dimension (Num(5))) Identificador(resultados))
    global.get $MP
    i32.const 0
    i32.add
    i32.const 0
    i32.store
    i32.const 52
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
