package ch05.inheritance;

import org.junit.Test;

public class ManagerTest {
    public static void main(String[] args) {
        // 构造一个 Manager 对象
        Manager boss = new Manager("Charlie", 80000, 2026, 7, 6);
        boss.setBonus(5000);

        Employee[] staff = new Employee[3];

        staff[0] = boss;
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        // 尽管e被声明为Employee类型，但实际上e既可以引用Employee类型的对象，也可以引用Manager类型的对象
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
        }
    }

    /**
     * 5.1.5 多态
     * “is-a”规则，子类的每个对象也是超类的对象。程序种需要超类对象的任何地方都可以使用子类对象替换
     * Java种，对象变量是多态的(ploymorphic)
     */
    @Test
    public void test1() {
        Manager boss = new Manager("Snow", 650000, 2024, 7, 6);
        Employee[] staff = new Employee[3];
        staff[0] = boss;    // 变量staff[0]和boss引用同一个对象，但是编译器只将staff[0]看成是一个Employee对象
        boss.setBonus(5000);    // OK
        // 因为staff[0]声明的类型是Employee，而setBonus并不是Employee类的方法
        //staff[0].setBonus(5000);    // ERROR

        // 在Java种，子类引用数组可以转换成超类引用数组，而不需要使用强制类型转换
        Manager[] managers = new Manager[10];
        Employee[] staff2 = managers;    // OK
        // staff[0]和managers[0]是相同的引用，所有数组都要牢记创建时的元素类型
        staff2[0] = new Employee("Harry", 35000, 1988, 10, 23);
        // managers[0].setBonus(1000); // 编译器不报错，但是运行错误！
    }

    /**
     * 5.1.6 理解**方法调用**
     * 假设要调用 x.f(args)，隐式参数x声明为类C的一个对象
     * 1. 编译器查看对象的 **声明类型(C)** 和 方法名。编译器将会一一列举C类中所有名为f的方法和其超类中所有名为f而且**可访问**的方法(超类的私有方法不可访问)
     *      至此，编译器已知所有可能要调用的候选方法。
     * 2. 接下来，编译器要确定方法调用中提供的参数类型。如果在所有名为f的方法中存在一个与所提供参数类型完全匹配的方法，就选择这个方法——重载解析(overloading resolution)
     *  允许类型转换。如果没有找到与参数类型匹配的方法，或者发现经过类型转换后有多个方法与之匹配，编译器就会报告一个错误。
     *      至此，编译器已经指导需要调用方法的名字和参数类型。
     * 3. 如果是private方法、static方法、final方法或者构造器，那么编译器可以准确地指导应该调用哪些方法，这成为静态绑定(static binding)。
     *      如果 **要调用地方法依赖于隐式参数的实际类型** ，那么必须在运行时使用动态绑定。
     * 4. 程序运行并且采用动态绑定调用方法时，虚拟机必须调用与x所引对象的实际类型对应的那个方法。
     *      假设x的实际类型是D，它是C类的子类。
     *      如果D类型定义了方法f，就会调用这个方法；否则，将在D类的超类中寻找f，依次类推。
     * 虚拟机预先为每个类计算了一个方法表(method table)，其中列出了所有方法的签名和要调用的实际方法
     */


    /***
     * 5.1.8 强制类型转换
     * 1. 只能在继承层次结构内进行强制类型转换
     * 2. 在将超类强制转换成子类之前，应该使用 instanceof 进行检测
     */
    @Test
    public void test2() {
        Animal a = new Cat("Feifei", 3);
        if (a instanceof Cat) {
            Cat c = (Cat) a;
            c.mow();
        }
    }

    /**
     * 5.1.10 受保护地访问
     * 1. 任何声明为private地特性都不允许其他类访问，即使子类也不能访问超类地私有字段。
     * 2. Java中地4个访问控制修饰符
     *  1) 可由外部访问——public
     *  2) 本包和所有子类可以访问——protected
     *  3) 本包中可以访问——默认(不需要修饰夫)
     *  4) 仅本类可以访问——private
     */
    public void test3() {

    }

}

/**
 * 方法的名字和参数列表称为方法的签名(signature)
 * 1. 如果在子类中定义了一个与超类签名相同的方法，那么子类中的这个方法就会覆盖(override)超类中的相同签名的方法
 * 2. 返回类型不是签名的一部分。不过覆盖一个方法时，需要保证返回类型的兼容性，允许子类将覆盖方法的返回类型改为原返回类型的字类型。
 * 3. 子类方法不能低于超类方法的可见性，如超类方法是public，子类方法也要声明为public
 */
class Animal {
    // 对于final字段，构造对象之后就不允许改变了
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }


    /**
     * 将类中的某个特定方法声明为final，所有子类都不能覆盖这个方法
     */
    public final void say() {
        System.out.println("Hello, World!");
    }

}

/**
 * 1. 不允许扩展的类被称为final类
 * 2. 如果将一个类声明为final，只有其中的方法自动地称为final，而不包括字段。
 */
final class Cat extends Animal {
    private int age;

    public Cat(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public String getName() {
        System.out.println("hello, I'm a cat!");
        return super.getName();
    }

    public void mow() {
        System.out.println("喵嗷~");
    }

}
