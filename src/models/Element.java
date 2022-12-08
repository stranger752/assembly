/**
 * @author Escalera Jimenez Enrique
 * @author Sánchez Mendieta Jesús Alberto
 */

package models;

public class Element {
    private final String text;          // cadena con el texto del elemento
    private final boolean valid;        // TRUE si el elemento es válido, o FALSE en caso contrario
    private String type;                // tipo de elemento
    private String subtype;             // subtipo del elemento

    /**
     * Constructor: crea elemento. Asigna el texto y valor de validez (TRUE o FALSE).
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Element(String text, boolean valid){
        this.text = text;
        this.valid = valid;
    }

    /**
     * Asigna el tipo de elemento.
     * @param type String: tipo del elemento.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Asigna el subtipo de elemento.
     * @param subtype String: subtipo del elemento.
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * @return String: cadena de texto del elemento.
     */
    public String getText() {
        return this.text;
    }

    /**
     * @return boolean: TRUE si el elemento es válido. FALSE en caso contrario.
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * @return String: tipo del elemento.
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return String: subtipo del elemento.
     */
    public String getSubtype() {
        return this.subtype;
    }

}
