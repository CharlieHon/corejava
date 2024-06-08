package ch05.reflection.methods;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * 5.9.7 调用任意方法和构造器
 * > 通过反射调用方法
 * 1. Method类有一个invoke方法，允许调用包装在当前Method对象中的方法，invoke方法签名为
 *      Object invoke(Object obj, Object... args)
 *      对于静态方法，第一个参数会忽略，即可以将它设置为 null
 *
 *
 */
public class MethodTableTest {
    public static void main(String[] args) throws ReflectiveOperationException {

        /**
         * 如何获得Method对象呢？
         * 1. 可以调用 getDeclaredMethods 方法，然后搜索返回的 Method对象数组，直到发现想要的方法为止
         * 2. 调用 Class类的 getMethod 方法。getMethod的签名为
         *      Method getMethod(String name, Class<?>... parameterTypes)
         */

        // 使用类似的方法调用任意的构造器
        Constructor<Random> cons = Random.class.getConstructor(long.class);
        Random random = cons.newInstance(42L);

        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);

    }

    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) throws InvocationTargetException, IllegalAccessException {
        System.out.println(f);

        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx) {
            double y = (Double) f.invoke(null, x);
            System.out.printf("%10.4f | %10.4f%n", x, y);
        }

    }

}
