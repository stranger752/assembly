/**
 * @author Escalera Jimenez Enrique
 * @author Sánchez Mendieta Jesús Alberto
 */

package controllers;

import models.*;

import java.util.ArrayList;

public class Phase1 {

    private final ArrayList<Element> elements = new ArrayList<>();      // lista de elementos del código fuente
    private final ArrayList<String[]> lexical = new ArrayList<>();      // lista de duplas elemento-tipo del código

    public Phase1(ArrayList<String[]> source){
        Tools tools = new Tools();

        // se llena al arreglo 'elements'
        ArrayList<String> aux = new ArrayList<>();
        for ( String[] s : source ) {
            aux.addAll(tools.splitLine(s[0]));
            if ( !s[0].equalsIgnoreCase("") ) aux.add("");
        }
        for ( String s : aux ) createElement(s);

        // se llena a la lista del análisis lexicográfico que contine los elementos y su tipo (identifiación)
        for (Element e: elements)
            this.lexical.add( new String[] { e.getText(), e.getType() } );
    }

    public void createElement(String text){
        // vacío
        if ( text.isBlank() ) {
            this.elements.add(new Element(text, true));
            elements.get(elements.size()-1).setType("");
        }
        // registro
        else if ( isRegister(text) )
            this.elements.add(new Register(text, true));
        // instrucción
        else if ( isInstruction(text) )
            this.elements.add(new Instruction(text, true));
        // instrucción no asignada
        else if ( isUnassignedInstruction(text) ) {
            this.elements.add(new Element(text, true));
            elements.get(elements.size()-1).setType("Símbolo");
        }
        // constante
        else if ( isChar(text) || isHex(text) || isBin(text) || isDec(text) )
            this.elements.add(new Constant(text, true));
        // pseudoinstrucción
        else if ( isPseudoinstruction(text) )
            this.elements.add(new Pseudoinstruction(text, true));
        else if ( isDUP(text) )
            this.elements.add(new Dup(text, true));
        // símbolo
        else if ( isSymbol(text) )
            this.elements.add(new Symbol(text, true));
        // inválido
        else {
            this.elements.add(new Element(text, false));
            elements.get(elements.size()-1).setType("Elemento inválido");
        }

    }

    private boolean isRegister(String text){
        String[] registers = { "AX", "AH", "AL", "BX", "BH", "BL", "CX", "CH", "CL", "DX", "DH", "DL",
                "CS", "DS", "ES", "SS",
                "SP", "BP", "SI", "DI", "IP" };
        for (String register : registers) {
            if (text.equalsIgnoreCase(register)) return true;
        }
        return false;
    }

    private boolean isInstruction(String text){
        String[] instructions = { "HLT", "LODSW", "POPF", "STC", "XLATB", "AAA", "POP", "IDIV", "NEG", "PUSH",
        "RCL", "SHL", "XCHG", "ADD", "JNGE", "JNP", "JP", "LOOPE", "JA", "JC" };
        for (String instruction : instructions) {
            if (text.equalsIgnoreCase(instruction)) return true;
        }
        return false;
    }

    private boolean isUnassignedInstruction(String text){
        String[] instructions = { "AAD", "AAM", "AAS", "ADC", "AND", "CALL", "CBW", "CLC", "CLD", "CLI", "CMC",
                "CMP", "CMPSB", "CMPSW", "CWD", "DAA", "DAS", "DEC", "DIV", "IMUL", "IN", "INC",
                "INT", "INTO", "IRET", "JAE", "JB", "JBE", "JCXZ", "JE", "JG", "JGE", "JL", "JLE",
                "JMP", "JNA", "JNAE", "JNB", "JNBE", "JNC", "JNE", "JNG", "JNL", "JNLE", "JNO",
                "JNS", "JNZ", "JO", "JPE", "JPO", "JS", "JZ", "LAHF", "LDS", "LEA", "LES", "LODSB",
                "LOOP", "LOOPNE", "LOOPNZ", "LOOPZ", "MOV", "MOVSB", "MOVSW", "MUL",
                "NOP", "NOT", "OR", "OUT", "POPA", "PUSHA", "PUSHF", "RCR", "REP",
                "REPE", "REPNE", "REPNZ", "REPZ", "RET", "RETF", "ROL", "ROR", "SAHF", "SAL", "SAR", "SBB",
                "SCASB", "SCASW", "SHR", "STD", "STI", "STOSB", "STOSW", "SUB", "TEST", "XOR" };
        for (String instruction : instructions) {
            if (text.equalsIgnoreCase(instruction)) return true;
        }
        return false;
    }

    private boolean isChar(String text){
        return ( (text.startsWith("\"") && text.endsWith("\"")) || (text.startsWith("'") && text.endsWith("'")) );
    }

    private boolean isHex(String text){
        String[] s = text.split("");
        boolean isHex = false;
        if ( s[0].matches("0") && s[s.length-1].equalsIgnoreCase("H")
                && ( s.length-2==4 || s.length-2==2 ) ) {
            isHex = true;
            for ( int i = 1; i < s.length-1; i++ ) {
                if ( !(s[i].matches("[0-9]+") || s[i].equalsIgnoreCase("A")
                        || s[i].equalsIgnoreCase("B") || s[i].equalsIgnoreCase("C")
                        || s[i].equalsIgnoreCase("D") || s[i].equalsIgnoreCase("E")
                        || s[i].equalsIgnoreCase("F")) ) {
                    isHex = false;
                    break;
                }
            }
        }
        return isHex;
    }

    private boolean isBin(String text){
        String[] s = text.split("");
        boolean isBin = false;
        if ( s[s.length-1].equalsIgnoreCase("B") && ( s.length-1==8 || s.length-1==16 ) ) {
            isBin = true;
            for ( int i = 0; i < s.length-1; i++ ) {
                if ( !(s[i].matches("[0-1]+") ) ) {
                    isBin = false;
                    break;
                }
            }
        }
        return isBin;
    }

    private boolean isDec(String text){
        String[] s = text.split("");
        boolean isDec = false;
        if ( s[0].matches("[0-9]+") || s[0].matches("-") ) {
            isDec = true;
            for ( int i = 0; i < s.length; i++ ) {
                if ( i == 0 )
                    if ( !( s[i].matches("[0-9]+") || s[i].matches("-") ) ) {
                        isDec = false;
                        break;
                    }
                else
                    if ( !(s[i].matches("[0-9]+") ) ) {
                        isDec = false;
                        break;
                    }
            }
        }
        return isDec && Integer.parseInt(text) >= 32768;
    }

    private boolean isPseudoinstruction(String text){
        String[] pinstructions = { ".data segment", ".code segment", ".stack segment", "ends",
                "dw", "db", "equ", "byte ptr", "word ptr", "macro", "endm", "proc", "endp"};
        for (String pinstruction : pinstructions) {
            if (text.equalsIgnoreCase(pinstruction)) return true;
        }
        return false;
    }

    private boolean isDUP(String text){
        Tools tools = new Tools();
        if ( (text.startsWith("DUP(") || text.startsWith("dup(")) && text.endsWith(")") ) {
            String arg = text.substring(text.indexOf("(")+1, text.length()-1);
            arg = tools.removeStartingSpaces(arg);
            arg = tools.removeEndingSpaces(arg);
            if ( arg.equalsIgnoreCase("\"?\"") || arg.equalsIgnoreCase("'?'") )
                return false;
            else return isChar(arg) || isHex(arg) || isBin(arg) || isDec(arg);
        }
        else return false;
    }

    private boolean isSymbol(String text){
        return ( text.length() <= 10 && Character.isLetter(text.charAt(0)) );
    }

    /**
     * @return 'ArrayList' de 'String[]': lista de arreglos que contiene el análisis lexicográfico
     */
    public ArrayList<String[]> getLexical() { return lexical; }

}
