package com.github.kmizu.jcombinator;

public class Cat<X, Y> implements Parser<Pair<X, Y>> {
	private Parser<X> lhs;
	private Parser<Y> rhs;
	public Cat(Parser<X> lhs, Parser<Y> rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public Parser<X> getLhs() {
		return lhs;
	}
	
	public Parser<Y> getRhs() {
		return rhs;
	}
	
	@Override
	public ParseResult<Pair<X, Y>> perform(String input) {
		ParseResult<X> lresult = lhs.perform(input);
		return lresult.fold(
		  (succ1) -> {			  
			  ParseResult<Y> rresult = rhs.perform(succ1.getNext());
			  return rresult.fold(
			    (succ2) -> new ParseResult.Success<>(new Pair<>(succ1.getValue(), succ2.getValue()), succ2.getNext()),
			    (failure) -> {
			    	ParseResult<Pair<X, Y>> newFailure = new ParseResult.Failure<>(failure.getMessage(), failure.getNext());
			    	return newFailure;
			    }
			  );
		  },
		  (failure) -> {
			  ParseResult<Pair<X, Y>> newFailure = new ParseResult.Failure<>(failure.getMessage(), failure.getNext());
			  return newFailure;
		  }
		);
	}
}