package ch03;

public class Variable { // 3.4 变量与常量

    // 常量：2.如果一个常量经常在一个类的多个方法中使用，通常将这些常量称为类常量(class constant)
    // 使用关键字 static final 设置一个类常量
    public static final double CM_PER_INCH = 2.54;

    public void var() { // 变量
        // 1. Java变量名由字母、数字、货币符号以及“标点连接符”组成。第一个字符不能是数字
        int i, j;   // 可以在一行中声明多个变量(不提倡)
        int vacationDays;
        // 2. 必须用赋值语句显示地初始化变量，不能使用未初始化地变量！
        //System.out.println(vacationDays);
        vacationDays = 66;
        // 3. 可以将变量地声明和初始化放在同一行中
        int happyDays = 7;
    }

    public void finalType() { // 常量
        // 可以用关键字 final 指示变量
        // 1. 关键字 final 表示这个变量只能倍赋值一次，一旦赋值，就不能更改了
        final double PI = 3.1415926;
        // 2. 通过 类型.类常量 使用牠
        System.out.println(Variable.CM_PER_INCH);   // 2.54
    }

    public void enumType() {    // 枚举类型
        // 一个变量只包含有限的一组值，可以使用自定义枚举类型(enumerated type)。枚举类型包含有限个命名值
        enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};  // java8不允许局部定义enum类型
        Size s = Size.SMALL;
    }
}
