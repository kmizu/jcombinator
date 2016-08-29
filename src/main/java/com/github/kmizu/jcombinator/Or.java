package com.github.kmizu.jcombinator;

public class Or<X> implements Parser<X> {
	private Parser<X> lhs;
	private Parser<X> rhs;
	public Or(Parser<X> lhs, Parser<X> rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public Parser<X> getLhs() {
		return lhs;
	}
	
	public Parser<X> getRhs() {
		return rhs;
	}
	
	@Override
	public ParseResult<X> perform(String input) {
		ParseResult<X> lresult = lhs.perform(input);
		return lresult.fold(
		  succ -> succ,
		  fail -> rhs.perform(input)
		);
	}
}