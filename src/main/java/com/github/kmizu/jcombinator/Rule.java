package com.github.kmizu.jcombinator;

import com.github.kmizu.jcombinator.core.Functions.Fn0;
import com.github.kmizu.jcombinator.Parser;

public class Rule<T> implements Parser<T> {
	private Fn0<Parser<T>> body;
	public Rule() {
	}
	
	@Override
	public ParseResult<T> invoke(String input) {
		return body.invoke().invoke(input);
	}
	
	public void setBody(Fn0<Parser<T>> body ) {
		this.body = body;
	}
}
