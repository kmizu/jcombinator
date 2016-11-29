package com.github.kmizu.jcombinator;

public class Tp2<X, Y> {
	private X fst;
	private Y snd;
	public Tp2(X fst, Y snd) {
		this.fst = fst;
		this.snd = snd;
	}
	public X fst() {
		return fst;
	}
	public Y snd() {
		return snd;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tp2<?, ?> tp2 = (Tp2<?, ?>) o;

		if (!fst.equals(tp2.fst)) return false;
		return snd.equals(tp2.snd);
	}

	@Override
	public int hashCode() {
		int result = fst.hashCode();
		result = 31 * result + snd.hashCode();
		return result;
	}
}
