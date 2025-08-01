package alex;

import errors.GestionErroresExp;

%%
%cup
%column
%line
%class AnalizadorLexicoExp
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

// Identificadores y literales
letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}* | 0)
parteDecimal = {digito}* {digitoPositivo}
separador = [\t\r\b\n]
other_id_char   = [_]
identifier      = {letra}({letra}|{digito}|{other_id_char})*
numeroEntero = {parteEntera}
numeroReal = {parteEntera}\.{parteDecimal}

// Comentarios
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Comments = {LineComment} | {BlockComment}
LineComment = "//" [^\n]* 
BlockComment =  "/*" [^*] ~"*/" | "/*" "*"+ "/"

// Espacios en blanco y salto de línea
BlankSpace = {LineTerminator} | [ \t\f]


// Definición de expresiones regulares para los operadores y palabras clave
operadorChar = char
operadorString = string
operadorCout = cout
operadorCin = cin
operadorIf = if
operadorElse = else
operadorSwitch = switch
operadorCase = case
operadorDefault = default
operadorReturn = return
operadorWhile = while
operadorFor = for
operadorBreak = break
operadorNew = new
operadorTrue = true
operadorFalse = false
operadorBool = bool
operadorInt = int
operadorVoid = void
operadorStruct = struct
operadorClass = class
operadorType = type
operadorMain = main

operadorConstante = const

// Operadores
asignacion = \=
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
operadorModulo = \%

igual = \==
distinto = \!=
operadorMenor = <
operadorMayor = >
operadorMenorIgual = <=
operadorMayorIgual = >=

this = this
flecha = ->

operadorAnd = &&
operadorOr = \|\|
operadorNot = \!

operadorDireccion = &
operadorContenidoPuntero = @
operadorAcceso = \.

// Paréntesis, corchetes y llaves

parentesisApertura = \(
parentesisCierre = \)
corcheteAbierto = \[
corcheteCerrado = \]
llaveApertura = "{"
llaveCierre = "}"

coma = ,
puntoYComa = ;
dosPuntos = :




// Reglas léxicas

%%
    {separador}             {}
    {Comments}              { /* ignorar comentarios */ }
    {BlankSpace}            { /* ignorar espacios */ }
    {operadorMain} 		{ return ops.unidadOperadorMain(); }
    {numeroEntero}             { return ops.unidadEnt(); }  
    {numeroReal}               { return ops.unidadReal(); }
    {dosPuntos} 			 {return ops.unidadDosPuntos();}
  // Palabras clave
  {operadorMenor}          { return ops.unidadMenor(); }
  {operadorMayor}          { return ops.unidadMayor(); }
  {operadorMenorIgual}     { return ops.unidadMenorIgual(); }
  {operadorMayorIgual}     { return ops.unidadMayorIgual(); }

  {operadorAnd}            { return ops.unidadAnd(); }
  {operadorOr}             { return ops.unidadOr(); }
  {operadorNot}            { return ops.unidadNot(); }

  {operadorDireccion}      { return ops.unidadDireccion(); }
  {operadorContenidoPuntero}   { return ops.unidadContenidoPuntero(); }
	

  {operadorAcceso}         { return ops.unidadAcceso(); }

  {corcheteAbierto}        { return ops.unidadCorcheteAbierto(); }
  {corcheteCerrado}        { return ops.unidadCorcheteCerrado(); }
  {parentesisApertura}     { return ops.unidadPAp(); }
  {parentesisCierre}       { return ops.unidadPCierre(); }
  {llaveApertura}         { return ops.unidadLlaveApertura(); }
  {llaveCierre}           { return ops.unidadLlaveCierre(); }
  {coma}                  { return ops.unidadComa(); }
  {puntoYComa}            { return ops.unidadPuntoYComa(); }
  {asignacion}            { return ops.unidadAsignacion(); }
  {this}                  { return ops.unidadThis();   }
  {flecha}                { return ops.unidadFlecha(); }
  
  {operadorChar} { errores.errorLexico(fila(),columna(),lexema()); }
  {operadorString}  {errores.errorLexico(fila(),columna(),lexema()); }
  {operadorCout}           { return ops.unidadCout(); }
  {operadorCin}            { return ops.unidadCin(); }
  {operadorBool}           { return ops.unidadBool(); }
  {operadorIf}             { return ops.unidadIf(); }
  {operadorElse}           { return ops.unidadElse(); }
  {operadorSwitch}         { return ops.unidadSwitch(); }
  {operadorCase}           { return ops.unidadCase(); }
  {operadorDefault}        { return ops.unidadDefault(); }
  {operadorReturn}         { return ops.unidadReturn(); }
  {operadorWhile}          { return ops.unidadWhile(); }
  {operadorFor}            { return ops.unidadFor(); }
  {operadorBreak}          { return ops.unidadBreak(); }
  {operadorNew}            { return ops.unidadNew(); }
  {operadorTrue}           { return ops.unidadTrue(); }
  {operadorFalse}          { return ops.unidadFalse(); }
  {operadorInt}            { return ops.unidadInt(); }
  {operadorVoid}           { return ops.unidadVoid(); }
  {operadorStruct}         { return ops.unidadStruct(); }
  {operadorClass}          { return ops.unidadClass(); }
  {operadorType}           { return ops.unidadType(); }
  {operadorConstante}      { return ops.unidadConst(); }
  {identifier}             { return ops.unidadId(); }

  // Operadores
  {operadorSuma}           { return ops.unidadSuma(); }
  {operadorResta}          { return ops.unidadResta(); }
  {operadorMultiplicacion} { return ops.unidadMultiplicacion(); }
  {operadorDivision}       { return ops.unidadDivision(); }
  {operadorModulo}         { return ops.unidadModulo(); }

  {igual}                  { return ops.unidadIgual(); }
  {distinto}               { return ops.unidadDistinto(); }
  


  // Errores léxicos
  [^] { errores.errorLexico(fila(),columna(),lexema()); }


