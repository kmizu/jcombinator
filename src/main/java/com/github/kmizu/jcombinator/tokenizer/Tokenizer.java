package com.github.kmizu.jcombinator.tokenizer;

public interface Tokenizer<T> {
    T next();
    boolean hasNext();
}
