package ch06.interfaces;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 6.1.8 Comparator接口
 * 1. 可以对一个字符串数组排序，因为String类实现了 Comparable<String>
 * 2. 如何自定义 在已经实现了Comparator接口的类对象的 排序规则？
 * 3.  public static <T> void sort(T[] a, Comparator<? super T> c)
 *      接受一个数组和一个比较器作为参数，比较器是实现了 Comparator 接口的类的实例
 */
public class ComparatorTest {
    public static void main(String[] args) {
        String[] words = {"charlie", "snow"};
        LengthComparator comp = new LengthComparator();
        if (comp.compare(words[0], words[1]) < 0) System.out.println("Hello");

        Arrays.sort(words, new LengthComparator());
        System.out.println(Arrays.toString(words)); // [snow, charlie]

    }
}

/**
 * 完成比较时，需要建立一个实例，compare方法要在比较器对象上调用，而不是字符串本身调用
 * comp.compare(words[0], words[1])
 */
class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}