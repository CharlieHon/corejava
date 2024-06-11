package ch04.init;

import java.util.Random;

public class ConstructorTest {
    public static void main(String[] args) {
        Employee2[] staff = new Employee2[3];

        staff[0] = new Employee2("Harry", 40000);
        staff[1] = new Employee2(60000);
        staff[2] = new Employee2();

        /*
        name=Harry,id=8600,salary=40000.0
        name=Employee#8601,id=8601,salary=60000.0
        name=,id=8602,salary=0.0
         */
        for (Employee2 e : staff)
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary="
                    + e.getSalary());
    }
}

class Employee2 {
    private static int nextId;

    private int id;
    private String name = "";   // 实例字段初始化
    private double salary;

    private static Random generator = new Random();

    // 静态初始化块
    static {
        nextId = generator.nextInt(10000);
    }

    // 对象初始化块
    {
        id = nextId;
        nextId++;
    }

    // 三个重构构造器
    public Employee2(String n, double s) {
        name = n;
        salary = s;
    }

    public Employee2(double s) {
        this("Employee#" + nextId, s);
    }

    // 默认构造器
    public Employee2() {
        // name 初始化为 ""
        // salary 没有显式初始化——则默认初始化为 0
        // id 在代码块中初始化
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
