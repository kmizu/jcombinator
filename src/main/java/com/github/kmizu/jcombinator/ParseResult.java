package com.github.kmizu.jcombinator;

public class ParseResult<T> {
	public static class Success<T> extends ParseResult<T> {
		private T value;
		private String next;
		Success(T value, String next) {
			this.value = value;
			this.next = next;
		}
		public T getValue() {
			return value;
		}
		public String getNext() {
			return next;
		}
	}
	
	public static class Failure<T> extends ParseResult<T> {		
	}
}
