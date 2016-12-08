package com.github.kmizu.jcombinator;

import com.github.kmizu.jcombinator.core.Function1;

public class MapParser<T, R> implements Parser<R> {
    private final Parser<T>       parser;
    private final Function1<T, R> fn;

    public MapParser(Parser<T> parser, Function1<T, R> fn) {
        this.parser = parser;
        this.fn = fn;
    }
    @Override
    public ParseResult<R> invoke(String input) {
        return parser.invoke(input).map(fn);
    }
}