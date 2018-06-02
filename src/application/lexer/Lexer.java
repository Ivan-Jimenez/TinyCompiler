package application.lexer;

import java.io.IOException;
import java.util.Hashtable;

import symbol.Type;

public class Lexer {
	private Hashtable<String, KeyWord> words = new Hashtable<>();
	
	private char peek = ' ';
	
	public static int line = 1;
	
	private void reserve (KeyWord kword) {
		words.put(kword.lexeme, kword);
	}
	
	private void reserveKeywords () {
		reserve(KeyWord.FALSE);
		reserve(KeyWord.TRUE);
		
		reserve(KeyWord.DO);
		reserve(KeyWord.WHILE);
		reserve(KeyWord.IF);
		reserve(KeyWord.ELSE);
		reserve(KeyWord.BREAK);
		
		reserve(Type.BOOLEAN);
		reserve(Type.CHAR);
		reserve(Type.FLOAT);
		reserve(Type.INT);
	}
	
	public Lexer () {
		reserveKeywords();
	}
	
	private void read () throws IOException {
		peek = (char) System.in.read();
	}
	
	private boolean read (char c) throws IOException {
		read();
		if (peek != c) {
			return false;
		}
		peek = ' ';
		return true;
	}
	
	public Token scan () throws IOException {
		for (;; read()) {
			if (peek == ' ' || peek == '\t') {
				
			} else if(peek == '\n') {
				line = line + 1;			
			} else {
				break;
			}
		}
		
		switch (peek) {
		case '&':
			if (read('&')) {
				return KeyWord.AND;
			} else {
				return new Token('&');
			}
		case '|':
			if (read('|')) {
				return KeyWord.OR;
			} else {
				return new Token('|');
			}
		case '>':
			if (read('=')) {
				return KeyWord.G_EQUAL;
			} else {
				return new Token('>');
			}
		case '<':
			if (read('=')) {
				return KeyWord.L_EQUAL;
			} else {
				return new Token('<');
			}
		case '=':
			if (read('=')) {
				return KeyWord.EQUAL;
			} else {
				return new Token('=');
			}
		case '!':
			if (read('=')) {
				return KeyWord.NOT_EQUAL;
			} else {
				return new Token('!');
			}
		}
		
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				read();
			} while (Character.isDigit(peek));
			
			if (peek != '.') {
				return new Num(v);
			}
			
			float x = v, d = 10;
			for (;;) {
				read();
				if (!Character.isDigit(peek)) {
					break;
				}
				x = x + Character.digit(peek, 10) / d;
				d = d * 10;
			}
			return new Real(x);
		}
		
		if (Character.isLetter((int) peek)) {
			StringBuilder b = new StringBuilder();
			do {
				b.append(peek);
				read();
			} while (Character.isLetter((int) peek));
			String s = b.toString();
			KeyWord w = (KeyWord) words.get(s);
			if (w != null) {
				return w;
			}
			w = new KeyWord(s, Tag.ID);
			words.put(s, w);
			return new KeyWord(s, Tag.ID);
		}
		
		Token t = new Token(peek);
		peek = ' ';
		return t;
	}
}
