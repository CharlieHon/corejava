package ch06.interfaces;

/**
 * 6.1.5 默认方法
 * 1> 可以为任何接口方法提供一个默认实现。必须用 default修饰符 标记这样一个方法
 */
public interface Flyable {

    private String name() {
        return "People";
    }

    default int prepare() {
        // 2> 默认方法可以调用其它方法
        System.out.println(name());
        return 0;
    }

}
