/**
 * @author Escalera Jimenez Enrique
 * @author Sánchez Mendieta Jesús Alberto
 */

package models;

public class Splitter {

    /**
     * Remueve espacios (" ") o tabs ("\t") al final de la cadena de texto dada como parámetro.
     * @param line String: texto.
     * @return String: texto sin espacios (" ") o tabs ("\t") al final.
     */
    public String removeEndingSpaces(String line){
        if ( !(line.endsWith(" ") || line.endsWith("\t")) )
            return line;
        else return removeEndingSpaces(line.substring(0,line.length()-1));
    }

    /**
     * Remueve espacios (" ") o tabs ("\t") al inicio de la cadena de texto dada como parámetro.
     * @param line String: texto.
     * @return String: texto sin espacios (" ") o tabs ("\t") al inicio de la cadena.
     */
    public String removeStartingSpaces(String line){
        if ( !(line.startsWith(" ") || line.startsWith("\t")) )
            return line;
        else return removeStartingSpaces(line.substring(1));
    }

}
