package ch06.interfaces;

import java.util.Arrays;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Charlie", 35000);
        staff[1] = new Employee("Snow", 20000);
        staff[2] = new Employee("Tom", 50000);

        Arrays.sort(staff);

        /*
        name=Snow, salary=20000.0
        name=Charlie, salary=35000.0
        name=Tom, salary=50000.0
         */
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
        }

        Manager bruce = new Manager("Bruce", 60000, 20000);
        System.out.println(staff[0].compareTo(bruce));  // -1
        System.out.println(bruce.compareTo(staff[0]));  // 1
    }
}
