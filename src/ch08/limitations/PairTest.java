package ch08.limitations;

import java.util.ArrayList;
import java.util.Collection;

public class PairTest {
    public static void main(String[] args) {
        // 1. 不能使用基本类型实例化类型参数，原因就在于类型擦除。擦除之后，Pair类含有Object类型的值，而Object不能存储double值
        // Pair<double> a = null;    // ERROR

        // 2. 运行时类型查询只适用于原始类型？
        Pair<String> stringPair = new Pair<>("charlie", "han");
        if (stringPair instanceof Pair<String>) {
            System.out.println(stringPair.getClass());  // class ch08.limitations.Pair
        }

        // 3. 不能创建参数化类型的数组，还是因为类型擦除。如果能创建的话，类型擦除为 Pair[]，可以转化为Object[]，
        //      数组会记住它的元素类型，如果视图存储类型不正确的元素，就会抛出一个ArrayStore-Exception异常：
        //      objArray[0] = "Hello";  // ERROR-component type is Pair
        // 不过对于泛型类型，擦除会使这种机制失效。如：objArray = new Pair<Employee>(...);
        // 出于这个原因，不允许创建参数化类型的数组
        // var table = new Pair<String>[10];   // ERROR: generic array creation
        // Object[] objArray = table;          // x
        //var table = (Pair<String>[]) new Pair<?>[10];   // 可以声明通配符类型的数组，然后进行强制类型转换，但是结果是不安全的

        // 4. Varargs警告：向参数个数可变的方法传递一个泛型类型的实例
        Collection<Pair<String>> table = new ArrayList<>();
        Pair<String> pair1 = new Pair<>("leslie", "chang");
        Pair<String> pair2 = new Pair<>("bruce", "lee");
        addAll(table, pair1, pair2);

        /* 5. 不能实例化类型变量
        不能在类似 new T(...) 的表达式中使用类型变量。
        public Pair() {first = new T(); second = new T();}  // ERROR
         */

        /* 6. 不能构造泛型数组
        public static <T extends Comparable> T[] minmax(T... a) {
            T[] mm = new T[2];  // ERROR，因为类型擦除会让这个方法构造 Comparable[2] 数组
            // ...
        }
         */

        /* 7. 泛型类的静态上下文中类型变量无效 */
    }

    /** 4. Varargs警告：向参数个数可变的方法传递一个泛型类型的实例
     * 1> 由3我们知道，Java虚拟机不允许创建参数化类型的数组，而这里参数ts实际上是一个数组，包含提供的所有实参
     * 2> 在这种情况下，规则有所松动，只会得到一个警告，而不是错误
     * 3> 可以用注解 @SuppressWarnings("unchecked") 抑制这个警告
     */
    @SuppressWarnings("unchecked")
    public static <T> void addAll(Collection<T> coll, T... ts) {
        System.out.println("ts.getClass()=" + ts.getClass());   // ts.getClass()=class [Lch08.limitations.Pair;
        for (T t : ts) coll.add(t);
    }

    /** 5. 不能实例化类型变量
     * 1. first = T.class.getConstructor().newInstance();   // ERROR
     * 2. 表达式 T.class 是不合法的，因为它会擦除为 Object.class
     * 3. 必须适当地设计API以便得到一个Class对象，如下
     * 4. Class类本身是泛型。例如，String.class是Class<String>的一个实例
     */
    public static <T> Pair<T> makePair(Class<T> cl) {
        try {
            return new Pair<>(cl.getConstructor().newInstance(), cl.getConstructor().newInstance());
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

class Pair<T> {
    private T first;
    private T second;

    public Pair() {}

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {return first;}

    public T getSecond() {return second;}

    public void setFirst(T newFirst) {first = newFirst;}

    public void setSecond(T newSecond) {second = newSecond;}
}

/* 7. 泛型类的静态上下文中类型变量无效，不能在静态字段或方法中引用类型变量
// 如果这样可行，程序就可以声明一个Singleton<Random>以共享一个随机数生成器
class Singleton<T> {
    private static T singleInstance;    // ERROR
    public static T getSingleInstance() {   // ERROR
        if (singleInstance == null) ...
    }
}
 */
