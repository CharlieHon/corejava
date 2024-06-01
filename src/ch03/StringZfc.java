package ch03;

import org.junit.Test;

import javax.sound.midi.Soundbank;

public class StringZfc {    // 3.6 字符串

    @Test
    public void test1() {   // 一些常用的字符串方法
        String e = "";
        String greeting = "Hello";
        // 1. 子串
        String s = greeting.substring(0, 3);    // Hel
        // 2. 拼接
        String expletive = "Expletive";
        String PG13 = "deleted";
        String message = expletive + PG13;  // Explectivedeleted
        // 3. 当一个字符串与一个非字符串的值进行拼接时，后者会转换成字符串
        int age = 13;
        String rate = "PG" + age;   // PG13
        // 4. 多个字符串方法放在一样，用一个界定符分隔
        String all = String.join(" / ", "S", "M", "L", "XL");
        System.out.println("all = " + all);     // all = S / M / L / XL
    }

    @Test
    public void test2() {   // 字符串不可变
        /** String类没有提供任何方法来修改字符串中的某个字符，String类对象是不可变的(immutable)
         * 1. 可以修改字符串变量，如greeting，让它指向另外一个字符串
         * 2. 不可变字符串有一个很大的优点：编译器可以让字符串共享
         * 3. Java字符串大致类似于C++中的 char* 指针
         * 4. 字符串字面值会共享，而 + 或 substring 等操作得到的字符串不共享
         */
        // 1. 检测字符串是否相等
        String s = "Hello";
        String t = "Hello";
        System.out.println(s.equals(t));    // true
        System.out.println("Hello".equals(s));  // true
        // 2. 检测两个字符串是否相等，忽略大小写
        System.out.println("Hello".equalsIgnoreCase("hello"));  // true
        // 不要使用 == 运算符检测两个字符串是否相等！
        // == 运算符只能够确定两个字符串是否存放在同一个位置上
        if (s == t) {   // true
            System.out.println("s == t");
        }
        if (s.substring(0, 3) == "Hel") {   // false
            System.out.println("s.substring(0, 3) == \'Hel\'");
        }
    }

    @Test
    public void test3() {   // 空串与Null串
        // 1. 空串 "" 是长度为0的字符串
        // 通过 str.length() == 0 或 str.equals("") 判断一个字符串是否为空
        // 2. null表示目前没有任何对象与该变量关联
        // 通过 str != null 判断
        // if (str != null && str.length() != 0) 检查一个字符串既不是null也不是空串
    }

    @Test
    public void test4() {   // 码点与代码单元
        /**
         * Java字符串是一个char值序列
         * char数据类型是采用UTF-16编码表示Unicode码点的一个代码单元。
         * 最常用的Unicode字符可以用一个代码单元表示，而辅助字符需要一对代码单元表示
         */
        // 1. length() 方法将返回采用UTF-16编码表示给定字符串所需的代码单元个数
        String greeting = "hello";
        int n = greeting.length();  // 5
        // 2. 象得到实际长度，即码点个数，可以调用
        int cpCount = greeting.codePointCount(0, greeting.length());
        System.out.println(cpCount);    // 5
        // 3. s.charAt()返回位置n的代码单元
        char c = greeting.charAt(0);    // h
    }

    @Test
    public void test5() {   // String API
        // 1. charAt返回给定位置的代码单元(除非对底层的代码单元感兴趣，否则不需要调用这个方法)
        // 2. compareTo(String other)：按照字典顺序，如果字符串位于other之前，返回一个负值
        String str = "Hello";
        String other = "thank";
        System.out.println(str.compareTo(other));   // -44
        // 3. isEmpty isBlank 判断字符串为空或者由空白符组成，返回true
        // 4. equals equalsIgnoreCase 如果两个字符串相等(忽略大小写)，返回true
        // 5. startsWith(String prefix) endsWith(String suffix)：以...开头/结尾
        // 6. indexOf(String str | int cp)：返回与字符串str或码点cp相等的第一个子串的开始位置
        // lastIndexOf：最后一个子串的开始位置
        System.out.println(str.indexOf("l"));  // 2
        System.out.println(str.lastIndexOf("l"));   // 3
        // 7. toLowerCase toUpperCase：返回一个新字符串，包含院士字符串中所有字符，不过将原始字符串中的大写字母改为小写，或小写改为大写
        System.out.println(str.toUpperCase());  // HELLO
        // 8. strip stripLeading stripTrailing：删除原始字符串头部和尾部或只是头部或尾部的空白符
        String s = "  Charlie ";
        System.out.println(s.strip());  // Charlie
        System.out.println(s.stripLeading());   // Charlie ;
    }

    @Test
    public void test6() {   // 构建字符串
        char ch = 'c';
        String str = "Hello";
        // 有时候需要由较短的字符串构建字符串。
        // 如果采用字符串拼接方式来达到这个目的，效率会比较差。每次拼接都会创建一个新的String对象，既耗时，又浪费空间。
        // 使用StringBuilder类可以避免这个问题
        // 1) 首先，构建一个空的字符串构造器
        StringBuilder builder = new StringBuilder();
        // 2) 当每次需要添加另外一部分时，就调用 append 方法
        builder.append(ch);
        builder.append(str);
        // 3) 字符串构建完成后，调用 toString 方法。就会得到一个String对象，其中包含了构建器重的字符序列
        String string = builder.toString();
        System.out.println("string = " + string);   // cHello

        // StringBuffer类的效率不如StringBuilder类，不过它允许采用多线程的方式添加或删除字符。两个类的API是一样的

        // StringBuilder API
        // 1. length：返回构建器重的代码单元个数
        int len = builder.length();
        System.out.println("len = " + len); // 6
        // 2. append：追加一个字符串/代码单元并返回 this
        // insert(int offset, String str)：在offset位置插入一个代码单元并返回this
        builder.insert(2, "kid");
        System.out.println(builder); // cHkidello
        // 3. delete(int startIndex, int endIndex)：删除从startIndex到endIndex-1的代码单元并返回this
        System.out.println(builder.delete(2, 5));   // cHello
    }

}
