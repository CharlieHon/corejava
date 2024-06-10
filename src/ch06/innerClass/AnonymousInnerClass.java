package ch06.innerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * 6.3.6 匿名内部类
 * 1. 只想创建这个类的一个对象，甚至不需要为类指定名字，这样的类称为匿名内部类(anonymous inner class)
 *
 */
public class AnonymousInnerClass {

    public static void main(String[] args) {
        TalkingClock3 clock3 = new TalkingClock3(1000, true);
        clock3.start(5000, true);

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock3 {
    private int interval;
    private boolean beep;

    public TalkingClock3(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start(int interval, boolean beep) {
        /**
         * 2. 创建一个类的新对象，这个类实现了 ActionListener 接口，需要实现的方法 actionPerformed 是大括号 {} 中定义的方法
         *  new SuperType(construction parameters) {
         *      inner class methods and data
         *  }
         * 3. SuperType可以是接口，也可以是一个类
         *  1> 对于接口，内部类需要实现这个接口 SupperType
         *  2> 对于类，内部类就要扩展这个类
         * 4. 由于构造器的名字必须和类名相同，而匿名内部类没有类名，所以，匿名内部类不能有构造器.
         *  1) 实际上，构造参数要传递给超类(superclass)构造器
         *  2) 只要内部类实现了一个接口，就不能有任何构造参数。不过，仍然要提供一组小括号
         *  3) 尽管匿名类不能有构造器，但可以提供一个对象初始化块
         */
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        };

        Timer timer = new Timer(interval, listener);
        timer.start();
    }
}
