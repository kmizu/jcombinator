package com.github.kmizu.jcombinator;

public interface Parser<T> {
	ParseResult<T> perform(String input);
}
