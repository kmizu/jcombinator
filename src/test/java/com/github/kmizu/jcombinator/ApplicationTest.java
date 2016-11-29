package com.github.kmizu.jcombinator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

import static com.github.kmizu.jcombinator.Parser.*;
import static com.github.kmizu.jcombinator.TestHelper.*;
import static com.github.kmizu.jcombinator.core.Functions.*;

/**
 * Unit test for simple Application.
 */
public class ApplicationTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ApplicationTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ApplicationTest.class );
    }

    public void testStringParser()
    {
        Parser<String> helloWorld = string("Hello, World");
        helloWorld.invoke("Hello, World").fold(
            (success) -> {
                assertEquals("Hello, World", success.value());
                assertEquals("", success.next());
                return null;
            },
            (failure) -> {
               assertTrue(false);
               return null;
            }
        );
    }

    public void testManyParser() {
        let(string("a").many(), ax -> {
            ax.invoke("aaaaa").fold(
                    (success) -> {
                        assertEquals(listOf("a", "a", "a", "a", "a"), success.value());
                        assertEquals("", success.next());
                        return null;
                    },
                    (failure) -> {
                        assertTrue(false);
                        return null;
                    }
            );
        });
    }

    public void testCat() {
        let(string("a").cat(string("b")), ab -> {
            ab.invoke("ab").fold(
                    (success) -> {
                        assertEquals(new Tp2<>("a", "b"), success.value());
                        assertEquals("", success.next());
                        return null;
                    },
                    (failure) -> {
                        assertTrue(false);
                        return null;
                    }
            );

        });
    }
}
