package models;

public class Instruction extends Element {

    private String subtype;

    /**
     * Constructor: crea elemento instrucción. Asigna el texto, valor de validez (TRUE o FALSE) y tipo.
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Instruction(String text, boolean valid){
        super(text, valid);
        setType("Instrucción");
        setSubtype();
    }

    /**
     * Asigna el tipo de instrucción según el número de operandos ("sin ops", "un op", "dos ops" o "salto").
     */
    private void setSubtype(){
        String [][] instructions = { { "HLT", "LODSW", "POPF", "STC", "XLATB", "AAA"},
                {"POP", "IDIV", "NEG", "PUSH"},
                {"RCL", "SHL", "XCHG", "ADD"},
                {"JNGE", "JNP", "JP", "LOOPE", "JA", "JC"} };
        String[] types = {"sin ops", "un op", "dos ops", "salto"};

        for ( int i=0; i<instructions.length; i++ )
            for ( String reg : instructions[i] )
                if ( this.getText().equalsIgnoreCase(reg) ){
                    this.subtype = types[i];
                    return;
                }
    }

}
