package com.wsbg.common;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.IOException;  
import java.util.Random;  
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
/** 
 * 验证码图片生成Servlet类，直接调用该Servlet即可使用 
 * 取值的时候调用session.getAttribute("code")得到生成的值 
 * @author  lgc
 * @time 2011-6-26 上午11:21:04 
 * 
 */  
public class ImageServlet extends HttpServlet {
	       
        private static final long serialVersionUID = 1L; 
        
        private Logger logger = LoggerFactory.getLogger(ImageServlet.class);
        private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色  
                Random random = new Random();  
                if (fc > 255)  
                        fc = 255;  
                if (fc < 0)  
                        fc = 0;  
                if (bc > 255)  
                        bc = 255;  
                if (bc < 0)  
                        bc = 0;  
                int r = fc + random.nextInt(bc - fc);  
                int g = fc + random.nextInt(bc - fc);  
                int b = fc + random.nextInt(bc - fc);  
                return new Color(r, g, b);  
        }  
        @Override  
        protected void service(HttpServletRequest request,  
                        HttpServletResponse response) throws ServletException, IOException {  
                // 设置输出  
                response.setContentType("image/jpeg");  
                int width = 60;  
                int height = 20;  
                // 产生随机数  
                Random r = new Random();  
                // 把随机数绘制成图像  
                BufferedImage imgbuf = new BufferedImage(width, height,  
                                BufferedImage.TYPE_INT_RGB);// 产生缓冲图像,40宽20高  
                Graphics2D g = imgbuf.createGraphics();// 取得缓冲的绘制环境  
                // 开始绘制  
                g.setColor(getRandColor(200, 250));// 设定背景色  
                g.fillRect(0, 0, width, height);  
                // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到  
                g.setColor(getRandColor(160, 200));  
                for (int i = 0; i < 155; i++) {  
                        int x = r.nextInt(width);  
                        int y = r.nextInt(height);  
                        int xl = r.nextInt(12);  
                        int yl = r.nextInt(12);  
                        g.drawLine(x, y, x + xl, y + yl);  
                }  
                // 随机产生100个干扰点，使图像中的验证码不易被其他分析程序探测到  
                g.setColor(getRandColor(120, 240));  
                for (int i = 0; i < 100; i++) {  
                        int x = r.nextInt(width);  
                        int y = r.nextInt(height);  
                        g.drawOval(x, y, 0, 0);  
                }  
                g.setFont(new Font("Times New Roman", Font.PLAIN, 18));  
                String scode = "";  
                for (int i = 0; i < 4; i++) {  
                        String rand = String.valueOf(r.nextInt(10));  
                        scode += rand;  
                        g.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110),  
                                        20 + r.nextInt(110)));  
                        // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成  
                        g.drawString(rand, 13 * i + 6, 16);  
                }  
                request.getSession().setAttribute(CommonStatic.VALIDATE_CODE, scode);  
                logger.info("code------->"+scode);  
                // 输出图像  
                ServletOutputStream out = response.getOutputStream();// 得到HTTP的流  
                // JPEGCodec.createJPEGEncoder(out);转码  
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);// 产生JPEG的图像加码器  
                encoder.encode(imgbuf);  
                out.flush();  
        }  
}
