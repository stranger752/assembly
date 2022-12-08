/**
 * @author Escalera Jimenez Enrique
 * @author Sánchez Mendieta Jesús Alberto
 */

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

        // se prepara a la cadena contenida entre los parentesis
        Splitter splitter = new Splitter();
        String arg = text.substring(text.indexOf("(")+1, text.length()-1);
        arg = splitter.removeStartingSpaces(arg);
        arg = splitter.removeEndingSpaces(arg);

        // si la cadena sea una constante asigna tipo, subtipo y argumento
        Sorter sorter = new Sorter();
        if ( sorter.isChar(arg) || sorter.isHex(arg) || sorter.isBin(arg) || sorter.isDec(arg) ) {
            this.argument = new Constant(arg, true);
            setType("Pseudoinstrucción");
            setSubtype();
        }
        // si la cadena no es una constante asigna al tipo como "Elemento inválido"
        else {
            setType("Elemento inválido");
            setValid(false);
        }
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
