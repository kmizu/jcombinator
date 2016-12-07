package com.github.kmizu.jcombinator.tokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.kmizu.jcombinator.core.Functions.*;
import static com.github.kmizu.jcombinator.core.Tuples.*;

public class TokenizerSpecification<T> {
    private final List<Tp2<Pattern, Fn1<String, T>>> specs = new ArrayList<>();
    private final String input;

    public TokenizerSpecification(String input) {
        this.input = input;
    }

    public final void register(Pattern pattern, Fn1<String, T> action) {
        specs.add(new Tp2<>(pattern, action));
    }

    public final Tokenizer tokenizer() {
        return new Tokenizer<T>(){
            private String next = input;
            private Tp2<Pattern, Fn1<String, T>> preparedSpec;
            @Override
            public boolean hasNext() {
                for(Tp2<Pattern, Fn1<String, T>> spec:specs) {
                    boolean shouldHaveNext = spec.extract((pattern, action) -> {
                        if(pattern.matcher(next).find(0)) {
                            preparedSpec = spec;
                            return true;
                        } else {
                            preparedSpec = null;
                            return false;
                        }
                    });
                    if(shouldHaveNext) return true;
                }
                return false;
            }

            @Override
            public T next() {
                return preparedSpec.extract((pattern, action) -> {
                    Matcher matcher = pattern.matcher(next);
                    matcher.find(0);
                    String tokenImage = next.substring(matcher.start(), matcher.end());
                    next = next.substring(tokenImage.length());
                    return action.invoke(tokenImage);
                });
            }
        };
    }
}
