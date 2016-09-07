package com.github.kmizu.jcombinator;

public class Pair<X, Y> {
	private X fst;
	private Y snd;
	public Pair(X fst, Y snd) {
		this.fst = fst;
		this.snd = snd;
	}
	public X getFst() {
		return fst;
	}
	public Y getSnd() {
		return snd;
	}
}
