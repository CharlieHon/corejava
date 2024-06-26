# core java

## ch03 Java的基本程序设计结构

## ch06 接口、lambda表达式与内部类

### 6.2 lambda表达式

| 函数式接口          | 参数类型 | 返回类型      | 抽象方法   | 描述             | 其它方法                    |
|----------------|------|-----------|--------|----------------|-------------------------|
| `Runnable`     | 无    | `void`    | `run`  | 运行一个无参数或返回值的动作 |                         |
| `Supplier<T>`  | 无    | `T`       | `get`  | 提供一个`T`类型的值    |                         |
| `Predicate<T>` | `T`  | `boolean` | `test` | 布尔值函数          | `and,or,negate,isEqual` |

> 如果设计自己的接口，其中只有一个抽象方法，可以用 `@FunctionalInterface` 注解来标记这个接口
