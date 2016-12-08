package com.github.kmizu.jcombinator.core;

/**
 * Created by kota_mizushima on 2016/12/08.
 */
public interface Function2<T1, T2, R> {
    R invoke(T1 arg1, T2 arg2);
}
