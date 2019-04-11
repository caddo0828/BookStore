package com.store.user.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.user.service.RandomHelper;

/** 绘制验证码图片
 * @author 老腰
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建图片绘制对象
		BufferedImage image = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB);
		//获取绘制对象
		Graphics grap = image.getGraphics();
		//设置背景颜色
		grap.setColor(Color.PINK);
		//绘制矩形框
		grap.fillRect(0, 0, 60, 30);
		
		//获取生成的随机数
		String number = RandomHelper.getNumber();
		//设置随机验证码字体Font (名字无，字体为斜体，大小为20)
		grap.setFont(new Font(null, Font.ITALIC, 20));
		//设置随机的验证码颜色
		Random random = new Random();
		//设置随机数的字体颜色
		grap.setColor(new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255)));
		//将生成的数字文本加载入绘制框中
		grap.drawString(number, 10, 30);
		
		//将生成的随机数保存在session中，取出数据与输入数据进行匹配
		request.getSession().setAttribute("checkNumber", number);
		
		//绘制干扰线
		for(int i=0;i<10;i++) {
			//设置干扰线的随机颜色
			/*grap.setColor(new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255)));
			//绘制干扰线随机位置：从（x1,y1）范围 到（x2,y2）范围之间
			grap.drawLine(random.nextInt(60), random.nextInt(30), 10+random.nextInt(60), 10+random.nextInt(30));*/
			//绘制干扰点
			grap.setColor(new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255)));
			grap.drawOval(60, 30,1,0);
		}
	    //将绘制的图片加载进内存
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
