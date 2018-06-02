/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiny.lexer;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class Lexer {
    private char[] code;
    private int index;
    private String symbol;
    private char c;
    private String type;
    private boolean error;
    private ArrayList<Token> tokens;
    
    public Lexer (String code) {
        this.tokens = new ArrayList<>();
        clean();
        this.code = code.toCharArray();
        getSymbol();
    }
    
    private void getSymbol () {
        while (index < code.length && !error) {
            if (' ' != code[index]) q1();
            index++;
        }
    }
    
    private void q1 () {
        c = code[index];
        if (Character.isDigit(c)) {
            type = "num";
            symbol += c;
            index++;
            q2();
        } if (Character.isLetter(c)) {
            type = "id";
            symbol += c;
            index++;
            q2();
        } else if (c != '\n' && c != ' ' && c != '\t' && c != '\b' && c != '\r') q3(); 
    }
    
    private void q2 () {
        if (index < code.length) c = code[index];
        else returnSymbol();
        
        if (Character.isDigit(c)) {
            symbol += c;
            index++;
            q2();
        } /*else if (c == '.' && !type.equals("id")) {
            symbol += c;
            index++;
            q2();
        }*/ else if (Character.isLetter(c) && type.equals("id")) {
            symbol += c;
            index++;
            q2();
        } else returnSymbol();
    }
    
    @SuppressWarnings("empty-statement")
    private void q3 () {
        if (c == ':' && code[index+1] == '=') {
            type = "asig";
            symbol = ":=";
            index++;
        } else if (c == '<' || c == '=') {
            type = "com";
            symbol += c;
        } else if (c == '+' || c == '-') {
            type = "add";
            symbol += c;
        } else if (c == ';') {
            type = "endl";
            symbol += c;
        }else if (c == '/' || c == '*') {
            type = "mul";
            symbol += c;
        } else if (c == '{') {
            q4();
        } else {
            error = true;
            JOptionPane.showMessageDialog(null, "Símbolo inválido: '" + code[index] + "'", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        index++;
        returnSymbol();
    }
    
    private void q4 () {
        index++;
        if (index < code.length) c = code[index];
        else return;
        
        if (c != '}') q4();
        else {
            index++;
            getSymbol();
        }
    }
    
    private void returnSymbol () {
        if (isReservedWord(symbol)) type = "res";
        // TODO: Fix this
        if (!" ".equals(symbol) && !"".equals(symbol))
            tokens.add(new Token(type, symbol));
            
        symbol = "";
        type = "";
        c = ' ';
        getSymbol();
    }
    
    private boolean isReservedWord (String word) {
        if (word.equalsIgnoreCase("if")) return true;
        else if (word.equalsIgnoreCase("then")) return true;
        else if (word.equalsIgnoreCase("end")) return true;
        else if (word.equalsIgnoreCase("repeat")) return true;
        else if (word.equalsIgnoreCase("until")) return true;
        else if (word.equalsIgnoreCase("end")) return true;
        else if (word.equalsIgnoreCase("read")) return true;
        else if (word.equalsIgnoreCase("write")) return true;
        else if (word.equalsIgnoreCase("else")) return true;
        
        return false;
    }
    
    private void clean () {
        tokens.clear();
        index = 0;
        type = "";
        symbol = "";
        error = false;
    }
    
    public ArrayList<Token> getTokens () {
        if (error) tokens.clear();
        return tokens;
    }
}
