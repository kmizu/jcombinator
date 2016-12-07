package com.github.kmizu.jcombinator.tokenizer;

public interface Tokenizer<T> {
    T current();
    boolean moveNext();
    String nextInput();
}
