package ch06.clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 1. Cloneable接口没有指定 clone 方法，这个方法是从 Object 类继承得。这个接口只是作为一个标记，指示类设计者了解克隆过程
 * 2. Cloneable接口是Java提供得少数标记接口(tagging interface)，不包含任何方法，它唯一作用就是允许在类型查询时使用 instanceof
 *      if (obj instanceof Cloneable)
 * 3. 即使clone的默认(浅拷贝)实现能够满足需求，还是需要实现 Cloneable 接口，将clone定义为public，再调用super.clone()。
 * public class Cat implements Cloneable {
 *     // 可以在你的clone方法指定正确的返回类型
 *     @Override
 *     public Cat clone() throws CloneNotSupportedException {
 *         return (Cat) super.clone();
 *     }
 * }
 * 4. 如果在一个对象上调用clone，但这个对象的类并没有实现Cleanable接口，就会抛出CloneNotSupportException异常
 * 5. 不能保证子类的实现者一定会修正clone方法让它正确地完成工作，出于这个原因，在Object类中clone方法声明为protected
 */
public class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();   // 返回创建时日期
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // call Object.clone()
        Employee cloned = (Employee) super.clone();

        // 克隆可变字段
        cloned.hireDay = (Date) hireDay.clone();

        return cloned;
    }

    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}

class Cat implements Cloneable {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Cat clone() throws CloneNotSupportedException {
        return (Cat) super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
