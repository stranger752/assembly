package models;

public class Pseudoinstruction extends Element {

    /**
     * Constructor: crea elemento pseudoinstrucción. Asigna el texto, valor de validez (TRUE o FALSE) y tipo.
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Pseudoinstruction(String text, boolean valid){
        super(text, valid);
        setType("Pseudoinstrucción");
        setSubtype();
    }

    /**
     * Asigna el tipo de pseudoinstrucción
     */
    private void setSubtype(){
        String[][] pseudoinstructions = {
                {".data segment", ".code segment", ".stack segment", "ends"},   // segmento
                {"dw", "db", "equ"},                                            // tipo de dato
                {"byte ptr", "word ptr"},                                       // memory access
                {"macro", "endm"},                                              // macro
                {"proc", "endp"} };                                             // procedimiento
        String[] types = {"segment", "data", "access", "macro", "procedure"};

        for ( int i=0; i<pseudoinstructions.length; i++ )
            for ( String reg : pseudoinstructions[i] )
                if ( this.getText().equalsIgnoreCase(reg) ){
                    this.setSubtype( types[i] );
                    return;
                }
    }

}
