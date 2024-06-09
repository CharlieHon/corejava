package ch06.clone;

import java.util.Arrays;

/**
 * 6.1.9 对象克隆
 * 1. 默认的克隆是“浅拷贝”，并没有克隆对象中引用的其他对象
 * 2. 浅拷贝会有什么影响吗？具体情况具体分析：
 *      1) 如果原对象和浅克隆对象共享的子对象是 **不可变的** ，那么这种共享就是安全得
 *      2) 如果子对象属于一个不可变得类，如String；或者，在对象生命周期中，子对象一直保持不变，没有更改器方法改变它，也没有方法生成它得引用
 * 3. 通常子对象是可变得，必须重新定义clone方法来建立一个 深拷贝(deep copy)，这会克隆所有子对象
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Cat original = new Cat("feifei", 3);
        // clone方法是Object的一个protected方法，因此，子类必须重写该方法才能直接调用！
        Cat copy = original.clone();
        copy.setName("triFlower");
        System.out.println("original=" + original.getName());   // original=feifei
        System.out.println("copy=" + copy.getName());   // original=triFlower

        // 所有数组类型都有一个公共地clone方法，而不是受保护的
        int[] luckyNumbers = {3, 5, 7, 9, 11, 21};
        int[] cloned = luckyNumbers.clone();
        cloned[2] = 6;
        System.out.println("luckyNumbers=" + Arrays.toString(luckyNumbers));    // [3, 5, 7, 9, 11, 21]
        System.out.println("cloned=" + Arrays.toString(cloned));                // [3, 5, 6, 9, 11, 21]
    }
}
