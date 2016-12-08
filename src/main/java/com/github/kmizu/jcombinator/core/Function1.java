package com.github.kmizu.jcombinator.core;

/**
 * Created by kota_mizushima on 2016/12/08.
 */
public interface Function1<T1, R> {
    R invoke(T1 arg);
}
