package ch04.init;

import java.util.Random;

/**
 * 4.6.7 初始化块
 * 1. 初始化实例字段的方法：
 * 1) 在构造器中设置值
 * 2) 在声明中赋值
 * 3) 在初始化块(initialization block)中初始化
 * 2. 在一个类的声明中，可以包含任意的代码块。构造这个类的对象时，这些块就会执行
 * 5. 调用构造器时的具体处理步骤：
 *  1> 如果构造器的第一行调用了另一个构造器，则基于所提供的参数执行第二个构造器。否则
 *  2> 所有实例字段初始化为默认值(0, false或null)
 *  3> 按照在类声明中出现的顺序，执行所有字段初始化方法(如，初始值初始化，有的话)和初始化块
 *      初始化块会按照顺序拼接起来，一起执行
 *  4>执行构造器主题代码
 * ___绵绵若存，用之不勤___
 * 6. 在类第一次加载的时候，会完成静态字段的初始化。
 *  1] 与实例字段一样，除非将静态阻断显式地设置成其它值，否则默认的初始值为0,false或null
 *  2] 所有静态字段初始化方法以及静态初始化块都将依照类声明中出现的顺序执行
 */
public class InitBlock {
    public static void main(String[] args) {
        /*
        静态代码块...初始化静态字段nextId前
        nextId=0
        初始化块1...在未赋值前：
        id=0
        初始化块2...
        id=5589
        name=charlie
        salary=0.0
        Employee5589: name=charlie, salary=0.0
        ---Finish!---
         */
        Employee e = new Employee();
        System.out.println("Employee" + e.getId() + ": name=" + e.getName() + ", salary=" + e.getSalary());
        System.out.println("---Finish!---");

        /*
        初始化块1...在未赋值前：
        id=0
        初始化块2...
        id=5590
        name=charlie
        salary=0.0
        Employee5590: name=charlie, salary=0.0
         */
        Employee e2 = new Employee();
        System.out.println("Employee" + e2.getId() + ": name=" + e2.getName() + ", salary=" + e2.getSalary());
    }
}

class Employee {

    private static int nextId;

    private int id;
    private String name = "charlie";
    private double salary;

    private static final Random generator = new Random();


    // 静态代码块只在类加载的时候执行一次
    static {
        System.out.println("静态代码块...初始化静态字段nextId前");
        System.out.println("nextId=" + nextId);
        nextId = generator.nextInt(10000);
    }

    /**
     * 3. 可以在初始化块中设置字段，即使这些字段在类后面才定义。但是，为了避免重复定义
     *      建议总是将初始化块放在字段定义之后
     * 4. 在执行代码块时，会按照它们在类中的声明顺序拼接起来，然后顺序执行！
     */
    {
        System.out.println("初始化块1...在未赋值前：");
        System.out.println("id=" + id);
        id = nextId++;
    }

    public Employee() {
        //this(5, "bruce", 10.20);
        System.out.println("id=" + id);
        System.out.println("name=" + name);
        System.out.println("salary=" + salary);
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    {
        System.out.println("初始化块2...");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
