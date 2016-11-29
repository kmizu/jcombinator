package com.github.kmizu.jcombinator.core;

public class Functions {
    public static interface Fn1<T1, R> {
        R invoke(T1 arg);
    }
    public static interface Fn2<T1, T2, R> {
        R invoke(T1 arg1, T2 arg2);
    }
    public static interface Fn3<T1, T2, T3, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3);
    }
    public static interface Fn4<T1, T2, T3, T4, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
    }
    public static interface Fn5<T1, T2, T3, T4, T5, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
    }
    public static interface Fn6<T1, T2, T3, T4, T5, T6, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6);
    }
    public static interface Fn7<T1, T2, T3, T4, T5, T6, T7, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7);
    }
    public static interface Fn8<T1, T2, T3, T4, T5, T6, T7, T8, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8);
    }
    public static interface Fn9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8, T9 arg9);
    }
}
