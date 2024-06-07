package ch05.varargs;

/**
 * 5.5 参数个数可变的方法
 * - 可以提供参数可变的方法，即变参(varargs)方法
 * - 可变参数的方法，可以为参数指定任意类型，甚至是基本类型
 */
public class PrintStreamTest {

    public static void main(String[] args) {
        double dVal = PrintStreamTest.max(3.1, 40.4, -5);
        System.out.println("dVal=" + dVal); // 40.4

        // 允许将数组作为最后一个参数传递给有可变参数的方法
        System.out.printf("%d %s", new Object[]{Integer.valueOf(7), "hello"});  // 7 hello
    }

    /**
     * 1. 省略号... 是Java代码的一部分，它表明这个方法可以接收任意数量的对象
     * 2. 实际上可变参数接收的是一个 数组类型，其中保存着所有其它参数
     * 3. 编译器将 new double[] {3.1, 40.4, -5} 传递给 max函数
     */
    public static double max(double... values) {
        // values.getClass()=class [D ，即 double[] 类型
        System.out.println("values.getClass()=" + values.getClass());
        double largest = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            if (v > largest) {
                largest = v;
            }
        }
        return largest;
    }

}
