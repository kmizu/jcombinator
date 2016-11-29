package com.github.kmizu.jcombinator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

import static com.github.kmizu.jcombinator.Parser.*;
import static com.github.kmizu.jcombinator.TestHelper.*;

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
        Parser<List<String>> ax = string("a").many();
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
    }

    public void testCat() {
        Parser<Tp2<String, String>> ab = string("a").cat(string("b"));
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
    }
}
