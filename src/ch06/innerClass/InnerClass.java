package ch06.innerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * 6.3 内部类
 * 1. 内部类(inner class)是定义在另一个类中的类
 * 2. 内部类的对象会有一个隐式引用，指向实例化这个对象的外部类对象。通过这个指针，可以访问外部对象的全部状态
 */
public class InnerClass {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();

        JOptionPane.showMessageDialog(null, "退出程序");
        System.exit(0);

        /**
         * 6. 内部类对象的构造器语法：outerObject.new InnerClass(construction parameters)
         *      其中 outerObject 表示外部类的对象
         * 7. 在外部类的作用域之外，可以这样引用内部类： OuterClass.InnerClass
         *      如下，TalkingClock.TimerPrinter
         */
        TalkingClock.TimerPrinter timerPrinter = clock.new TimerPrinter();

    }
}

class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        // 承3. 在start方法中构造一个TimePrinter对象后，编译器就会将当前语音时钟的this引用传递给这个构造器
        //      TimerPrinter listener = new TimerPrinter(this);
        TimerPrinter listener = new TimerPrinter();
        Timer timer = new Timer(interval, listener);
        timer.start();

    }

    /**
     * 1. TimerPrinter类没有实例字段或者名为beep的变量。beep指示创建这个TimePrinter的TalkingClock对象中的字段
     * 2. 一个内部类方法可以访问自身的实例字段，也可以访问创建它的外部类对象的实例字段
     * 3. 外部类的引用在构造器中设置，编译器会修改所有的内部类构造器，添加一个对应外部类引用的参数
     *      public TimePrinter(TalkingClock clock) {
     *          outer = clock;
     *          // if (outer.beep) ...
     *      }
     * 4. 可以把 TimePrinter 类声明为私有(private)。这样一来，只有TalkingClock方法才能够构造TimePrinter对象
     *      只有内部类可以是私有的(private)，而常规类可以有包可见性(默认)或公共可见性(public)
     *  9. 内部类将转换为常规的类文件，用 $(美元符号) 分隔外部类名和内部类名
     */
    public class TimerPrinter implements ActionListener {

        private String field1;
        // 内部类中声明的所有静态字段都必须是final，并初始化为一个编译时常量
        private static final String field2 = "charlie";


        /**
         * 8. 内部类不能有 static 方法
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
            if (beep) Toolkit.getDefaultToolkit().beep();
            // 5. 外部类引用的正规语法表达式：OuterClass.this 表示外部类引用
            //if (TalkingClock.this.beep) ...
        }
    }

}
