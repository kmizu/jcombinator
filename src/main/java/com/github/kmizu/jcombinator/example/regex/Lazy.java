package com.github.kmizu.jcombinator.example.regex;

import com.github.kmizu.jcombinator.datatype.Function0;

public class Lazy<T> {
    private final Function0<T> thunk;
    private T result;

    public Lazy(Function0<T> thunk) {
        this.thunk = thunk;
    }

    public synchronized T force() {
        if(result == null) {
            result = thunk.invoke();
        }
        return result;
    }

    public synchronized boolean isForced() {
        return result != null;
    }

    public synchronized boolean isDelayed() {
        return !isForced();
    }

    public static <T> Lazy<T> make(Function0<T> thunk) {
        return new Lazy<>(thunk);
    }
}
