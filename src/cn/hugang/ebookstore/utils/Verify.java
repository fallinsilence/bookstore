package cn.hugang.ebookstore.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("ALL")
@WebServlet(name = "Verify",urlPatterns = "/verify")
public class Verify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 1 在内存中创建图片
         * 创建图片,需要定义图片的宽度和高度.
         * 利用BufferedImage类来创建图片.
         * new BufferedImage(宽度, 高度, 图片类型)
         */
        StringBuffer verify = new StringBuffer();
        //verify.delete(0, verify.length());
        int width = 120;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        /*2 绘制图片背景颜色
         * 通过创建的图片对象的getGraphics()方法,获取画笔.
         * 通过画笔对象的setColor()方法设置图片的背景颜色.
         * 通过画笔对象的fillRect()方法设置背景颜色填充的面积.
         */
        Graphics2D graphics2d = image.createGraphics();
        graphics2d.setColor(Color.gray);
        graphics2d.fillRect(0, 0, width, height);
        /*
        * 3 绘制边框
        * * 通过画笔对象的drawRect()方法绘制边框的面积.
        */
        //graphics2d.setColor(Color.BLUE);
        //graphics2d.drawRect(1, 1, width - 1, height - 1);
        /* 4 向图片中生成显示的验证码内容
         * 通过画笔对象的setFont()方法设置验证码内容的字体、大小等.
         * word表示生成验证码的备选文本内容.
         */
        graphics2d.setColor(Color.YELLOW);
        graphics2d.setFont(new Font(" 新宋体 ", Font.BOLD, 18));
        String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        // 这段代码用于将生成的验证码内容写入到图片中.
        Random random = new Random();
        int x = 5;
        for (int i = 0; i < 4; i++) {
            // 加入字体旋转 角度为"-30-30"之间.
            int jiaodu = random.nextInt(60) - 30;
            // 转换角度为弧度.
            double theta = jiaodu * Math.PI / 180;
            // 生成下标
            int randomIndex = random.nextInt(word.length());
            // 获取用于验证码显示的字符.
            char c = word.charAt(randomIndex);
            verify.append(c);
            // 将字符写入图片.
            graphics2d.rotate(theta, x, 20);
            graphics2d.drawString(c + "", x, 20);
            graphics2d.rotate(-theta, x, 20);
            // 设置下一个字符出现的水平坐标.
            x += 30;
        }
        ServletContext context = getServletContext();
        //ServletContext中存入验证码信息
        context.setAttribute("checkString" , verify.toString());
        // 5 绘制干扰线
        graphics2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 1; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics2d.drawLine(x1, y1, x2, y2);
        }
        // 6 释放内存中的资源
        graphics2d.dispose();
        // 7 将生成的图片,响应到客户端浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
