package com.store.order.dao;

import java.util.ArrayList;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;
import com.store.order.service.DataService;
import com.store.user.domain.User;

public interface OrderDao {
	/** ��orders�� �Լ�orderitem������Ӧ������
	 *@param helper ���������࣬�������������ӽ�������
	 * @param user  �û��࣬���ڻ�ȡ�û���id�ţ������ڶ�Ӧ�����ݿ��orders��
	 * @param order ������Ϣ�࣬��ȡ�ջ����������ջ��绰���ջ���ַ���������ݿ��orders��
	 * @return �������ݿ����Ϣ�Ƿ�ɹ�����
	 */
	 boolean saveOrder(DataService helper ,User user,Order order);

	 /**
	 * �����û���id�Ų�ѯ��Ӧ�Ķ�������Ҫ����ǰ̨�����û���ѯ��ʷ����)
	 * @param userID �û�id
	 * @return
	 */
	 ArrayList<Order>  findOrder(String userID) ;
	 
	//����orders���еĶ����Ų�ѯorderitem���е�����
	ArrayList<OrderItem> findOrdertems(String orderID);
	
	/**
	 * ��ѯ�������е���ҳ��
	 * @return  ������orders����ҳ��
	 */
	int getCount();
	
	/**
	 * ����������orders���з�ҳ����
	 * @param pageNumber  Ҫ��ʾ������һҳ������
	 * @return  ��Ӧҳ���Ķ�������
	 */
	 ArrayList<Order> findPageOrder(int pageNumber) ;
	 
	 /**
	 * ���ݶ�����ɾ������������ɾ����Ӧ�Ķ����ӱ������
	 * @param orderId ������
	 * @return  �Ƿ�ɾ���ɹ�
	 */
	 boolean deleteOrderAndItem(String orderId);
	 
	 /**
	 * ���ݶ����Ż����ջ�����������ģ��ƥ����Ҷ�������
	 * @param orderId  orders���еĶ�����
	 * @param orderName  ����������
	 * @return  �������ݵļ���
	 */
	 ArrayList<Order> searchByIdOrName(String orderId,String orderName);
	 
	 
	 /**
	 * ���ݶ������޸�orders���еĶ�������������Ʒ�ܼۣ������绰���ջ���ַ
	 * @param orderId    ������orders�еĶ�����
	 * @param orderName  ����������
	 * @param totalPrice ��Ʒ�ܼ�
	 * @param address    �ջ���ַ
	 * @param telephone  �����绰
	 * @return �Ƿ�����ɹ�
	 */
	 boolean updateOrdersById(String orderId,String orderName,double totalPrice,String address, String telephone) ;
	 
	 
}
