package com.github.kmizu.jcombinator.tokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.kmizu.jcombinator.core.Functions.*;
import static com.github.kmizu.jcombinator.core.Tuples.*;

public class TokenizerSpecification<T> {
    private final List<Tp2<Pattern, Fn1<String, T>>> specs = new ArrayList<>();
    private Pattern skipPattern = Pattern.compile("(\\s)*");

    public TokenizerSpecification() {
    }

    public final void register(Pattern pattern, Fn1<String, T> action) {
        specs.add(new Tp2<>(pattern, action));
    }

    public final void register(String pattern, Fn1<String, T> action) {
        register(Pattern.compile(pattern), action);
    }

    public final void registerSkipPattern(Pattern skipPattern) {
        this.skipPattern = skipPattern;
    }

    public final void registerSkipPattern(String skipPattern) {
        this.skipPattern = Pattern.compile(skipPattern);
    }

    public final Tokenizer tokenizer(String input) {
        return new Tokenizer<T>(){
            private String nextInput = input;
            private T currentToken;
            @Override
            public boolean moveNext() {
                Matcher skipMatcher = skipPattern.matcher(nextInput);
                if(skipMatcher.lookingAt()) {
                    nextInput = nextInput.substring(skipMatcher.end());
                }
                for(Tp2<Pattern, Fn1<String, T>> spec:specs) {
                    boolean shouldHaveNext = spec.extract((pattern, action) -> {
                        Matcher matcher = pattern.matcher(nextInput);
                        if(matcher.lookingAt()) {
                            currentToken = action.invoke(nextInput.substring(matcher.start(), matcher.end()));
                            nextInput = nextInput.substring(matcher.end());
                            return true;
                        } else {
                            return false;
                        }
                    });
                    if(shouldHaveNext) return true;
                }
                return false;
            }

            @Override
            public T current() {
                return currentToken;
            }

            public String nextInput() {
                return nextInput;
            }
        };
    }
}
