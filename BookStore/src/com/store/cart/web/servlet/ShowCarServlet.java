package com.store.cart.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.cart.service.CarService;
import com.store.web.servlet.BaseServlet;

/**
 * ��̬��ʾ���ﳵ�е�����  (��Ҫ����ҳ�����ˢ��)
 * @author ����
 */
@WebServlet("/ShowCarServlet")
public class ShowCarServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String showCar(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ���ﳵ�е�����������Ϣ�������ݸ����ﳵ��ʾ����
		 CarService car = (CarService) request.getSession().getAttribute("myCar");
		
		//�жϹ��ﳵ�����Ƿ�Ϊ�գ��վ��ȵ�¼
		if(car!=null) {
			//���鼮�ӹ��ﳵ������ж����ύʱ�����յ���治��ʱ�Ĵ�����Ϣ
			request.setAttribute("errorMsg", request.getAttribute("error"));
			request.setAttribute("bookList", car.findAllBook());
			return "shoppingCar.jsp";
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
	}
}
