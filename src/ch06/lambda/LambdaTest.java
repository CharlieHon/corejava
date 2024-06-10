package ch06.lambda;

import jdk.jshell.execution.LoaderDelegate;
import org.junit.Test;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 6.2 lambda表达式
 */
public class LambdaTest {

    public static void main(String[] args) {

        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("按字典序排序：");
        Arrays.sort(planets);
        // [Earth, Jupiter, Mars, Mercury, Neptune, Saturn, Uranus, Venus]
        System.out.println(Arrays.toString(planets));

        System.out.println("按照长度排序");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        // [Mars, Earth, Venus, Saturn, Uranus, Jupiter, Mercury, Neptune]
        System.out.println(Arrays.toString(planets));

        Timer timer = new Timer(1000, event ->
                System.out.println("The time is " + new Date()));
        timer.start();

        // Keep program running utils user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

    /**
     * 6.2.2 lambda表达式得语法
     * 1. lambda表达式形式：参数，箭头(->)以及一个表达式。如果代码要完成的计算无法在一个表达式中，就可以像写方法一样，把这些代码放在 {} 中，
     * 并包含显式的 return 语句
     * 2. 如果一个lambda表达式只在某些分支返回一个值，而另外一些分支不返回值，这是不合法的。例如：
     * (int x) -> {if (x >= 0) return 1;}
     */
    @Test
    public void test1() {
        // 1) 第一个lambda表达式(等号右边部分)，lambda表达式就是一个代码块，以及必须传入代码得所有变量得规范
        // 4) 如果可以推导出一个lambda表达式的参数类型，则可以忽略其类型
        Comparator<String> comp1 = (first, second) -> first.length() - second.length();
        // 2) {}和显式return语句
        Comparator<String> comp2 = (String first, String second) -> {
            if (first.length() < second.length()) return -1;
            else if (first.length() > second.length()) {
                return 1;
            } else {
                return 0;
            }
        };
        // 3) 即使lambda表达式没有参数，仍然要提供空括号
        Runnable comp3 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        };
        // 5) 如果方法只有一个参数，而且这个参数的类型可以推导出来，那么可以省略小括号
        //      instead of (event) -> ... or (ActionEvent event) -> ...
        ActionListener listener = event -> System.out.println("The time is " + Instant.ofEpochMilli(event.getWhen()));
    }

    /**
     * 6.2.3 函数式接口
     * 1. 对于只有一个抽象方法的接口，需要这种接口的对象时，可以提供一个 lambda表达式。这种接口称为函数式接口(functional interface)。
     * 注：只有 一个抽象方法，可以有静态、私有或默认实现方法
     * 2. 最好把 lambda表达式 看作是一个函数，而不是一个对象
     * 3. lambda表达式可以转换为接口。实际上，在java中，对lambda表达式所能做的也只是 转换 为函数式接口
     * 4. 甚至不能把 lambda表达式 赋给类型为 Object 的变量，Object不是一个函数式接口
     */
    @Test
    public void test2() {
        // 1) java.util.function包中有很多非常通用的函数式接口
        // 2) BiFunction<T, U, R> 描述了参数类型为 T 和 U 而且返回类型为 R 的函数
        BiFunction<String, String, Integer> comp = (first, second) -> first.length() - second.length();

        /** 3) 一个尤其有用的接口 Predicate(谓语)
         @FunctionalInterface public interface Predicate<T> {
         boolean test(T t);
         }
         */
        Predicate<String> predicate = s -> s == null;
        String[] strArr = {"charlie", "snow", "", null, "king"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(strArr));
        list.removeIf(predicate);
        System.out.println(list);   // [charlie, snow, , king]

        /**
         * 4) 另一个有用的函数式接口是 Supplier<T>。
         *   @FunctionalInterface
         *   public interface Supplier<T> {
         *      T get ();
         *   }
         * 1> 供应者(supplier)没有参数，调用时会生成一个T类型的值
         * 2> 供应者用于实现懒计算(lazy evaluation)
         */
        LocalDate day = null;
        LocalDate h1 = Objects.requireNonNullElse(day, LocalDate.of(2024, 6, 10));
        // 上不是最优的。因为day很少为null，所以希望只在必要时才构造默认的LocalDate
        // requireNonNullElseGet方法只在需要值时才调用供应者
        LocalDate h2 = Objects.requireNonNullElseGet(day, () -> LocalDate.of(2019, 6, 7));
    }

    /**
     * 6.2.4 方法引用
     * 1. 表达式 System.out::println 是一个方法引用(method reference)，它指示编译器生成一个函数式接口的实例，覆盖这个接口的抽象方法
     *      来调用给定的方法。
     * 2. 在这个例子中，会生成一个 ActionListener，它的 actionPerformed(ActionEvent e) 方法要调用 System.out.println(e);
     * 3. 类似于lambda表达式，方法引用也不是一个对象。不过，为一个类型为函数式接口的变量赋值时会生成一个对象
     * 4. PrintStream类(System.out就是该类的一个实例)中有10个重载的println方法。编译器需要根据上下文确定使用哪一个方法。
     * 5. 要用 :: 操作符分隔 方法名 与 对象/类名，主要有3种情况：
     *  1) object::instanceMethod
     *      等价于一个 lambda表达式，其参数要传递到方法(lambda参数作为这个方法的显式参数传入)。
     *      对于 System.out::println，对象是 System.out，所以这个方法表达式等价于 x -> System.out.println(x);
     *  2) Class::instanceMethod
     *      第1个参数会成为方法的隐式参数，其余的参数会传递到方法
     *      例如，String::compareToIgnoreCase 等同于 (x, y) -> x.compareToIgnore(y);
     *  3) Class::staticMethod
     *      所有参数都传递到静态方法
     *      Math.pow 等价于 (x, y) -> Math.pow(x, y);
     * 6. 只有当lambda表达式的体**只调用一个方法而不做其它操作**时，才能把 lambda表达式 重写为 方法引用。
     *      如 s -> s.length() == 0; 这里有一个方法调用，还有一个比较，所以不能使用方法引用。
     * 7. 类似于lambda表达式，方法引用不会独立存在，总是会转换为 函数式接口的实例
     */
    @Test
    public void test3() {
        // Timer timer = new Timer(1000, event -> System.out.println(event));
        Timer timer = new Timer(1000, System.out::println);

        // 因为 Runnable 函数式接口 有一个无参的抽象方法(void run())，在这种情况下，会选择无参数的println方法。
        // 调用 task.run() 会向 System.out 打印一个空行
        Runnable task = System.out::println;

        // 对字符串排序，而不考虑字母的大小写
        String[] strings = {"charlie", "snow", "bruce", "leslie"};
        Arrays.sort(strings, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strings));   // [bruce, charlie, leslie, snow]
    }
}

/**
 * 1. 可以在方法引用中使用 this 参数。
 *      例如，this::equals 等同于 x -> this.equals(x);
 * 2. 使用 super 也是合法的。
 *      super::instanceMethod
 */
class Greeter {
    public void greet(ActionEvent event) {
        System.out.println("hello, the time is " + Instant.ofEpochMilli(event.getWhen()));
    }
}

class RepeatedGreeter extends Greeter {
    @Override
    public void greet(ActionEvent event) {
        Timer timer = new Timer(1000, super::greet);
        timer.start();
    }
}
