package models;

public class Register extends Element {

    private String operandType;
    private int numBytes;

    public Register(String text){
        super(text);
        setType();
        setNumBytes();
        setOperandType();
    }

    /**
     * Asigna el tipo de registro entre: registros de propósito general, de segmento, de índice o de bandera.
     */
    private void setType(){
        String[][] registers = { {"AX", "AH", "AL", "BX", "BH", "BL", "CX", "CH", "CL", "DX", "DH", "DL"},
                {"CS", "DS", "ES", "SS"},
                {"SP", "BP", "SI", "DI", "IP"} };
        String[] types = { "propósito general", "segmento", "propósito especial"};

        for ( int i=0; i<registers.length; i++ )
            for ( String reg : registers[i] )
                if ( this.getText().equalsIgnoreCase(reg) ){
                    setType(types[i]);
                    return;
                }
    }

    /**
     * Asigna el numero de bits del registro
     */
    private void setNumBytes(){
        if ( this.getText().endsWith("H") || this.getText().endsWith("L") )
            this.numBytes = 8;
        else
            this.numBytes = 16;
    }

    /**
     * Asiga el tipo de operando al registro, entre: REG y SREG.
     */
    private void setOperandType(){
        String[][] registers = { { "AX","AH","AL","BX","BH","BL","CX","CH","CL","DX","DH","DL","SI","DI","BP","SP"},
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
     * @return String: tipo de operando, REG o SREG.
     */
    public String getOperandType() {
        return operandType;
    }

    /**
     * @return Int: número de bytes que ocupa el registro, 8 o 16.
     */
    public int getNumBytes() {
        return numBytes;
    }

}
