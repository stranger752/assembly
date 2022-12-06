package models;

public class Instruction extends Element {

    /**
     * Constructor: crea elemento instrucción. Asigna el texto y valor de validez (TRUE o FALSE).
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Instruction(String text, boolean valid){
        super(text, valid);
        setType();
    }

    /**
     * Asigna el tipo de instruccioón según el número de operandos ("sin ops", "un op", "dos ops" o "salto").
     */
    private void setType(){
        String [][] instructions = { { "HLT", "LODSW", "POPF", "STC", "XLATB", "AAA"},
                {"POP", "IDIV", "NEG", "PUSH"},
                {"RCL", "SHL", "XCHG", "ADD"},
                {"JNGE", "JNP", "JP", "LOOPE", "JA", "JC"} };
        String[] types = {"sin ops", "un op", "dos ops", "salto"};

        for ( int i=0; i<instructions.length; i++ )
            for ( String reg : instructions[i] )
                if ( this.getText().equalsIgnoreCase(reg) ){
                    this.setType( types[i] );
                    return;
                }
    }

}
