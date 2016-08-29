package com.github.kmizu.jcombinator;

public interface Visitor<T> {
	T visitOr(Or or);
}
