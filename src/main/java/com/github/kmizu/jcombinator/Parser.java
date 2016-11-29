package com.github.kmizu.jcombinator;

import java.util.List;

import static com.github.kmizu.jcombinator.core.Functions.*;

public interface Parser<T> {
	ParseResult<T> invoke(String input);
	default Parser<T> or(Parser<T> rhs) {
		return new Or<>(this, rhs);
	}
	default <U> Parser<Tp2<T, U>> cat(Parser<U> rhs) {
		return new Cat<>(this, rhs);
	}
	default <U> Parser<U> map(Fn1<T, U> fn) {
	    return new MapParser<>(this, fn);
	}
	default <U> Parser<U> flatMap(Fn1<T, Parser<U>> fn) {
		return new FlatMapParser<>(this, fn);
	}
	default Parser<List<T>> many() {
		return new ManyParser<T>(this);
	}
	default Parser<List<T>> many1() {
		return new Many1Parser<T>(this);
	}
	static Parser<String> string(String literal) {
	    return new StringParser(literal);
	}
}
