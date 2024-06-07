package ch05.abstractClasses;

/**
 * 5.6 抽象类
 * 1. 抽象类不能实例化。将它作为派生其他类的基类，而不是用来构造想使用的特定实例
 * 2. 抽象类可以不包含抽象方法，但是包含一个或多个抽象方法的类本身必须声明为抽象的
 * 3. 抽象类还可以包含字段和具体方法
 */
public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    /**
     * 抽象方法相当于子类中实现的具体方法的占位符。
     * 扩展一个抽象类时，可以有两种选择：
     * 1) 在子类中保留抽象类中的部分或所有抽象方法仍未定义，这就必须将子类也标记为抽象类
     * 2) 定义全部方法，子类就不再时抽象的
     */
    public abstract String getDescription();

    public String getName() {
        return name;
    }
}
