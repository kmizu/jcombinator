package com.github.kmizu.jcombinator;

import com.github.kmizu.jcombinator.core.Tuples;

public class Cat<X, Y> implements Parser<Tuples.Tp2<X, Y>> {
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
	public ParseResult<Tuples.Tp2<X, Y>> invoke(String input) {
		ParseResult<X> lresult = lhs.invoke(input);
		return lresult.fold(
		  (succ1) -> {			  
			  ParseResult<Y> rresult = rhs.invoke(succ1.next());
			  return rresult.fold(
			    (succ2) -> new ParseResult.Success<>(new Tuples.Tp2<>(succ1.value(), succ2.value()), succ2.next()),
			    (failure) -> {
			    	ParseResult<Tuples.Tp2<X, Y>> newFailure = new ParseResult.Failure<>(failure.message(), failure.next());
			    	return newFailure;
			    }
			  );
		  },
		  (failure) -> {
			  ParseResult<Tuples.Tp2<X, Y>> newFailure = new ParseResult.Failure<>(failure.message(), failure.next());
			  return newFailure;
		  }
		);
	}
}