package ch08.pair2;

import java.time.LocalDate;

public class PairTest2 {
    public static void main(String[] args) {

        /*
        1. 当调用一个泛型方法时，可以把具体类型包围在尖括号中，放在方法名前面
        2. 在大多情况下，方法调用中可以省略<String>类型参数，编译器将参数类型与泛型类型T...进行匹配，推断出T一定是String
         */
        String middle = ArrayAlg.<String>getMiddle("Charlie", "John", "Bruce"); // ArrayAlg.getMiddle(...);
        System.out.println("middle=" + middle); // John

        Number number = ArrayAlg.getMiddle(1.36, 1, 5, 3.14);
        System.out.println("number=" + number); // 5

        LocalDate[] birthday = {
                LocalDate.of(1999, 12, 20),
                LocalDate.of(2000, 11, 21),
                LocalDate.of(2001, 5, 1),
                LocalDate.of(2001, 6, 7)
        };

        Pair<LocalDate> minmax = ArrayAlg.minmax(birthday);
        System.out.println("min=" + minmax.getFirst());     // min=1999-12-20
        System.out.println("max=" + minmax.getSecond());    // max=2001-06-07
    }
}

class ArrayAlg {

    /**
     * 8.3 泛型方法
     * 1. 方法再普通类中定义，是一个泛型方法
     * 2. 类型变量放在修饰符(这里的修饰符就是 public static)的后面，并在返回类型的前面(T)
     */
    public static <T> T getMiddle(T... a) { // 注意这里是可变数量参数，会把传递过来的参数放进一个数组中！
        System.out.println("参数类型=" + a.getClass()); // class [Ljava.lang.String;
        return a[a.length / 2];
    }

    /**
     * 8.4 类型变量的限定
     * 1. 变量min/max的类型为T，意味着它们可以时任何一个类的对象，如何知道T所属的类有一个 compareTo 方法？
     *      通过对类型变量T设置一个限定(bound)来实现
     * 2. 使用关键字 extends 而非 implements，如下表示 T 应该是限定类型的字类型(subtype)。T和限定类型可以是类，也可以是接口。
     *  选择关键字 extends 是因为它更接近字类型的概念。
     *      <T extends BoundingType>
     * 3. 一个类型变量或通配符可以有多个限定，限定类型用 (&) 分隔，而用逗号 (,) 分隔类型变量。
     *      <T extends Comparable & Serializable>
     * 4. 如果有一个类作为限定，它必须是限定列表的中的第一个限定
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }

    // 示例
    public static <T extends Pair & Comparable> Pair<T> getMinmax() {
        return null;
    }

}
