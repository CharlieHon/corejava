package ch06.interfaces;

/**
 * 6.1 接口(interface)
 * 1. 接口用来描述类应该做什么，而不是指定它们具体应该如何做
 * 2. 一个类可以实现(implements)一个或多个接口。要声明一个类实现某个接口，需要使用关键字 implements
 * 3. 接口中所有方法都自动是 public 方法，不必提供关键字public。但是在实现方法时，必须加上 public
 */
public interface MyComparable<T> {

    // 4. 接口中还可以定义常量，接口中定义的字段默认为 public static final 的
    //      接口中不能有实例字段
    double PI = 3.1415926;

    /**
     * - compareTo方法是抽象的，没有具体实现
     * - 任何实现Comparable接口的类都必须包含一个compareTo方法
     * - 否则，这个类也应当是抽象的
     */
    int compareTo(T other);
}
