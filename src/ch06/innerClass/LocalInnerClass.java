package ch06.innerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * 6.3.4 局部内部类
 * 1. 可以在一个方法中局部地定义类
 *
 */
public class LocalInnerClass {
    public static void main(String[] args) {
        TalkingClock2 clock2 = new TalkingClock2(1000, true);
        clock2.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock2 {
    private int interval;
    private boolean beep;

    public TalkingClock2(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        /**
         * 2. 声明局部类时不能有访问说明符(即public或private)。局部类的作用域总是限定在声明这个局部类的块中，对外部世界完全隐藏
         *      因为声明在局部，可以看作一个类对象(TimePrinter类型)，所以不能有访问说明符
         */
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }

        TimePrinter listener = new TimePrinter();
        Timer timer = new Timer(interval, listener);
        timer.start();
    }

    /**
     * 3. 局部类不仅能够访问外部类的字段，还可以访问局部变量。
     *      要求这些变量必须是事实最终变量(effectively final)，即一旦赋值就绝对不会改变
     * 4. 局部内部类中会保存最终变量的值，在下面的 TalkingClock$TimePrinter 中，有
     *      final boolean val$beep; // 因为局部内部类中只是用到了beep变量
     * 5. 创建一个对象时，beep变量的当前值会存储在 val$beep 字段中
     */
    public void start(int interval, boolean beep) {
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }

        TimePrinter listener = new TimePrinter();
        Timer timer = new Timer(interval, listener);
        timer.start();
    }

}
