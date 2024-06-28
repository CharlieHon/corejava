package ch08.pair2;

import java.time.LocalDate;
import java.util.Date;

/**
 * 8.5 泛型代码和虚拟机
 * 1. 虚拟机没有泛型类型对象——所有对象都属于普通类。
 * 2. 类型擦除：无论何时定义一个泛型类型，都会自动提供一个相应的原始类型(raw type)。这个原始类型的名字就是去掉类型参数后的泛型类型名。
 *      类型变量会被擦除(erased)，并替换为其限定类型（或者，对于无限定的变量则替换为Object）
 * 3. Pair<T>的原始类型如下所示：
 * public class Pair {
 *  private Object first;
 *  private Object second;
 *  public Pair(Object first, Object second) {
 *      this.first = first;
 *      this.second = second;
 *  }
 *  public Object getFirst() {return first;}
 *  public Object getSecond() {return second;}
 *  public void setFirst(Object newValue) {first = newValue;}
 *  public void setSecond(Object newValue) {second = newValue;}
 * }
 * 总之，对于Java泛型的转换，需要记住以下几点：
 *  1> 虚拟机中没有泛型，只有普通的类和方法
 *  2> 所有的类型参数都会替换为它们的限定类型
 *  3> 会合成桥方法保持多态
 *  4> 为保持类型安全性，必要时会插入强制类型转换
 */
public class Pair<T> {
    private T first;
    private T second;

    public static void main(String[] args) {
        DateInterval interval = new DateInterval(LocalDate.of(2022, 11, 21), LocalDate.of(2021, 7, 1));
        Pair<LocalDate> pair = interval;
        LocalDate aDate = LocalDate.of(2024, 6, 28);
        pair.setSecond(aDate);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}

class DateInterval extends Pair<LocalDate> {

    public DateInterval() {
    }

    public DateInterval(LocalDate first, LocalDate second) {
        super(first, second);
    }

    /* 编译器会在DateInterval类中生成一个桥方法(bridge methods)
    1. public void setSecond(Object second) {setSecond(LocalDate second);}
    2. 因为上main()方法中，pair已经声明为类型Pair<LocalDate>，并且这个类型只有一个setSecond的方法，即setSecond(Object)，
        虚拟机在pair引用的对象上调用这个方法，这个对象是DateInterval类型，因而将会调用DateInterval.setSecond(Object)方法，即合成的桥方法
        它会调用DateInterval.setSecond(LocalDate)
     */

    // 日期区间是一对LocalDate对象，而且这里覆盖了getSecond方法来确保第二个值永远不小于第一个值
    public void setSecond(LocalDate second) {
        if (second.compareTo(getFirst()) >= 0) {
            super.setSecond(second);
        }
    }
}
