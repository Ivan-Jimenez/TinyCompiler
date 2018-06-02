package application.lexer;

public class KeyWord extends Token {
	public final String lexeme;
	
	public KeyWord (String lexeme, int tag) {
		super(tag);
		this.lexeme = lexeme;
	}
	
	public static final KeyWord AND = new KeyWord("&&", Tag.AND);
	public static final KeyWord OR = new KeyWord("||", Tag.OR);
	public static final KeyWord EQUAL = new KeyWord("=", Tag.EQ);
	public static final KeyWord NOT_EQUAL = new KeyWord("<>", Tag.NE);
	public static final KeyWord L_EQUAL = new KeyWord("<=", Tag.LE);
	public static final KeyWord G_EQUAL = new KeyWord(">=", Tag.GE);
	public static final KeyWord TRUE = new KeyWord("true", Tag.TRUE);
	public static final KeyWord FALSE = new KeyWord("false", Tag.FALSE);
	public static final KeyWord IF = new KeyWord("if", Tag.IF);
	public static final KeyWord ELSE = new KeyWord("else", Tag.ELSE);
	public static final KeyWord BREAK = new KeyWord("break", Tag.BREAK);
	public static final KeyWord DO = new KeyWord("do", Tag.DO);
	public static final KeyWord WHILE = new KeyWord("while", Tag.WHILE);
	public static final KeyWord MINUS = new KeyWord("minus", Tag.MINUS);
}
