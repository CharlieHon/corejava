package ch05.inheritance;

/**
 * 1. 关键字 extends 表示继承
 * 2. 在Java中，所有继承都是公有继承
 * 3. Java语言规范指出：声明为私有的类成员不会被这个类的子类继承。
 *      因为，子类(Manage类)不能直接访问这些私有字段。
 *      所以，每个子类(Manage)对象 **有** 超累中(Employee)的3个字段(name, salary, hireDay)，但是Manage类并没有 “继承” 这些字段
 * 关键字this有两个含义：
 * 1. 指示隐式参数的引用，即调用方法的对象的引用
 * 2. 调用该类的其它构造器。
 * super关键字也有两个含义：
 * 1. 调用超类的方法
 * 2. 调用超类的构造器
 * 用来调用构造器时，this和super这两个关键字语句只能作为另一个构造器的第一条语句出现
 */
public class Manager extends Employee { // 5.1 类、超类和子类
    private double bonus;

    /**
     * 5.1.3 子类构造器
     * 1. super(name, salary, year, month, day); 调用超类构造器的简写形式。
     *      由于Manage类的构造器不能访问Employee类的私有字段，所以必须通过一个构造器来初始化这些私有字段
     * 2. 使用 super 调用构造器的语句必须是子类构造器的第一条语句
     * 3. 如果构造子类对象时没有显示地调用超类的构造器，那么超类必须有一个无参构造器。这个构造器在子类构造之前调用
     */
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    /**
     * 5.1.2 覆盖方法
     * 1. 只有Employee方法能直接访问Employee类的私有字段
     * 2. 使用关键字 super 调用超类方法。注意：super不是一个对象引用
     * 3. 子类可以增加字段、方法或覆盖超类的方法。但是，继承绝对不会删除任何字段或方法
     */
    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }

}
