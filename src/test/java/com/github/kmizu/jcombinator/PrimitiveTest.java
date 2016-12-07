package com.github.kmizu.jcombinator;

import com.github.kmizu.jcombinator.core.Tuples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static com.github.kmizu.jcombinator.Parser.*;
import static com.github.kmizu.jcombinator.TestHelper.*;
import static com.github.kmizu.jcombinator.core.Functions.*;

@RunWith(JUnit4.class)
public class PrimitiveTest {

    @Test
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

    @Test
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

    @Test
    public void testCat() {
        let(string("a").cat(string("b")), ab -> {
            ab.invoke("ab").fold(
                    (success) -> {
                        assertEquals(new Tuples.Tp2<>("a", "b"), success.value());
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
