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
	default Parser<T> chain(Parser<Fn2<T, T, T>> q) {
		return this.cat(q.cat(this).many()).map(t -> {
			T init = t.fst();
			List<Tp2<Fn2<T, T, T>, T>> list = t.snd();
			return foldLeft(list, init, ts -> {
				T a = ts.fst();
				return let(ts.snd().fst(), f -> {
					return let(ts.snd().snd(), b -> {
						return f.invoke(a, b);
					});
				});
			});
		});
	}
	static Parser<String> string(String literal) {
	    return new StringParser(literal);
	}
	static <T> Rule<T> rule(Fn0<Parser<T>> body) {
		return new Rule<>(body);
	}
	static Parser<String> eof() {
		return new EOFParser();
	}
	static RangeParser range(char from, char to) {
		return new RangeParser(from, to);
	}
	static Parser<String> alphabet() {
		return range('a', 'z').or(range('A', 'Z'));
	}
	static Parser<String> digit() {
		return range('0', '9');
	}
}
