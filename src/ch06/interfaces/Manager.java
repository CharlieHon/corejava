package ch06.interfaces;

/**
 * 语言标准规定：对于任意的x和y，实现者必须确保 x.compareTo(y) 和 y.compareTo(y) 符号相反。
 * - 与 equals 一样，使用继承时有可能会出现问题
 * - 这里 Manager 扩展了 Employee，而 Employee 实现了 Comparable<Employee>，而不是 Comparable<Manager>
 * - 如果 Manager 覆盖 compareTo ，就必须做好准备比较经理与员工，违反了 “反对称” 规则
 * 有两种不同的补救方式：
 * 1) 如果不同子类中的比较有不同的含义，就应该将属于不同类的对象之间的比较视为非法。每个compareTo方法首先都应该进行以下检测：
 *  if (getClass() != other.getClass()) throw new ClassCastException();
 * 2) 如果存在一个比较子类对象的通用算法，那么只需要在超类中提供一个 compareTo 方法，并将这个房啊声明为 final.
 */
public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }
}
