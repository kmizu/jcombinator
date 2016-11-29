package com.github.kmizu.jcombinator;

import static com.github.kmizu.jcombinator.core.Functions.*;


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
		  Fn1<Success<T>, U> succ,
		  Fn1<Failure<T>, U> fail) {
			return succ.invoke(this);
		}

		@Override
		public <U> ParseResult<U> map(Fn1<T, U> fn) {
			return new Success<U>(fn.invoke(value), next);
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
		  Fn1<Success<T>, U> succ,
		  Fn1<Failure<T>, U> fail) {
			return fail.invoke(this);
		}

		@Override
		public <U> ParseResult<U> map(Fn1<T, U> fn) {
			return (ParseResult<U>)this;
		}
	}
	
	<U> U fold(
		Fn1<Success<T>, U> succ,
	    Fn1<Failure<T>, U> fail
	);

	<U> ParseResult<U> map(Fn1<T, U> fn);
}
