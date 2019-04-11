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

/** ������֤��ͼƬ
 * @author ����
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ͼƬ���ƶ���
		BufferedImage image = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB);
		//��ȡ���ƶ���
		Graphics grap = image.getGraphics();
		//���ñ�����ɫ
		grap.setColor(Color.PINK);
		//���ƾ��ο�
		grap.fillRect(0, 0, 60, 30);
		
		//��ȡ���ɵ������
		String number = RandomHelper.getNumber();
		//���������֤������Font (�����ޣ�����Ϊб�壬��СΪ20)
		grap.setFont(new Font(null, Font.ITALIC, 20));
		//�����������֤����ɫ
		Random random = new Random();
		//�����������������ɫ
		grap.setColor(new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255)));
		//�����ɵ������ı���������ƿ���
		grap.drawString(number, 10, 30);
		
		//�����ɵ������������session�У�ȡ���������������ݽ���ƥ��
		request.getSession().setAttribute("checkNumber", number);
		
		//���Ƹ�����
		for(int i=0;i<10;i++) {
			//���ø����ߵ������ɫ
			/*grap.setColor(new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255)));
			//���Ƹ��������λ�ã��ӣ�x1,y1����Χ ����x2,y2����Χ֮��
			grap.drawLine(random.nextInt(60), random.nextInt(30), 10+random.nextInt(60), 10+random.nextInt(30));*/
			//���Ƹ��ŵ�
			grap.setColor(new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255)));
			grap.drawOval(60, 30,1,0);
		}
	    //�����Ƶ�ͼƬ���ؽ��ڴ�
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
