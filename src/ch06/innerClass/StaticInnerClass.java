package ch06.innerClass;

/**
 * 6.3.7 静态内部类
 * 1. 有时候使用内部类只是为了把一个类隐藏在另外一个类的内部，并不需要内部类有外部类的一个引用，此时可以将内部类声明为static
 * 2. 只有内部类能声明为static
 */
public class StaticInnerClass {
    public static void main(String[] args) {
        double[] values = new double[20];
        for (int i = 0; i < values.length; i++) {
            values[i] = 100 * Math.random();
        }
        ArrayAlg.Pair p = ArrayAlg.Pair.minmax(values);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayAlg {

    /**
     * 静态内部类 Pair
     * 3. 只要内部类不需要访问外部类对象，就应该使用静态内部类
     * 4. 静态内部类可以有静态字段和方法
     * 5. 在接口中声明的内部类自动是 static 和 public
     * 6. 类中声明的接口，记录和枚举都自动为 static
     */
    public static class Pair {
        private double first;
        private double second;

        public Pair(double f, double s) {
            first = f;
            second = s;
        }

        public static Pair minmax(double[] values) {
            double max = Double.NEGATIVE_INFINITY;
            double min = Double.POSITIVE_INFINITY;

            for (double v : values) {
                if (v > max) max = v;
                if (v < min) min = v;
            }
            return new Pair(min, max);
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }
}
