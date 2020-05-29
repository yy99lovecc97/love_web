package com.lovecc.love_web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
@WebServlet(urlPatterns = "/authImg")
public class AuthImg extends HttpServlet {
    private static final long serialVersionUID = -2313486423089907397L;
    //定义图形验证码中绘制字符的字体
    private final Font font = new Font("Arial Black" , Font.PLAIN , 16);
    //定义图形验证码的大小
    private final int IMG_WIDTH = 100;
    private final int IMG_HEIGTH = 18;
    //定义一个获取随机颜色的方法
    private Color getRandColor(int fc , int bc){
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        // 得到随机颜色
        return new Color(r , g , b);
    }
    //定义获取随机字符串的方法
    private String getRandomChar(){
        //生成0,1,2的随机数字
        int rand = (int) Math.round(Math.random() * 2);
        long itmp = 0;
        char ctmp = '\u0000';
        switch (rand){
            //生成大写字母
            case 1:
                itmp = Math.round(Math.random() * 25 + 65);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            // 生成小写字母
            case 2:
                itmp = Math.round(Math.random() * 25 + 97);
                ctmp = (char)itmp;
                return String.valueOf(ctmp);
            //生成数字
            default:
                itmp = Math.round(Math.random() * 9);
                return itmp+"";
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置禁止缓存
        resp.setHeader("Pragma" , "No-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires", -1);
        resp.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(IMG_WIDTH , IMG_HEIGTH , BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();
        graphics.setColor(getRandColor(200,250));
        //填充背景色
        graphics.fillRect(1 , 1 , IMG_WIDTH , IMG_HEIGTH);
        //为图形验证码绘制边框
        graphics.setColor(new Color(102 , 102 , 102));
        graphics.drawRect(0 , 0 , IMG_WIDTH , IMG_HEIGTH);
        graphics.setColor(getRandColor(160,200));
        //生成随机干扰线
        for (int i =0; i < 30 ; i++){
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGTH - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics.drawLine(x , y , x+xl , y+yl);
        }
        // 生成随机干扰线
        for (int i = 0 ; i < 30 ; i++)
        {
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGTH - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            graphics.drawLine(x , y , x - xl , y - yl);
        }
        //设置绘制字符的字体
        graphics.setFont(font);
        //用于保存系统生成的随机字符串
        String sRand = "";
        for (int i = 0 ; i < 6 ; i++){
            String tmp = getRandomChar();
            sRand += tmp;
            // 获取随机颜色
            graphics.setColor(new Color(20 + random.nextInt(110)
                    ,20 + random.nextInt(110)
                    ,20 + random.nextInt(110)));
            // 在图片上绘制系统生成的随机字符
            graphics.drawString(tmp , 15 * i + 10,15);
        }
        //获取一个HttpSession对象
        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("rand" , sRand);
        graphics.dispose();
        //向输出流中输出图片
        ImageIO.write(image , "JPEG" , resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
