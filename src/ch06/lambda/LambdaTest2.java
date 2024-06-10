package ch06.lambda;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class LambdaTest2 {
    /**
     * 6.2.5 构造器引用
     * 1. 构造器引用与方法引用很类似，只不过方法名为 new 。具体使用哪一个构造器，取决于上下文
     * 2. 可以用数组类型建立构造器引用。例如，int[]::new是一个构造器引用，它有一个参数：数组长度。
     * 等价于lambda表达式 x -> new int[x]
     */
    @Test
    public void test1() {
        String[] strArr = {"charlie", "snow", "king"};
        ArrayList<String> names = new ArrayList<>(Arrays.asList(strArr));
        Stream<Person> stream = names.stream().map(Person::new);
        List<Person> people = stream.toList();
        System.out.println(people); // [Person{name='charlie'}, Person{name='snow'}, Person{name='king'}]
    }

    /**
     * 6.2.6 变量作用域
     * 1. lambda表达式有3个部分：
     * 1) 一个代码块
     * 2) 参数
     * 3) 自由变量的值，这是指非参数而且不在代码中定义的变量
     * 2. lambda表达式的数据结构可以存储自由变量的值，这些值被lambda表达式捕获(captured)
     * 3. 代码块连同自由变量值有一个术语，闭包(closure)。在java中，lambda表达式就是闭包
     * 4. lambda表达式可以捕获外围作用域变量的值。为了确保所捕获的值是明确定义的，在lambda表达式中，只能引用值而不会改变。
     * lambda表达式中捕获的变量必须是事实最终变量(effectively final)，变量在初始化之后就不会再为它赋新值
     */
    public static void repeatMessage(String text, int delay) {
        ActionListener listener = event -> {
            // lambda表达式有一个自由变量text
            System.out.println(text);
            Toolkit.getDefaultToolkit().beep();
        };
        new Timer(delay, listener).start();
    }

    /**
     * 5. lambda表达式的体与嵌套块有相同的作用域。
     * 在lambda表达式中声明与一个局部变量同名或局部变量是不合法的
     */
    public void test2() {
        Path first = Path.of("/usr/bin");
        // Variable 'first' is already defined in the scope
        // Comparator<String> comp = (first, second) -> first.length() - second.length();
    }

    /**
     * 6. 在一个lambda表达式中使用 this 关键字时，是指创建这个lambda表达式的方法的this参数
     * 表达式this.getClass()会调用Application对象的getClass()方法
     */
    @Test
    public void test3() {
        // class ch06.lambda.LambdaTest2
        Runnable task = () -> System.out.println(this.getClass());
        task.run();
    }

    /**
     * 6.2.7 处理lambda表达式
     * 1. 使用lambda表达式的重点是延迟执行(deferred execution)
     * 2. 要接受lambda表达式，需要选择一个函数式接口
     */
    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }

    /**
     * 6.2.8 再谈Comparator
     * 静态comparing方法接受一个“键提取器”函数，它将类型T映射为一个可比较的类型(如String)
     */
    @Test
    public void test4() {
        Person[] people = new Person[]{new Person("charlie"), new Person("snow"), new Person("leslie")};
        Arrays.sort(people, Comparator.comparing(Person::getName));
        for (Person p : people) {
            /*
            Person{name='charlie'}
            Person{name='leslie'}
            Person{name='snow'}
             */
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        // prints Hello every 1,000 milliseconds
        repeatMessage("Hello", 1000);

        repeat(10, () -> System.out.println("Hello, world!"));
    }
}

class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
