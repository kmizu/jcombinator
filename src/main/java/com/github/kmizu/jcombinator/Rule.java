package com.github.kmizu.jcombinator;

import com.github.kmizu.jcombinator.core.Functions.Fn0;
import com.github.kmizu.jcombinator.Parser;

public class Rule<T> implements Parser<T> {
	private final Fn0<Parser<T>> body;
	public Rule(Fn0<Parser<T>> body) {
		this.body = body;
	}
	
	@Override
	public ParseResult<T> invoke(String input) {
		return body.invoke().invoke(input);
	}
	
}
