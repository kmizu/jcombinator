package com.github.kmizu.jcombinator;

public class RangeParser implements Parser<String> {
	private final char from;
	private final char to;
	public RangeParser(char from, char to) {
		this.from = from;
		this.to= to;
	}
	
	@Override
	public ParseResult<String> invoke(String input) {
		if(input.length() < 1) {
			return new ParseResult.Failure<>("expect: " + from + ".." + to, input);
		}else {
			char fst = input.charAt(0);
			if(from <= fst && fst <= to) {
				return new ParseResult.Success<String>(input.substring(0, 1), input.substring(1));
			} else {
  			    return new ParseResult.Failure<>("expect: " + from + ".." + to + " actual: " + fst, input);
			}
		}
	}
}