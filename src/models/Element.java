package models;

public class Element {
    private String text;            // almacena cadena con el texto del elemento
    private boolean valid;          // almacena TRUE si el elemento es válido, o FALSE en caso contrario
    private String type;            // almacena tipo de pseudoinst ( o de inst o de simbolo o de cte o de reg)

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
     * @return String: cadena de texto del elemento.
     */
    public String getText() {
        return text;
    }

    /**
     * @return boolean: TRUE si el elemento es válido.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @return String: tipo del elemento.
     */
    public String getType() {
        return type;
    }

}
