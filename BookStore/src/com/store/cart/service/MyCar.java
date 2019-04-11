package com.store.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.store.book.domain.Book;

/**
 * 购物车类，对加入购物车的数据进行处理。进行书籍添加，删除，修改，清空购物车，以及显示购物车中的所有数据
 * @author 老腰
 */
public class MyCar {
	private static HashMap<String, Book> hm = new HashMap<String, Book>();

	/**
	 * 在购物车中添加书籍
	 * @param id  map集合主键，以书籍id作为主键
	 * @param book  要添加到购物车的书籍
	 * @param butNums  购买的书籍数量
	 */
	public void add(String id, Book book,int buyNums) {
		//判断购物车中是否已经存在此书籍,存在取出书籍，进行购买数量的修改
		if (hm.containsKey(id)) {
			book = hm.get(id);
			book.setShopNums(book.getShopNums() + buyNums);
			hm.put(id, book);
		} else {
			//不存在书籍时，设置书籍初始购买数量，并添加此书籍
			book.setShopNums(buyNums);
			hm.put(id, book);
		}
	}

	/**
	 * 根据书籍的id号，删除购物车中对应的书籍
	 * @param id   书籍id,也是map集合的键
	 */
	public void delete(String id) {
		hm.remove(id);
	}

	/**
	 * 修改购物车中对应键的书籍的购买数量
	 * @param id 书籍的id，也是map集合的键值
	 * @param nums 修改后的购买数量
	 */
	public void update(String id,int nums) {
		Book book = hm.get(id);
		book.setShopNums(nums);
		hm.put(id, book);
		
	}
	
	/**
	 * 清空购物车
	 */
	public void clear() {
		hm.clear();
	}
	
	/**
	 * 查询购物车中的所有书籍
	 * @return
	 */
	public ArrayList<Book> findAllBook() {
		ArrayList<Book> list = new ArrayList<Book>();
		Iterator<String> iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			String id = iterator.next();
			Book book = hm.get(id);
			list.add(book);
		}
		return list;
	}
	
	

}
