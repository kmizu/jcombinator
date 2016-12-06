package com.github.kmizu.jcombinator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

import static com.github.kmizu.jcombinator.Parser.*;
import static com.github.kmizu.jcombinator.core.Functions.*;

/**
 * Unit test for simple Application.
 */
public class ChainlTest extends TestCase {
	private Rule<Integer> expression() {
	    return rule(() ->
			additive().cat(eof()).map(t -> t.fst())
		);
	}
	private Rule<Integer> additive() {
		return rule(() -> {
			final Parser<Fn2<Integer, Integer, Integer>> Q = string("+").map(op -> (Integer lhs, Integer rhs) -> lhs + rhs);
			final Parser<Fn2<Integer, Integer, Integer>> R = string("-").map(op -> (Integer lhs, Integer rhs) -> lhs - rhs);
			return multitive().chain(Q.or(R));
		});
	}
	private Rule<Integer> multitive() {
		return rule(() -> {
			final Parser<Fn2<Integer, Integer, Integer>> Q = string("*").map(op -> (Integer lhs, Integer rhs) -> lhs * rhs);
			final Parser<Fn2<Integer, Integer, Integer>> R = string("/").map(op -> (Integer lhs, Integer rhs) -> lhs / rhs);
			return primary().chain(Q.or(R));
		});
	}

	private final Rule<Integer> primary() {
		return rule(() ->
			number().or((string("(").cat(expression())).cat(string(")")).map(t -> t.fst().snd()))
		);
	}

	private final Rule<Integer> number() {
		return rule(() ->
			digit().many1().map(digits -> Integer.parseInt(join(digits, "")))
		);
	}

    public ChainlTest()
    {
    	super("Arithmetic");
    }

    public static Test suite()
    {
        return new TestSuite( ChainlTest.class );
    }

    public void testArithmetic() {
	    Parser<Integer> arithmetic = expression();
    	arithmetic.invoke("100").onSuccess(s -> {
    		assertEquals((Integer)100, s.value());
    	});
    	arithmetic.invoke("100+200").onSuccess(s -> {
    		assertEquals((Integer)300, s.value());
    	});
    	arithmetic.invoke("(1+2)*(3+4)").onSuccess(s -> {
    		assertEquals((Integer)21, s.value());
    	});
    }
}
