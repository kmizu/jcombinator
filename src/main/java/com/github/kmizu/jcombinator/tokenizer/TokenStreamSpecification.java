package com.github.kmizu.jcombinator.tokenizer;
import com.github.kmizu.jcombinator.datatype.Function1;
import com.github.kmizu.jcombinator.datatype.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenStreamSpecification<T> {
    private final List<Tuple2<Pattern, Function1<String, T>>> specs       = new ArrayList<>();
    private Pattern                                           skipPattern = Pattern.compile("(\\s)*");

    public TokenStreamSpecification() {
    }

    public final void register(Pattern pattern, Function1<String, T> action) {
        specs.add(new Tuple2<>(pattern, action));
    }

    public final void register(String pattern, Function1<String, T> action) {
        register(Pattern.compile(pattern), action);
    }

    public final void registerSkipPattern(Pattern skipPattern) {
        this.skipPattern = skipPattern;
    }

    public final void registerSkipPattern(String skipPattern) {
        this.skipPattern = Pattern.compile(skipPattern);
    }

    public final TokenStream<T> stream(String input) {
        return new TokenStream<T>(){
            private String next = input;
            private T currentToken;
            private boolean terminal;

            {
                terminal = confirm();
            }

            public T head() {
                return currentToken;
            }

            public TokenStream<T> tail() {
                return stream(next);
            }

            public boolean isTerminal() {
                return terminal;
            }

            public boolean confirm() {
                Matcher skipMatcher = skipPattern.matcher(next);
                if(skipMatcher.lookingAt()) {
                    next = next.substring(skipMatcher.end());
                }
                for(Tuple2<Pattern, Function1<String, T>> spec:specs) {
                    boolean shouldHaveNext = spec.extract((pattern, action) -> {
                        Matcher matcher = pattern.matcher(next);
                        if(matcher.lookingAt()) {
                            currentToken = action.invoke(next.substring(matcher.start(), matcher.end()));
                            next = next.substring(matcher.end());
                            return true;
                        } else {
                            return false;
                        }
                    });
                    if(shouldHaveNext) return true;
                }
                return false;
            }
        };
    }
}
