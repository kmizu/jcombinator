package com.github.kmizu.jcombinator.core;

import java.util.List;

public class Functions {
	public static interface Function0<R> {
		R invoke();
	}
    public static interface Function1<T1, R> {
        R invoke(T1 arg);
    }
    public static interface Function2<T1, T2, R> {
        R invoke(T1 arg1, T2 arg2);
    }
    public static interface Function3<T1, T2, T3, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3);
    }
    public static interface Function4<T1, T2, T3, T4, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
    }
    public static interface Function5<T1, T2, T3, T4, T5, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
    }
    public static interface Function6<T1, T2, T3, T4, T5, T6, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6);
    }
    public static interface Funtion7<T1, T2, T3, T4, T5, T6, T7, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7);
    }
    public static interface Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8);
    }
    public static interface Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {
        R invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8, T9 arg9);
    }
    public static interface Procedure1<T1> {
        void invoke(T1 arg);
    }
    public static interface Procedure2<T1, T2> {
        void invoke(T1 arg1, T2 arg2);
    }
    public static interface Procedure3<T1, T2, T3> {
        void invoke(T1 arg1, T2 arg2, T3 arg3);
    }
    public static interface Procedure4<T1, T2, T3, T4> {
        void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
    }
    public static interface Procedure5<T1, T2, T3, T4, T5> {
        void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
    }
    public static interface Procedure6<T1, T2, T3, T4, T5, T6> {
        void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6);
    }
    public static interface Procedure7<T1, T2, T3, T4, T5, T6, T7> {
        void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7);
    }
    public static interface Procedure8<T1, T2, T3, T4, T5, T6, T7, T8> {
        void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8);
    }
    public static interface Procedure9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
        void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8, T9 arg9);
    }

    public static <T, U> U let(T value, Function1<T, U> fn) {
        return fn.invoke(value);
    }

    public static <T> void let(T value, Procedure1<T> pr) {
        pr.invoke(value);
    }
    
    public static <T, U> U foldLeft(List<T> list, U init, Function1<Tuples.Tuple2<U, T>, U> f) {
    	U result = init;
    	for(T t:list) {
    		result = f.invoke(new Tuples.Tuple2<>(result, t));
    	}
    	return result;
    }
    
    public static <T> String join(List<T> list, String separator) {
    	if(list.size() == 0) {
    		return "";
    	} else {
    		return let(new StringBuilder(), builder -> {
    		    T t = list.get(0);
    		    List<T> es = list.subList(1, list.size());
    			builder.append(t.toString());
    			for(T e:es) {
    				builder.append(separator);
    				builder.append(e.toString());
    			}
    			return new String(builder);
    		});
    	}
    }
}
