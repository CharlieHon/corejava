package ch05.resources;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 5.9.3 资源
 * 类通常有一些关联的数据文件，例如：
 * 1) 图像和声音文件
 * 2) 包含消息字符串和按钮标签的文本文件
 * Class类提供了一个可以查找资源文件的服务：
 * 1. 获得拥有资源的类的Class对象，例如ResourceTest.class
 * 2. 有些方法接收描述资源位置的URL，则可以调用
 *      URL url = cl.Resource("about.txt");
 * 3. 否则，使用 getResourceAsStream 方法得到一个输入流来读取文件中的数据
 * Java虚拟机知道如何查找一个类，所以它能够搜索到相同位置上的关联资源。
 * JavaBase/
 *  ch03/
 *  ch05/
 *      corejava/
 *          title.txt
 *      resources/
 *          data/
 *              about.txt
 *          about.gif
 *          ResourceTest
 */
public class ResourceTest {
    public static void main(String[] args) throws IOException {
        Class<ResourceTest> cl = ResourceTest.class;
        URL aboutURL = cl.getResource("about.gif");
        ImageIcon icon = new ImageIcon(aboutURL);

        InputStream stream = cl.getResourceAsStream("data/about.txt");
        String about = new String(stream.readAllBytes(), "UTF-8");

        InputStream stream2 = cl.getResourceAsStream("/ch05/corejava/title.txt");   // 当前目录为 JavaBase
        String title = new String(stream2.readAllBytes(), StandardCharsets.UTF_8).strip();

        JOptionPane.showMessageDialog(null, about, title, JOptionPane.INFORMATION_MESSAGE, icon);

    }
}
