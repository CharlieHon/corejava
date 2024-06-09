package ch05.varargs;

import java.util.Arrays;

/**
 * 5.5 参数个数可变的方法
 * - 可以提供参数可变的方法，即变参(varargs)方法
 * - 可变参数的方法，可以为参数指定任意类型，甚至是基本类型
 */
public class PrintStreamTest {

    public static void main(String[] args) {
        double dVal = PrintStreamTest.max(3.1, 40.4, -5);
        System.out.println("dVal=" + dVal); // 40.4

        // 可以不向不变数组中传入参数，则接受的参数为空数组，即 []
        double d = PrintStreamTest.max();

        // 对于数组类型参数(如int[])，则必须提供该参数，否则会报错
        int iVal = PrintStreamTest.max("", new int[0]);

        // 允许将数组作为最后一个参数传递给有可变参数的方法
        System.out.printf("%d %s", new Object[]{Integer.valueOf(7), "hello"});  // 7 hello
    }

    /**
     * 1. 省略号... 是Java代码的一部分，它表明这个方法可以接收任意数量的对象
     * 2. 实际上可变参数接收的是一个 数组类型，其中保存着所有其它参数
     * 3. 编译器将 new double[] {3.1, 40.4, -5} 传递给 max函数
     * 4. 可变参数类型与数组类型的区别在于数组类型参数不能啥都不传入，而可变类型参数可以，编译器会认为传入了一个空数组
     * 4. 不能重复定义 max(double... ); 和 double max(double[]);
     */
    public static double max(double... values) {
        // values.getClass()=class [D ，即 double[] 类型
        //System.out.println("values.getClass()=" + values.getClass());
        System.out.println(Arrays.toString(values));
        double largest = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            if (v > largest) {
                largest = v;
            }
        }
        return largest;
    }

    public static int max(String param, int[] values) {
        System.out.println("只是区别一下参数..." + param);
        System.out.println(Arrays.toString(values));
        int largest = Integer.MIN_VALUE;
        for (int v : values) {
            if (v > largest) {
                largest = v;
            }
        }
        return largest;
    }

}
