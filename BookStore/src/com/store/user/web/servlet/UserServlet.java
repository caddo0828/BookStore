package com.store.user.web.servlet;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.cart.service.CarService;
import com.store.cart.service.impl.CarServiceImpl;
import com.store.user.domain.User;
import com.store.user.service.UserService;
import com.store.user.service.impl.UserServiceImpl;
import com.store.web.mail.SendMail;
import com.store.web.service.CurrentDate;
import com.store.web.service.UUIDImpl;
import com.store.web.servlet.BaseServlet;


@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();; 
	private CarService car = new CarServiceImpl();
	//�û�ע��
    public String register(HttpServletRequest request, HttpServletResponse response) {
    	//���մӱ����ύ������
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		User user = new User(UUIDImpl.getUID(),name, pwd, email, tel);
		//�ֶ������ü���״̬
		user.setStatus(0);
		//�ֶ����ü�����
		user.setActivationCode(user.getId());
		if(service.insert(user)) {
			//���ͼ����ʼ�
			String content = "��ϲ"+user.getName()+"��Ϊ�̳ǵ��û���"+"<a href='http://localhost:8080/BookStore/UserServlet?method=actival&code="+user.getActivationCode()+"'>����˴�����</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 //ע��ɹ���ת��reg-result.html���棬ֱ��ת�򵽵�¼����
	    	 return "reg-result.html";
	    }else {
	    	//ע��ʧ��
	    	return "register.html";
	    }
    }
    
    
    //�û�����
    public String actival(HttpServletRequest request, HttpServletResponse response) {
    	String code = request.getParameter("code");
		if(code!=null) {
			User user = service.findUserByCode(code);
			if(user!=null) {
				//�޸ļ����״̬
				service.setStatus(user);
				request.setAttribute("err", "�û�����ɹ�");
				return "login.jsp";
			}else {
				request.setAttribute("err", "�û��Ѿ��������,����Ҫ���еڶ��μ���");
				return "login.jsp";
			}
		}else {
			return "login.jsp";
		}
    }
    
    //������֤��
    public void imageNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//����ͼƬ���ƶ���
		BufferedImage image = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB);
		//��ȡ���ƶ���
		Graphics grap = image.getGraphics();
		//���ñ�����ɫ
		grap.setColor(Color.PINK);
		//���ƾ��ο�
		grap.fillRect(0, 0, 60, 30);
		
		//��ȡ���ɵ������
		String number = service.getNumber();
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
    
    
    
    //�û���¼
    public String login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	//���ձ��ύ�ĵ绰�����Լ�����
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		//��ȡ�洢��session�е����ɵ���λ����֤�룬���û��������ƥ��
		String checkNumber = (String) request.getSession().getAttribute("checkNumber");
		//��ȡ�û��������֤��
		String veryCode = request.getParameter("veryCode");
		
		//�Ƚ�����֤����֤���� �����ݿ�ƥ���¼�û�����Ϣ
		if(veryCode!=null&&veryCode.equals(checkNumber)) {
			User user = service.search(name, pwd);
			if(user==null) {
				//�û������ڣ��������
				request.setAttribute("err", "�û���ϵ�绰�������������������������!");
				return "login.jsp";
			}else if(user.getStatus() == 1){
				//�û���ȷ�����Ѿ��������¼��ת,Ϊ�䴴��һ�����ﳵ����������û���Ϣ,���ҽ��û���Ϣ�洢��session��
				 request.getSession().setAttribute("loginUser", user);
				
				//��ǰ��¼ʱ��
				request.getSession().setAttribute("time", CurrentDate.getTime());
				
				//�������ﳵ����,������ӵ�session��
				request.getSession().setAttribute("myCar", car);
				
				//���Զ���¼��cookie,���û��绰�Լ����뱣�棬���ҽ��м��ܴ���
				String keepTime = request.getParameter("keepTime");
				if(keepTime!=null){
					//Ϊ��֤cookie�п��Ա����������ݣ�����base64����
					Cookie cookie = new Cookie("autoLogin",java.net.URLEncoder.encode(name, "utf-8")
							+"="+java.net.URLEncoder.encode(pwd, "utf-8"));
					cookie.setMaxAge(Integer.parseInt(keepTime));
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				//����ٻ���Ʒ��ҳ
				return "ProductServlet?method=showHall";
			}else {
				//�û�δ����
				request.setAttribute("err", "��ǰ�û�δ����!");
				return "login.jsp";
			}
		}else {
			//��������֤�����ʱ����������ʾ
			request.setAttribute("err", "��֤�������������������");
			return "login.jsp";
		}
    }

    //�û���ȫ�˳�
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
    	//��ȫ�˳�,ɾ����������session�е���Ϣ�������������ѹ��,����ɾ��session����������
		request.getSession().invalidate();
		//�ӿͻ���ɾ���Զ���¼��cookie,��ȡ��ǰ�������û��Զ���¼��Ϣ��cookie����ɾ��
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			//�������е�cookie���ҵ�ƥ������ݽ���ɾ��
			for(Cookie c : cookies) {
				if(c.getName().equals("autoLogin")) {
					Cookie cook = new Cookie(c.getName(), c.getValue());
					cook.setMaxAge(0);
					cook.setPath(request.getContextPath());
					response.addCookie(cook);
				}
			}
		}
		return "login.jsp";
    }
}
