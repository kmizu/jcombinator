package com.github.kmizu.jcombinator;

import java.util.function.Function;

public interface ParseResult<T> {
	public static class Success<T> implements ParseResult<T> {
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
		
		@Override
		public <U> U fold(
		  Function<Success<T>, U> succ,
		  Function<Failure<T>, U> fail) {
			return succ.apply(this);
		}
	}
	
	public static class Failure<T> implements ParseResult<T> {		
		private String message;
		private String next;
		public Failure(String message, String next) {
			this.message = message;
			this.next = next;
		}
		public String getMessage() {
			return message;
		}
		public String getNext() {
			return next;
		}
		
		@Override
		public <U> U fold(
		  Function<Success<T>, U> succ,
		  Function<Failure<T>, U> fail) {
			return fail.apply(this);
		}
	}
	
	<U> U fold(
		Function<Success<T>, U> succ,
	    Function<Failure<T>, U> fail
	);
}
