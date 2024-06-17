package ch08.pair1;

/**
 * 8.2 定义简单的泛型类
 * 1. Pair类引入了一个类型变量T，用尖括号`<>`括起来，放在类名的后面
 * 2. 泛型类可以有多个类型变量
 * 3. 类型变量在整个类定义中用于指定方法的返回类型以及字段和局部变量的类型
 * 4. 常见的类型变量使用大写字母。Java类库使用变量E表示集合的元素类型，K和V分别表示表的键和值的类型。
 *      T/U/S表示“任意类型”
 */
public class Pair<T> {
    private T first;
    private T second;

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

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }
}
