package com.github.kmizu.jcombinator;

import static com.github.kmizu.jcombinator.core.Functions.*;

public interface Parser<T> {
	ParseResult<T> invoke(String input);
	default Parser<T> or(Parser<T> rhs) {
		return new Or<>(this, rhs);
	}
	default <U> Parser<Pair<T, U>> cat(Parser<U> rhs) {
		return new Cat<>(this, rhs);
	}
	default <U> Parser<U> map(Fn1<T, U> fn) {
	    return new MapParser<>(this, fn);
	}
	default <U> Parser<U> flatMap(Fn1<T, Parser<U>> fn) {
		return new FlatMapParser<>(this, fn);
	}
}
