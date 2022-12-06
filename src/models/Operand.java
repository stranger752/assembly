package models;

public interface Operand {

    /**
     * Asigna el tipo de operando, entre REG, SREG, memory o immediate.
     */
    void setOperandType();

    /**
     * Asiga el número de bytes que usa el operando.
     */
    void setNumberOfBits();

    /**
     * Asiga el tamaño al operando, entre word o byte.
     */
    void setSize();

    /**
     * @return String: tipo de operando (REG, SREG, memory o immediate).
     */
    String getOperandType();

    /**
     * @return int: numero de bits que usa el operando.
     */
    int getNumberOfBits();

    /**
     * @return String: tipo de dato según tamaño (byte o word).
     */
    String getSize();

}
