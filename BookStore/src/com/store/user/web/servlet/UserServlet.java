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
	//用户注册
    public String register(HttpServletRequest request, HttpServletResponse response) {
    	//接收从表单中提交的数据
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		User user = new User(UUIDImpl.getUID(),name, pwd, email, tel);
		//手动改设置激活状态
		user.setStatus(0);
		//手动设置激活码
		user.setActivationCode(user.getId());
		if(service.insert(user)) {
			//发送激活邮件
			String content = "恭喜"+user.getName()+"成为商城的用户，"+"<a href='http://localhost:8080/BookStore/UserServlet?method=actival&code="+user.getActivationCode()+"'>点击此处激活</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 //注册成功跳转到reg-result.html界面，直接转向到登录界面
	    	 return "reg-result.html";
	    }else {
	    	//注册失败
	    	return "register.html";
	    }
    }
    
    
    //用户激活
    public String actival(HttpServletRequest request, HttpServletResponse response) {
    	String code = request.getParameter("code");
		if(code!=null) {
			User user = service.findUserByCode(code);
			if(user!=null) {
				//修改激活的状态
				service.setStatus(user);
				request.setAttribute("err", "用户激活成功");
				return "login.jsp";
			}else {
				request.setAttribute("err", "用户已经激活过了,不需要进行第二次激活");
				return "login.jsp";
			}
		}else {
			return "login.jsp";
		}
    }
    
    //生成验证码
    public void imageNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//创建图片绘制对象
		BufferedImage image = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB);
		//获取绘制对象
		Graphics grap = image.getGraphics();
		//设置背景颜色
		grap.setColor(Color.PINK);
		//绘制矩形框
		grap.fillRect(0, 0, 60, 30);
		
		//获取生成的随机数
		String number = service.getNumber();
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
    
    
    
    //用户登录
    public String login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	//接收表单提交的电话号码以及密码
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		//获取存储在session中的生成的四位数验证码，与用户输入进行匹配
		String checkNumber = (String) request.getSession().getAttribute("checkNumber");
		//获取用户输入的验证码
		String veryCode = request.getParameter("veryCode");
		
		//先进行验证码验证，再 到数据库匹配登录用户的信息
		if(veryCode!=null&&veryCode.equals(checkNumber)) {
			User user = service.search(name, pwd);
			if(user==null) {
				//用户不存在，输入错误
				request.setAttribute("err", "用户联系电话或者密码输入错误，请重新输入!");
				return "login.jsp";
			}else if(user.getStatus() == 1){
				//用户正确并且已经被激活，登录跳转,为其创建一个购物车，用于添加用户信息,并且将用户信息存储到session中
				 request.getSession().setAttribute("loginUser", user);
				
				//当前登录时间
				request.getSession().setAttribute("time", CurrentDate.getTime());
				
				//创建购物车对象,并且添加到session中
				request.getSession().setAttribute("myCar", car);
				
				//做自动登录的cookie,将用户电话以及密码保存，并且进行加密处理
				String keepTime = request.getParameter("keepTime");
				if(keepTime!=null){
					//为保证cookie中可以保存中文数据，进行base64编码
					Cookie cookie = new Cookie("autoLogin",java.net.URLEncoder.encode(name, "utf-8")
							+"="+java.net.URLEncoder.encode(pwd, "utf-8"));
					cookie.setMaxAge(Integer.parseInt(keepTime));
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				//进入百货商品首页
				return "ProductServlet?method=showHall";
			}else {
				//用户未激活
				request.setAttribute("err", "当前用户未激活!");
				return "login.jsp";
			}
		}else {
			//当输入验证码错误时给出错误提示
			request.setAttribute("err", "验证码输入错误，请重新输入");
			return "login.jsp";
		}
    }

    //用户安全退出
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
    	//安全退出,删除掉保存在session中的信息，减轻服务器的压力,彻底删除session中所有属性
		request.getSession().invalidate();
		//从客户端删除自动登录的cookie,获取当前保存了用户自动登录信息的cookie并且删除
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			//遍历所有的cookie，找到匹配的数据进行删除
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
