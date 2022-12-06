package models;

public class Register extends Element implements Operand {

    private String operandType;     // tipo de operando (REG o SREG)
    private int numBits;            // número de bytes que usa (8 o 16 bits)
    private String size;            // tipo de registro según su tamaño (word o byte)

    /**
     * Constructor: crea elemento registro. Asigna el texto, valor de validez (TRUE o FALSE) y tipo.
     * Además, asigna subtipo, tipo de operando, número de bits que ocupa y clasificación según su tamaño.
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Register(String text, boolean valid){
        super(text, valid);
        setType("Registro");
        setSubtype();
        setOperandType();
        setNumberOfBits();
        setSize();
    }

    /**
     * Asigna el tipo de registro (entre registros de propósito general, de segmento o de propósito especial).
     */
    private void setSubtype() {
        String[][] registers = { {"AX", "AH", "AL", "BX", "BH", "BL", "CX", "CH", "CL", "DX", "DH", "DL"},
                {"CS", "DS", "ES", "SS"},
                {"SP", "BP", "SI", "DI", "IP"} };
        String[] types = { "propósito general", "segmento", "propósito especial"};

        for ( int i=0; i<registers.length; i++ )
            for ( String reg : registers[i] )
                if ( this.getText().equalsIgnoreCase(reg) ){
                    this.setSubtype(types[i]);
                    return;
                }
    }

    /**
     * Asigna el tipo de operando, entre REG, SREG, memory o immediate.
     */
    @Override
    public void setOperandType() {
        String[][] registers = { {"AX","AH","AL","BX","BH","BL","CX","CH","CL","DX","DH","DL","SI","DI","BP","SP"},
                { "DS", "ES", "SS", "CS" } };
        String[] types = { "REG", "SREG" };

        for ( int i=0; i<registers.length; i++ )
            for ( String reg : registers[i] )
                if ( this.getText().equalsIgnoreCase(reg) ) {
                    operandType = types[i];
                    break;
                }
    }

    /**
     * Asiga el número de bytes que usa el operando.
     */
    @Override
    public void setNumberOfBits() {
        if ( this.getText().endsWith("H") || this.getText().endsWith("L") )
            this.numBits = 8;
        else
            this.numBits = 16;
    }

    /**
     * Asiga el tamaño al operando, entre word o byte.
     */
    @Override
    public void setSize() {
        if ( this.getText().endsWith("H") || this.getText().endsWith("L") )
            this.size = "byte";
        else
            this.size = "word";
    }

    /**
     * @return String: tipo de operando (REG, SREG, memory o immediate).
     */
    @Override
    public String getOperandType() {
        return this.operandType;
    }

    /**
     * @return int: numero de bits que usa el operando.
     */
    @Override
    public int getNumberOfBits() {
        return this.numBits;
    }

    /**
     * @return String: tipo de dato según tamaño (byte o word).
     */
    @Override
    public String getSize() {
        return this.size;
    }
}
