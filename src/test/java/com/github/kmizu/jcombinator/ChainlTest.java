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
	private final Rule<Integer> expression = rule();
	private final Rule<Integer> multitive = rule();
	private final Rule<Integer> additive = rule();
	private final Rule<Integer> primary = rule();
	private final Rule<Integer> number = rule();
	
	{
		expression.setBody(() -> 
		    additive.cat(eof()).map(t -> t.fst())
		);
		multitive.setBody(() -> {
			final Parser<Fn2<Integer, Integer, Integer>> Q = string("*").map(op -> (Integer lhs, Integer rhs) -> lhs * rhs);
			final Parser<Fn2<Integer, Integer, Integer>> R = string("/").map(op -> (Integer lhs, Integer rhs) -> lhs / rhs);
			return additive.chain(Q.or(R));
		});
		additive.setBody(() -> {
			final Parser<Fn2<Integer, Integer, Integer>> Q = string("+").map(op -> (Integer lhs, Integer rhs) -> lhs + rhs);
			final Parser<Fn2<Integer, Integer, Integer>> R = string("-").map(op -> (Integer lhs, Integer rhs) -> lhs - rhs);
			return primary.chain(Q.or(R));
		});
	    primary.setBody(() ->
	        number.or(string("(").cat(expression).cat(string(")")).map(t ->
	            t.fst().snd()
	        )
	    ));
	    number.setBody(() ->
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
    	number.invoke("100").onSuccess(s -> {
    		assertEquals((Integer)100, s.value());
    	});
    	expression.invoke("100+200").onSuccess(s -> {
    		assertEquals((Integer)300, s.value());
    	});
    	expression.invoke("(1+2)*(3+4)").onSuccess(s -> {
    		assertEquals((Integer)21, s.value());
    	});
    }
}
