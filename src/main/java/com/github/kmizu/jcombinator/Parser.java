package com.github.kmizu.jcombinator;

public interface Parser<T> {
	ParseResult<T> perform(String input);
	default Parser<T> or(Parser<T> rhs) {
		return new Or<>(this, rhs);
	}
	default <U> Parser<Pair<T, U>> cat(Parser<U> rhs) {
		return new Cat<>(this, rhs);
	}
}
