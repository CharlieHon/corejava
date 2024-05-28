package ch03;

import org.junit.Test;

/**
 * 02_数据类型
 * 1. Java是一种强类型语言，必须为每一个变量声明一个类型。
 * 2. Java中，一共有 8 种基本类型(primitive type)：4种整型、2种浮点型、1种字符类型char(用于表示Unicode编码的代码单元)和
 * 1种表示真值的 boolean 类型
 * 3. Java中有表示任意精度的算术包，大数(big number)是Java对象，而不是基本Java类型
 * 4. Java种，所有数值类型的大小斗鱼平台无关
 * 5. Java没有无符号(unsigned)形式的 int,long,byte或short类型
 */
public class DataType {
    public static void main(String[] args) {

    }

    public void integer() {
        // 整形表示没有小数部分的数，可以是负数。Java提供了4种整型
        byte a = -128;  // 1字节 -128~127。底层文件处理等
        short b = 56;   // 2字节 -32768~32767
        int c = 3625;   // 4字节 略高于20亿。最常用
        long d = 58L;    // 8字节 长整型数值有一个后缀L/l

        int e = 0x12;   // 16进制前缀 0x/0X
        int f = 06;     // 8进制前缀  0
        int g = 0b1010; // 2进制前缀  0b/0B
        int h = 1_000_000;  // 可以为数字字面值量加下划线
    }

    public void floatNumber() {
        // 浮点类型用于表示有小数。
        // 1. double的类型精度是float类型的两倍
        float f = 3.1415f;  // 4字节 6~7位有效数字
        double d1 = 3.14159265154;   // 15位有效数字
        // 2. 没有后缀F的浮点数值是默认为double类型。也可以在double数值后面添加后缀D/d
        double d2 = 3.19491977d;
        /* 3. 所有浮点数计算都遵循IEEE 754规范。有3个特殊的浮点数值表示溢出和出错情况
        正无穷大: Double.POSITIVE_INFINITY
        负无穷大: Double.NEGATIVE_INFINITY
        NaN（不是一个数）: Double.NaN
         */
        // 4. 所有 NaN值 都是不相同的，可以使用 Double.isNaN 方法来判断
        // 5. 浮点数值采用二进制表示，而二进制系统中无法精确地表示分数1/10。如果需要精确的数值计算，不允许有舍入误差，则应该使用 BigDecimal
    }

    @Test
    public void charType() {
        // 1. char类型字面值使用单引号('')括起来
        char a = 'a';
        // 2. char类型的值可以表示为十六进制值，其范围从 \u0000 ~ \uffff
        char b = '\u2122';
        System.out.println("a = " + b);   // a = ™
        // 3. Unicode转义序列会在解析代码之前处理。
        String c = "\u0022+\u0022";
        System.out.println("c = " + c);     // \u0022 会在解析之前转换为 "，因此得到 ""+"" ，即结果是一个空串！
        // 4. 注意：当注释中出现 \u 时也会在解析之气进行替换。如下解析成一个换行，则其后面的内容实际开始在一个新行里，因此会报错
        // \u000A is a new line
        // c:\user 同样会报错，因为 \u 后面没有跟着4位十六进制数
    }

    public void boolean_type() {
        // boolean(布尔)类型有两个值：false 和 true，用来判定逻辑条件。整型和布尔值之间不能进行相互转换
        int x = 0;
        // if (x = 0) 报错：x = 0 表达式的值即赋的值0，整形不能转换为boolean，因此报错
    }

}
