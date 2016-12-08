package com.github.kmizu.jcombinator.core;

/**
 * Created by kota_mizushima on 2016/12/08.
 */
public interface Function5<T1, T2, T3, T4, T5, R> {
    R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
}
