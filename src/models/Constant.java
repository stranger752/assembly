/**
 * @author Escalera Jimenez Enrique
 * @author Sánchez Mendieta Jesús Alberto
 */

package models;

public class Constant extends Element implements Operand {

    private String operandType;       // tipo de operando
    private int numBytes;             // número de bytes que usa
    private String size;              // tipo de registro según su tamaño (word o byte)
    private boolean signed;           // tiene signo negativo

    /**
     * Constructor: crea elemento constante. Asigna el texto, valor de validez (TRUE o FALSE) y tipo.
     * Además, asigna subtipo, tipo de operando, número de bits que ocupa y clasificación según su tamaño.
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Constant(String text, boolean valid){
        super(text, valid);
        setTypes();
        setOperandType();
        setNumberOfBytes();
        setSize();
        setSigned();
    }

    /**
     * Asigna el tipo y subtipo de la constante ("carácter", "hex", "bin" o "dec").
     */
    private void setTypes(){
        switch ( this.getText().charAt(this.getText().length()-1) ) {
            case '\"', '\'' -> {
                this.setSubtype("char");
                this.setType("Constante carácter");
            }
            case 'H','h' -> {
                this.setSubtype("hex");
                this.setType("Constante numérica hexadecimal");
            }
            case 'B','b' -> {
                this.setSubtype("bin");
                this.setType("Constante numérica binaria");
            }
            default -> {
                this.setSubtype("dec");
                this.setType("Constante numérica decimal");
            }
        }
    }

    /**
     * Asigna el tipo de operando, entre REG, SREG, memory o immediate.
     */
    @Override
    public void setOperandType() {
        this.operandType = "immediate";
    }

    /**
     * Asiga el número de bytes que usa el operando.
     */
    @Override
    public void setNumberOfBytes() {
        switch (this.getSubtype()) {
            case "char" -> this.numBytes = getText().length();
            case "hex"  -> {
                if (this.getText().length() == 4) this.numBytes = 1;
                else this.numBytes = 2;
            }
            case "bin"  -> {
                if (this.getText().length() == 9) this.numBytes = 1;
                else this.numBytes = 2;
            }
            default     -> {
                if ( Integer.parseInt(this.getText())>=128 || Integer.parseInt(this.getText())<-128 )
                    this.numBytes = 2;
                else this.numBytes = 1;
            }
        }
    }

    /**
     * Asiga el tamaño al operando, entre word o byte.
     */
    @Override
    public void setSize() {
        switch (this.getSubtype()) {
            case "char" -> {
                if ( this.getText().length()<128 ) this.size = "byte";
                else this.size = "word";
            }
            case "hex"  -> {
                if ( this.getText().length()==4 ) this.size = "byte";
                else this.size = "word";
            }
            case "bin"  -> {
                if ( this.getText().length()==9 ) this.size = "byte";
                else this.size = "word";
            }
            default     -> {
                if ( Integer.parseInt(this.getText())>=128 || Integer.parseInt(this.getText())<-128 )
                    this.size = "word";
                else this.size = "byte";
            }
        }
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
    public int getNumberOfBytes() {
        return this.numBytes;
    }

    /**
     * @return String: tipo de dato según tamaño (byte o word).
     */
    @Override
    public String getSize() {
        return this.size;
    }

    /**
     * Asigna TRUE a signed si la constante es numérica negativa.
     */
    private void setSigned(){
        switch (this.getSubtype()) {
            case "char" -> this.signed = false;
            case "hex"  -> this.signed = getText().startsWith("08") || getText().startsWith("09")
                    || getText().startsWith("0A") || getText().startsWith("0B") || getText().startsWith("0C")
                    || getText().startsWith("0D") || getText().startsWith("0E") || getText().startsWith("0F");
            case "bin"  -> this.signed = getText().startsWith("1");
            default     -> this.signed = Integer.parseInt(getText()) < 0;
        }
    }

}
