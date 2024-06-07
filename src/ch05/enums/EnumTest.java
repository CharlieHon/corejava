package ch05.enums;

import java.util.Scanner;

/**
 * 5.7 枚举类
 */
public class EnumTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.getAbbreviation());

        // 在比较枚举类型时，可以直接使用 == 比较
        if (size == Size.EXTRA_LARGE) {
            System.out.println("Good job!");
        }

        /**
         * 所有枚举类型都是抽象类Enum的子类
         * public abstract class Enum<E extends Enum<E>>
         *         implements Constable, Comparable<E>, Serializable {}
         * 它们继承了这个类的许多方法
         */
        // 静态方法valueOf是toString()的逆方法
        Size s = Enum.valueOf(Size.class, "MEDIUM");    // 将s设置成 Size.MEDIUM
        // toString()方法返回枚举常量名
        String sString = s.toString();
        System.out.println("sString=" + sString);   // MEDIUM
        // 每个枚举类型都有一个静态的 values 方法，它将返回一个包含全部枚举值的数组
        Size[] values = Size.values();
        // ordinal方法返回一个枚举常量在enum声明中的位置，位置从0开始计数
        int ordinal = Size.MEDIUM.ordinal();
        System.out.println("ordinal=" + ordinal);   // 1
        // compareTo方法比较两个枚举常量出现次序
        System.out.println(s.compareTo(values[0])); // s在后，返回一个正数
    }
}

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbreviation;

    // 枚举的构造器总是私有的
    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
