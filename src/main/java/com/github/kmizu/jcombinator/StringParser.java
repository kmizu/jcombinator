package com.github.kmizu.jcombinator;

public class StringParser implements Parser<String> {
    private String literal;
    public StringParser(String literal) {
        this.literal = literal;
    }
    @Override
    public ParseResult<String> invoke(String input) {
        if(input.startsWith(literal)) {
            return new ParseResult.Success<>(literal, input.substring(literal.length()));
        }else {
            return new ParseResult.Failure<>("expect: " + literal, input);
        }
    }
}
