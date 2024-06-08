package ch06.interfaces;

/**
 * 6.1.6 解决默认方法冲突
 * 如果先在一个接口中将一个方法定义为默认方法，然后又在超类或另一个接口中定义了同样的方法，会发生什么情况？
 * 1. 超类优先。如果超类提供了一个具体方法，同名而且有相同参数类型的默认方法会被忽略。
 * 2. 接口冲突。如果一个接口提供了一个默认房啊，另一个接口提供了同名而且有相同参数类型的方法(不论是否是默认方法)，必须覆盖这个方法来解决冲突
 *  1) 如果至少有一个接口提供了一个实现，编译器就会报告错误
 *  2) 如果两个接口都没有为共享方法提供默认实现，那么就不存在冲突
 * 3. 超类和接口继承了相同的方法，只会考虑超类房方法，接口的所有默认方法都会被忽略(超类优先)
 * 4. 绝对不能创建一个默认方法重新定义Object类中的某个方法，因为“类优先”，所以这样的方法绝对无法超越 Objects.toString 或 Objects.equals
 */
public class Student implements Person, Named {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println("name=" + student.getName());    // name=Charlie
    }

    // Student继承Person和Named接口提供的两个不一致的getName方法，并不是从中选择一个，
    // Java编译器会报告一个错误，让程序员来解决这个二义性问题
    @Override
    public String getName() {
        // Person(父接口)
        // super.getName() 调用父接口方法
        return Person.super.getName();
    }
}

interface Person {
    default String getName() {return "Charlie";}
}

interface Named {
    default String getName() {
        return getClass().getName() + "_" + hashCode();
    }
}
