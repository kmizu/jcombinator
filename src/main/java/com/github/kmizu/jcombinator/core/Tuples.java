package com.github.kmizu.jcombinator.core;
import com.github.kmizu.jcombinator.core.Functions.*;

public class Tuples {
    public static class Tp2<X, Y> {
        private X item1;
        private Y item2;
        public Tp2(X item1, Y item2) {
            this.item1 = item1;
            this.item2 = item2;
        }

        public X item1() {
            return item1;
        }

        public Y item2() {
            return item2;
        }

        public <U> U extract(Fn2<X, Y, U> f) {
            return f.invoke(item1, item2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Tp2<?, ?> tp2 = (Tp2<?, ?>) o;

            if (!item1.equals(tp2.item1)) return false;
            return item2.equals(tp2.item2);
        }

        @Override
        public int hashCode() {
            int result = item1.hashCode();
            result = 31 * result + item2.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Tp2{" + "item1=" + item1 + ", item2=" + item2 + '}';
        }
    }
    public static final class Tp3<X, Y, Z> {
        private X item1;
        private Y item2;
        private Z item3;

        public Tp3(X item1, Y item2, Z item3) {
            this.item1 = item1;
            this.item2 = item2;
            this.item3 = item3;
        }

        public X item1() {
            return item1;
        }

        public Y item2() {
            return item2;
        }

        public Z item3() {
            return item3;
        }

        public <U> U extract(Fn3<X, Y, Z, U> f) {
            return f.invoke(item1, item2, item3);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Tp3<?, ?, ?> tp3 = (Tp3<?, ?, ?>) o;

            if (!item1.equals(tp3.item1)) return false;
            if (!item2.equals(tp3.item2)) return false;
            return item3.equals(tp3.item3);

        }

        @Override
        public int hashCode() {
            int result = item1.hashCode();
            result = 31 * result + item2.hashCode();
            result = 31 * result + item3.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Tp3{" + "item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + '}';
        }
    }

}
