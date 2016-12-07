package com.github.kmizu.jcombinator;

import com.github.kmizu.jcombinator.tokenizer.Tokenizer;
import com.github.kmizu.jcombinator.tokenizer.TokenizerSpecification;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TokenizerTest {
    enum TokenType {
        IDENTIFIER,
        NUMBER,
        PLUS,
        MINUS,
        ASTER,
        DIV,
        LPAREN,
        RPAREN
    }
    class Token {
        private final String image;
        private final TokenType type;
        public Token(String image, TokenType type) {
            this.image = image;
            this.type = type;
        }

        public String image() {
            return image;
        }

        public TokenType type() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Token token = (Token) o;

            if (!image.equals(token.image)) return false;
            return type == token.type;

        }

        @Override
        public int hashCode() {
            int result = image.hashCode();
            result = 31 * result + type.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Token{" + "image='" + image + '\'' + ", type=" + type + '}';
        }
    }

    @Test
    public void testTokenizer () {
        TokenizerSpecification<Token> spec = new TokenizerSpecification<>();
        spec.register("[a-zA-Z_][a-zA-Z0-9_]*", image -> new Token(image, TokenType.IDENTIFIER));
        spec.register("[0-9]+", image -> new Token(image, TokenType.NUMBER));
        spec.register("\\+", image -> new Token("+", TokenType.PLUS));
        spec.register("-", image -> new Token("-", TokenType.MINUS));
        spec.register("\\*", image -> new Token("*", TokenType.ASTER));
        spec.register("/", image -> new Token("/", TokenType.DIV));
        spec.register("\\(", image -> new Token("(", TokenType.LPAREN));
        spec.register("\\)", image -> new Token(")", TokenType.RPAREN));
        Tokenizer<Token> tokenizer = spec.tokenizer("100 + 200");
        tokenizer.moveNext();
        assertEquals(new Token("100", TokenType.NUMBER), tokenizer.current());
        tokenizer.moveNext();
        assertEquals(new Token("+", TokenType.PLUS), tokenizer.current());
        tokenizer.moveNext();
        assertEquals(new Token("200", TokenType.NUMBER), tokenizer.current());

        tokenizer = spec.tokenizer(" (100 + 200 )");
        tokenizer.moveNext();
        assertEquals(new Token("(", TokenType.LPAREN), tokenizer.current());
        tokenizer.moveNext();
        assertEquals(new Token("100", TokenType.NUMBER), tokenizer.current());
        tokenizer.moveNext();
        assertEquals(new Token("+", TokenType.PLUS), tokenizer.current());
        tokenizer.moveNext();
        assertEquals(new Token("200", TokenType.NUMBER), tokenizer.current());
        tokenizer.moveNext();
        assertEquals(new Token(")", TokenType.RPAREN), tokenizer.current());
    }
}
