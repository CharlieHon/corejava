package ch06.interfaces;

/**
 * 6.1.2 接口的属性
 */
public class InterfaceTest {
    public static void main(String[] args) {
        // 1. 接口不是类，不能使用 new 操作符实例化一个接口，但仍然能声明接口变量
        Testable t = new Cat();
        // 2. 使用 instanceof 检查一个对象是否属于某个特定类一样，也可以检查一个对象是否实现类某个特定的接口
        if (t instanceof Testable) System.out.println("true");  // true

        System.out.println(Testable.TIMI);  // 1999.12

        Cat cat = new Cat();
        cat.prepare();  // Cat can't fly.
    }
}


interface Testable {
    // 3. 接口中不能包含实例字段，但是可以包含常量
    // 接口中的方法都自动为 public
    // 接口中的字段总是 public static final
    double TIMI = 1999.12;  // a public static final constant
}

// 4. 每个类只能有一个超类，但是可以实现多个接口。使用逗号(,)将想要实现的各个接口分隔开
class Cat implements Testable, Flyable {
    @Override
    public int prepare() {
        // 调用父接口中的默认方法
        //return Flyable.super.prepare();
        System.out.println("Cat can't fly.");
        return 0;
    }
}
