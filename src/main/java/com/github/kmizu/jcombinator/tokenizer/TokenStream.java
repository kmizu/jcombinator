package com.github.kmizu.jcombinator.tokenizer;

public interface TokenStream<T> {
    T head();
    TokenStream<T> tail();
    public boolean isTerminal();
}
