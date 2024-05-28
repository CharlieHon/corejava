package ch03;

/** 01_一个简单的Java程序
 * 注释方式(3)可以用来自动生成文档
 * 1. public：访问修饰符，控制程序的其它部分对这段代码的访问级别
 * 2. class：表明Java程序中的全部内容都包含再类中
 * 3. 标准命名约定：类名是以大写字母开头的名字。如果有多个单词组成，每个单词的第一个字母都应该大写——驼峰命名法
 * 4. 源代码的文件名 FirstSample.java 必须与公共类 FirstSample 的类名相同，并以 .java 作为扩展名
 * 5. 编译源代码后会得到一个包含这个类字节码的文件，Java编译器将这个字节码文件自动地命名为 FirstSample.class
 */
public class FirstSample {
    // 单行注释(1)：Java虚拟机总是从指定类中 main 方法的代码开始执行。因此为了能够执行代码，类的源代码中必须包含一个 main 方法
    public static void main(String[] args) {    // Java中 main 方法必须声明为 public，且总是静态的 (static)
        /* 注释方式(2)
            - 使用 System.out 对象并调用它的 println 方法
            - 点号(.)用于调用方法：object.method(parameters)
            - Java中使用双引号("")界定字符串
         */
        System.out.println("We will not use 'Hello, World!");   // println输出后换行
        System.out.print("Hello, Charlie"); // print不在输出之后增加换行符
    }
}
