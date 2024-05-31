package ch03;

import org.junit.Test;

public class Operation {    // 3.5 运算符
    @Test
    public void baseOperation() {   // 算术运算符：+ - * / %(取余)
        // 1. 当参与运算的两个操作数都是整数时，/表示整数除法；否则，表示浮点数除法
        int a = 5 / 2;
        System.out.printf("a = %d\n", a);   // a = 2
        double b = 5.0 / 2;
        System.out.printf("b = %f\n", b);   // b = 2.5000000
        // 2. 整数被0除将产生一个异常，而浮点数被0除将会得到一个无穷大或NaN结果
    }

    @Test
    public void mathFunc() {    // 数学函数与常量
        // Math类中包含可能会用到的各种数学函数
        double x = 4;
        double y = Math.sqrt(x);
        System.out.println(y);      // 2.0
        // 1. Java中没有完成幂运算的运算符，因此必须使用Math类的pow方法
        double z = Math.pow(x, 3.);
        System.out.printf("z = %.2f\n", z); // 64.00

        /* 2. 一些常用的函数
        三角函数
        Math.sin
        Math.cos
        Math.tan
        Math.atan
        Math.atan2
        指数函数以及它的反函数
        Math.exp
        Math.log
        Math.log10
        常量
        Math.PI
        Math.E
         */

        // 3. 数值类型之间的转换
        /*
        如下实现箭头表示无信息丢失的转换
                        char
                          ↓
        byte -> short -> int -> long
                             ↘
                       float -> double
        如下表示可能有精度损失的转换
        int  -> float
              ↗
        long -> double
         */
        int n = 123456789;
        float f = n;
        System.out.println("f = " + f);     // 1.23456792E8

        // 4. 当用一个二元运算符连接两个值时，先要将两个操作数转换为同一种类型，然后再进行计算
        float f1 = n + f;   // int自动转换为float
        double d = 3.14;
        double d1 = d + n;  // int自动转换为double
        long l = 123L;
        long l1 = l + n;    // int自动转换为long

        // 5. 强制类型转换
        // 可能损失信息的转换要通过强制类型转换(cast)来完成
        double a = 9.997;
        int na = (int) a;    // 强制类型转换的语法格式：在圆括号中指定想要转换的目标类型
        System.out.println("na = " + na);   // na = 9
        int naa = (int) Math.round(a);  // round方法舍入一个浮点数得到最接近的整数，返回类型为long
        System.out.println("naa = " + naa); // naa = 10
    }

    @Test
    public void proOperation() {
        int x = 2;
        // 1. 如果运算符(+=, *=, &=等)得到一个值，其类型与左侧操作数的类型不同，就会发生强制类型转换
        x += 3.5;   // 等价于 x = (int) (x + 3.5);
        System.out.println("x = " + x);     // x = 5

        // 2. 赋值是一个表达式(expression)，即它有一个值，具体来讲就是所赋得那个值
        int y = x += 4;
        System.out.println("x = " + x);     // x = 9
        System.out.println("y = " + y);     // y = 9

        // 3. 自增与自减运算符
        int m = 7;
        int n = 7;
        int a = 2 * ++m;    // m = 8
        int b = 2 * n++;    // n = 8
        System.out.println("a = " + a); // a = 16
        System.out.println("b = " + b); // b = 14

        // 4. 条件运算符
        System.out.println(x < y - 1 ? x : y);  // 9

        // 5. switch表达式：需要在两个以上的值中做出选择时，可以使用switch表达式
        int seasonCode = 2;
        String seasonName = switch(seasonCode) {
            case 0 -> "Spring";
            case 1 -> "Summer";
            case 2 -> "Fall";
            case 3 -> "Winter";
            default -> "???";
        };
        System.out.println("seasonName = " + seasonName);   // seasonName = Fall

        // 6. 位运算符，可以直接处理组成整数的各个位
        // &("and"), |("or"), ^("xor"), ~("not")
        int k = 8;
        int fourthBitFormRight = (k & 0b1000) / 0b1000; // 得到k二进制表示从右起第4位值(1/0)
        System.out.println("fourthBitFormRight = " + fourthBitFormRight);   // 1
        // >>(位右移), <<(位左移), >>>(位无符号右移)使用0填充高位
        int fourthBitFormRight2 = (k & (1 << 3)) >>> 3;
        System.out.println("fourthBitFormRight2 = " + fourthBitFormRight2);   // 1

        // 7. 运算符优先级
        // - 有括号先计算括号里的，没有则按运算符优先级次序进行计算
        // - 同一个级别的运算符按照从左到右的次序进行计算
    }

}
