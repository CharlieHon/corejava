package ch06.interfaces;

/**
 * 6.1.4 静态和私有方法
 * 1) 在Java8中，允许在接口中增加静态方法
 * 2) 在Java9中，接口中的方法可以是 private方法。private方法可以是 静态方法 或 实例方法。
 *      由于私有方法只能在接口本身方法中，使用，所以指它们指示作为接口中其它方法的辅助方法
 */
public interface Flexible {
    public static void relax() {    // public 可省略
        helper();   // 私有方法常作为其它方法的辅助方法
        System.out.println("KunFu~");
    }

    // 私有方法必须有函数体，即必须在接口内实现。静态方法同
    private static void helper() {
        System.out.println("a private f.");
    }

    // 默认为 public 的抽象方法
    void drink(String name);
}
