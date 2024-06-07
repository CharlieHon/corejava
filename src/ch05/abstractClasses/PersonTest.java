package ch05.abstractClasses;

public class PersonTest {
    public static void main(String[] args) {

        // 不能创建抽象类的对象，但是可以创建一个抽象类的对象变量(object variable)！
        Person[] people = new Person[2];
        people[0] = new Employee("Tom", 60000., 2010, 12, 20);
        people[1] = new Student("Charlie", "computer science");

        for (Person p : people) {
            System.out.println(p.getName() + ", " + p.getDescription());
        }

    }
}
