package ch05.reflection.arrays;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 5.9.6 使用反射编写泛型数组代码
 * 编写一个通用地方法，进行任意类型数组的扩容
 */
public class CopyOfTest {
    public static void main(String[] args) {

        //int[] a = new int[3];
        //a = Arrays.copyOf(a, 2 * a.length);
        //System.out.println("a.length=" + a.length);     // 6

        int[] a = new int[] {1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println("a.length=" + a.length); // 3
        System.out.println(a);  // 上古遗留问题，直接输出数组会得到 [I@地址 ，其中[I表示数组的类型为 int[] ，其他类推
        System.out.println("a=" + Arrays.toString(a));  // a=[1, 2, 3]

        String[] b = new String[] {"charlie", "tom", "snow"};
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b)); // [charlie, tom, snow]

        //b = (String[]) badCopyOf(b, 10);    // 报错：ClassCastException，无法将 Object[] 数组转换为 String[]类型
    }

    /**
     * 有问题，因为Java数组会记住每个元素的类型，即创建数组时 new表达式中使用的元素类型，如下为 Object
     *  则，无法将返回的 newArray数组，转换为其它类型的数组，如 Employee[]
     */
    public static Object[] badCopyOf(Object[] a, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
        return newArray;
    }

    /**
     * 将数组a指定大小newLength个元素拷贝到一个新数组中
     * 需要将参数声明为 Object类型，而不是一个对象数组(Object[])类型！
     */
    public static Object goodCopyOf(Object a, int newLength) {
        Class<?> cl = a.getClass();
        if (!cl.isArray()) return null; // 如果a不是数组类型
        Class<?> componentType = cl.getComponentType(); // 获得数组元素类型的类类型
        int length = Array.getLength(a);    // 获得数组长度
        Object newArray = Array.newInstance(componentType, length); // 使用Array动态地创建指定类型和大小的数组
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));    // 数组拷贝
        return newArray;
    }

}

