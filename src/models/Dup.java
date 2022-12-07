package models;

public class Dup extends Element {

    private Constant argument;

    /**
     * Constructor: crea elemento dup. Asigna el texto, valor de validez (TRUE o FALSE) y tipo.
     * @param text   String:  cadena de texto del elemento.
     * @param valid  boolean: validez del elemento.
     */
    public Dup(String text, boolean valid){
        super(text, valid);
        setType("Pseudoinstrucción");
        setArgument( text );
        setSubtype();
    }

    /**
     * Crea y asigna el argumento constante.
     * @param text String: cadena de texto del elemento.
     */
    private void setArgument(String text){
        String arg = text.substring(this.getText().indexOf("(")+1, this.getText().length()-1);
        this.argument = new Constant(arg, true);
    }

    /**
     * Asigna el tipo de DUP, entre DUP_char, DUP_num_word o DUP_num_byte.
     */
    private void setSubtype(){
        if ( argument.getText().equalsIgnoreCase("\"?\"")
                || argument.getText().equalsIgnoreCase("'?'") ) this.setSubtype("DUP undefined");
        else if ( argument.getSubtype().equalsIgnoreCase("char") ) this.setSubtype("DUP char");
        else {
            if ( argument.getSize().equalsIgnoreCase("word") ) this.setSubtype("DUP num word");
            else if ( argument.getSize().equalsIgnoreCase("byte") ) this.setSubtype("DUP num byte");
            else this.setSubtype("DUP num");
        }
    }

}
