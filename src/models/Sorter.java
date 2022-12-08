/**
 * @author Escalera Jimenez Enrique
 * @author Sánchez Mendieta Jesús Alberto
 */

package models;

public class Sorter {

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es un registro del procesador 8086.
     */
    public boolean isRegister(String text){
        String[] regs = { "AX", "AH", "AL", "BX", "BH", "BL", "CX", "CH", "CL", "DX", "DH", "DL",
                "CS", "DS", "ES", "SS", "SP", "BP", "SI", "DI", "IP" };
        for (String r : regs)
            if (text.equalsIgnoreCase(r)) return true;
        return false;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una instrucción asignada al equipo 6.
     */
    public boolean isInstruction(String text){
        String[] insts = { "HLT", "LODSW", "POPF", "STC", "XLATB", "AAA", "POP", "IDIV", "NEG", "PUSH",
                "RCL", "SHL", "XCHG", "ADD", "JNGE", "JNP", "JP", "LOOPE", "JA", "JC" };
        for (String i : insts)
            if (text.equalsIgnoreCase(i)) return true;
        return false;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una instrucción no asignada al equipo 6.
     */
    public boolean isUnassignedInstruction(String text){
        String[] insts = { "AAD", "AAM", "AAS", "ADC", "AND", "CALL", "CBW", "CLC", "CLD", "CLI", "CMC",
                "CMP", "CMPSB", "CMPSW", "CWD", "DAA", "DAS", "DEC", "DIV", "IMUL", "IN", "INC",
                "INT", "INTO", "IRET", "JAE", "JB", "JBE", "JCXZ", "JE", "JG", "JGE", "JL", "JLE",
                "JMP", "JNA", "JNAE", "JNB", "JNBE", "JNC", "JNE", "JNG", "JNL", "JNLE", "JNO",
                "JNS", "JNZ", "JO", "JPE", "JPO", "JS", "JZ", "LAHF", "LDS", "LEA", "LES", "LODSB",
                "LOOP", "LOOPNE", "LOOPNZ", "LOOPZ", "MOV", "MOVSB", "MOVSW", "MUL",
                "NOP", "NOT", "OR", "OUT", "POPA", "PUSHA", "PUSHF", "RCR", "REP",
                "REPE", "REPNE", "REPNZ", "REPZ", "RET", "RETF", "ROL", "ROR", "SAHF", "SAL", "SAR", "SBB",
                "SCASB", "SCASW", "SHR", "STD", "STI", "STOSB", "STOSW", "SUB", "TEST", "XOR" };
        for (String i : insts)
            if (text.equalsIgnoreCase(i)) return true;
        return false;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una constante carácter.
     */
    public boolean isChar(String text){
        return (text.startsWith("\"") && text.endsWith("\"")) || (text.startsWith("'") && text.endsWith("'"));
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una constante hexadecimal.
     */
    public boolean isHex(String text){
        boolean isHex = false;
        String[] s = text.split("");
        if ( s[0].matches("0")
                && s[s.length-1].equalsIgnoreCase("H")
                && ( s.length-2==4 || s.length-2==2 ) ) {
            isHex = true;
            for ( int i=1; i<s.length-1; i++ )
                if ( !(s[i].matches("[0-9]+")
                        || s[i].equalsIgnoreCase("A")
                        || s[i].equalsIgnoreCase("B")
                        || s[i].equalsIgnoreCase("C")
                        || s[i].equalsIgnoreCase("D")
                        || s[i].equalsIgnoreCase("E")
                        || s[i].equalsIgnoreCase("F")) ) {
                    isHex = false;
                    break;
                }
        }
        return isHex;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una constante binaria.
     */
    public boolean isBin(String text){
        boolean isBin = false;
        String[] s = text.split("");
        if ( s[s.length-1].equalsIgnoreCase("B") && ( s.length-1==8 || s.length-1==16 ) ) {
            isBin = true;
            for ( int i=0; i<s.length-1; i++ )
                if ( !(s[i].matches("[0-1]+")) ) {
                    isBin = false;
                    break;
                }
        }
        return isBin;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una constante decimal.
     */
    public boolean isDec(String text){
        boolean isDec = false;
        String[] s = text.split("");
        if (s[0].matches("[0-9]+") || s[0].matches("-")) {
            isDec = true;
            for ( int i=1; i<s.length; i++ )
                if ( !(s[i].matches("[0-9]+")) ){
                    isDec = false;
                    break;
                }
        }
        return isDec && Integer.parseInt(text)<32768;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una pseudoinstrucción.
     */
    public boolean isPseudoinstruction(String text){
        String[] pseudos = { ".data segment", ".code segment", ".stack segment", "ends", "dw", "db", "equ",
                "byte ptr", "word ptr", "macro", "endm", "proc", "endp"};
        for (String p : pseudos)
            if (text.equalsIgnoreCase(p)) return true;
        return false;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es una pseudoinstrucción DUP(_).
     */
    public boolean isDUP(String text){
        String[] s = text.split("");
        return s[0].equalsIgnoreCase("D")
                && s[1].equalsIgnoreCase("U")
                && s[2].equalsIgnoreCase("P")
                && s[3].equalsIgnoreCase("(")
                && s[s.length-1].equalsIgnoreCase(")");
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es un símbolo.
     */
    public boolean isSymbol(String text){
        if (text.length()<=10 && Character.isLetter(text.charAt(0))) {
            for ( int i=1; i<text.length(); i++ ){
                if ( !Character.isLetterOrDigit(text.charAt(i)) ) return false;
            }
            return true;
        }
        else return false;
    }

    /**
     * @param  text String: elemento a revisar.
     * @return TRUE si el elemento es un acceso a memoria.
     */
    public boolean isMemoryAccess(String text){
        return text.startsWith("[") && text.endsWith("]");
    }

}
