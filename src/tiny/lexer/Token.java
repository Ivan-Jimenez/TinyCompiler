/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiny.lexer;

/**
 *
 * @author Ivan
 */
public class Token {
    private String lexeme;
    private String symbol;
    
    public Token (String lexeme, String symbol) {
        this.lexeme = lexeme;
        this.symbol = symbol;
    }
    
    public String get_lexeme () { return lexeme; }
    public String get_symbol () { return symbol; }
}
