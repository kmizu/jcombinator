package com.github.kmizu.jcombinator.tokenizer;

public class StringStream implements TokenStream<String> {
    private String input;
    public StringStream(String input) {
        this.input = input;
    }

    @Override
    public String head() {
        return input.substring(0, 1);
    }

    @Override
    public TokenStream<String> tail() {
        if(input.length() > 0) {
            return new StringStream(input.substring(1));
        } else {
            return new StringStream("");
        }
    }

    @Override
    public boolean isTerminal() {
        return input.length() == 0;
    }
}
